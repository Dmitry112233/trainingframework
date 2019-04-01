package utils;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;

@Log4j
public class RestApiUtil {

    Response response;

    public Response getResponse() {
        return response;
    }

    public Response sendRequest(String url, JSONObject jsonObject) {
        response = RestAssured.given().header("Content-Type", "application/json").
                body(jsonObject.toString()).post(url);
        if (response.path("message") != null) {
            log.info(response.path("message").toString());
        }
        return response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
