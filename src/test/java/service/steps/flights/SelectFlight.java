package service.steps.flights;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import dot.selectFlight.SelectFlightDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class SelectFlight extends TestBase {
    ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> queryParams = new HashMap<>();
    private final String payload = null;

    @Step("Select Flight")
    public Response selectFlight(SelectFlightDto requestBodyDto, String securityToken) {
        Response response = null;
        try {
            url = host + selectFlight;
            Map<String, String> headers=new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("authorization", accessToken);
            headers.put("appID", "Desktop");
            headers.put("SecurityToken", securityToken);
            String payload = objectMapper.writeValueAsString(requestBodyDto);
            RestClient restClient = new RestClient(url, payload, headers, queryParams, null, null, null);
            response = restClient.postResponse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
