package dot.payment;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PassengerDetails {
    String isPrimaryPassenger;
    @Builder.Default
    private String passengerId = "-1";
    @Builder.Default

    private String passengerType = "Adult";
    @Builder.Default
    private String title = "Mr";
    @Builder.Default
    private String firstName = "test";
    @Builder.Default
    private String lastName = "test";
    @Builder.Default
    private String middleName = null;
    @Builder.Default
    private String dob = null;
    @Builder.Default
    private String emailAddress = "test@test.com";
    @Builder.Default
    private String nationality = "";
    @Builder.Default
    private String documentNumber = null;
    @Builder.Default
    private String documentExpiryDate = null;
    @Builder.Default
    private String passportIssuingCountry = "";
    @Builder.Default
    private String contactMobileContryCode = "961";
    @Builder.Default
    private String contactMobileNumber = "34343434";
    @Builder.Default
    private String accompanyingAdult = null;
    @Builder.Default
    private String memberId = null;
    @Builder.Default
    private String countryOfResidence = "";
    @Builder.Default
    private String contactMobileNumberField = null;
    @Builder.Default
    private String contactTelephoneContryCode = null;
    @Builder.Default
    private String contactTelephoneNumber = null;
    @Builder.Default
    private String contactTelephoneField = null;
    @Builder.Default
    private String tierId = null;
    @Builder.Default
    private String tierName = null;
    @Builder.Default
    private String deleteEnabled = null;
    @Builder.Default
    private String isMinor = null;
    @Builder.Default
    private String ffpEnabled = null;
    @Builder.Default
    private String tierInfo = null;


    public static List<PassengerDetails> createPassangerDetails(int numberOfPassangers) {
        List<PassengerDetails> passengerList = new ArrayList<>();
        for (int i = 0; i < numberOfPassangers; i++) {
            if (i==0){
                passengerList.add(PassengerDetails.builder().passengerId("-" + (numberOfPassangers - i)).isPrimaryPassenger("true").build());
            }else passengerList.add(PassengerDetails.builder().passengerId("-" + (numberOfPassangers - i)).build());
        }
        return passengerList;
    }


}


