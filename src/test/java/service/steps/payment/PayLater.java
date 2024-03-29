package service.steps.payment;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import dot.payLater.PayLaterDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PayLater extends TestBase {
    ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private String payload = null;

    @Step("Pay Later")
    public Response payLater(String sessionId, String transactionKey, PayLaterDto requestBodyDto, String securityToken) {
        Response response = null;
        try {
            url = paymentHost + selectPaymentMethod;
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("authorization", accessToken);
            headers.put("appID", "Desktop");
            headers.put("SecurityToken", securityToken);
            queryParams.put("sessionId", sessionId);
            queryParams.put("transactionKey", transactionKey);
            queryParams.put("systemId", "FZPSS");
            String payload = objectMapper.writeValueAsString(requestBodyDto);
            RestClient restClient = new RestClient(url, payload, headers, queryParams, null, null, null);
            response = restClient.postResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
