package service.steps.flights;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ConfirmBooking extends TestBase {
    ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> queryParams = new HashMap<>();
    private final String payload = null;

    @Step("Confirm Booking")
    public Response confirmBooking(String securityToken) {
        Response response = null;
        try {
            url = host + confirmFlight;
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("authorization", accessToken);
            headers.put("appID", "Desktop");
            headers.put("SecurityToken", securityToken);
            RestClient restClient = new RestClient(url, payload, headers, queryParams, null, null, null);
            response = restClient.postResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
