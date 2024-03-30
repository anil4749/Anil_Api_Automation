package service.steps.payment;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import dot.payLater.PayLaterDto;
import dot.payment.PaymentDto;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payment extends TestBase {
    ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> queryParams = new HashMap<>();
    private final String payload = null;

    @Step("Request for payment")
    public Response requestPayment(PaymentDto requestBodyDto, String securityToken) {
        Response response = null;
        try {
            url = host + requestPayment;
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("authorization", accessToken);
            headers.put("appID", "Desktop");
            headers.put("SecurityToken", securityToken);
            String payload = objectMapper.writeValueAsString(requestBodyDto);
            RestClient restClient = new RestClient(url, payload, headers, queryParams, null, null, null);
            response = restClient.postResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Step("Get payment info")
    public List<String> paymentInfo(Response response){
        List<String> paymentinfo = new ArrayList<>();
        JsonPath payment = response.jsonPath();
        String[] url = payment.getString("pciURLtoRedirect").split("&");
        paymentinfo.add(url[0].split("=")[1]);
        paymentinfo.add(url[1].split("=")[1]);
        return paymentinfo;
    }

}
