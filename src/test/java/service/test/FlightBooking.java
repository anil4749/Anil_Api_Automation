package service.test;
import ValidateResponse.CommanValidation;
import base.TestBase;
import dot.payLater.PayLaterDto;
import dot.payment.PaymentDto;
import dot.searchFlight.SearchFlightDto;
import dot.selectFlight.Preferences;
import dot.selectFlight.SelectFlightDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.steps.flights.ConfirmBooking;
import service.steps.flights.SearchFlight;
import service.steps.flights.SelectFlight;
import service.steps.payment.GetPciSession;
import service.steps.payment.PayLater;
import service.steps.payment.Payment;

import java.util.List;

import static ValidateResponse.CommanValidation.getValueAsString;
import static ValidateResponse.CommanValidation.verityStatusCode;
import static dot.payment.PassengerDetails.createPassangerDetails;


public class FlightBooking {
    private SearchFlight searchFlight;
    private SelectFlight select_Flight;
    private ConfirmBooking confirmBooking;
    private GetPciSession getPciSession;
    private PayLater payLater;
    private Payment payment;
private CommanValidation commanValidation;
    @BeforeClass
    public void instanceSetup(){
        searchFlight = new SearchFlight();
        select_Flight = new SelectFlight();
        confirmBooking = new ConfirmBooking();
        getPciSession = new GetPciSession();
        payLater = new PayLater();
        payment = new Payment();
        commanValidation = new CommanValidation();
    }


    @Test(description = "Book flight")
    public void flightBooking() {
        //Test data
        String source = "DXB";
        String destination = "PRG";
        String date = "05/29/2024 12:00 AM";
        int numberOdAdult = 2;
        int numberOfChild = 0;


        SoftAssert softAssert=new SoftAssert();
        //Search flight
        SearchFlightDto searchFlightDto = SearchFlightDto.searchFlight(source, destination, date, numberOdAdult, numberOfChild);
        Response getAllFlights = searchFlight.searchFlight(searchFlightDto);
        verityStatusCode(getAllFlights.getStatusCode(),HttpStatus.SC_OK,softAssert, "Verify status code of search flight");
        JsonPath availableFlights = getAllFlights.jsonPath();

        //get sessionToken
        String securityToken = getAllFlights.getHeader("securityToken");

        //Select flight
        JSONObject selectFare = new JSONObject(availableFlights.getJsonObject("segments[0].flights[0].fareTypes[0]"));
        JSONObject selectFlight = new JSONObject(availableFlights.getJsonObject("segments[0].flights[0]"));
        selectFlight.put("selectedFare", selectFare);
        JSONArray selectedFlights = new JSONArray();
        selectedFlights.add(selectFlight);
        SelectFlightDto selectFlightDto = SelectFlightDto.builder().searchRequest(searchFlightDto).preferences(
                Preferences.builder().build()
        ).selectedFlights(selectedFlights).build();
        Response response = select_Flight.selectFlight(selectFlightDto, securityToken);
        verityStatusCode(response.statusCode(),HttpStatus.SC_OK,softAssert, "Verify status code of add flight");

        //payment
        PaymentDto paymentDto = PaymentDto.builder().
                passengerList(createPassangerDetails(numberOdAdult+numberOfChild)).
                preferences(Preferences.builder().build()).
                searchRequest(searchFlightDto).selectedFlights(selectedFlights).
                confirmUrl(new TestBase().host+new TestBase().confirmFlight).build();
        Response paymnet = payment.requestPayment(paymentDto,securityToken);
        verityStatusCode(paymnet.statusCode(),HttpStatus.SC_OK,softAssert, "Verify status code payment");
        List<String> transaction = payment.paymentInfo(paymnet);
        String sessionId = transaction.get(0);
        String transactionKey = transaction.get(1);

        //getPccSession
        Response getSessionDetails = getPciSession.getPciSession(sessionId,transactionKey,paymentDto,securityToken);
        verityStatusCode(getSessionDetails.statusCode(),HttpStatus.SC_OK,softAssert, "Verify status code of getPicSession");
        String paymentId =getValueAsString(getSessionDetails,"paymentId");
        String currency =getValueAsString(getSessionDetails,"currency");
        String amount =getValueAsString(getSessionDetails,"amount");

        //payLater
        PayLaterDto payLaterDto = PayLaterDto.builder().amount(amount).currency(currency).paymentId(paymentId).sessionId(sessionId).build();
        Response payLaterResp = payLater.payLater(sessionId,transactionKey,payLaterDto,securityToken);
        verityStatusCode(payLaterResp.statusCode(),HttpStatus.SC_OK,softAssert, "Verify status code of pay later");

        //confirm
        Response confirm = confirmBooking.confirmBooking(securityToken);
        verityStatusCode(confirm.statusCode(),HttpStatus.SC_OK,softAssert, "Verify status code of confirm");

        System.out.println("PNR ====> "  +getValueAsString(confirm,"pnrInformation.bookingReference"));

        softAssert.assertAll();
    }
}
