package ValidateResponse;


import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class CommanValidation {

    @Step("Verify Status Code ")
    public static void verityStatusCode(int actualStatusCode, int expectedStatusCode, SoftAssert softAssert, String massage){
        softAssert.assertEquals(actualStatusCode,expectedStatusCode,massage);
    }
    public static void validateResponse(Response response,Map<String,String> expected,SoftAssert softAssert){
        JsonPath path= response.jsonPath();
        for (Map.Entry<String, String> pair : expected.entrySet()) {
            softAssert.assertEquals(path.get(pair.getKey()),pair.getValue(),"PATH "+pair.getKey()+" Expected = "+pair.getValue()+",Actual = "+path.get(pair.getKey()));
        }
    }

    public static String getValueAsString(Response response,String jpath){
        JsonPath path= response.jsonPath();
        String value = null;
        try {
            value= path.getString(jpath);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return value;
    }

}
