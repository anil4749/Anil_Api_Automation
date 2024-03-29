package service.steps.flights;

import base.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.RestClient;
import dot.searchFlight.SearchFlightDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class SearchFlight extends TestBase {
    ObjectMapper objectMapper=new ObjectMapper();
    private Map<String, String> headers=new HashMap<>();
    private Map<String, String> queryParams=new HashMap<>();
    private String payload=null;
    String url;

    @Step("Search Flights")
    public Response searchFlight(SearchFlightDto requestBodyDto) {
     Response response=null;
      try {
          url = host + searchFlight;
          Map<String, String> headers=new HashMap<>();
          headers.put("Content-Type", "application/json");
          headers.put("authorization", accessToken);
          headers.put("appID", "Desktop");
          String payload=objectMapper.writeValueAsString(requestBodyDto);
          RestClient restClient=new RestClient(url,payload,headers,queryParams,null,null,null);
          response=restClient.postResponse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
