import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class UsersApiTests {

    @Test(testName = "aaaaaaaaaaaaa")
    public void postRequestExampleTest() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", someRandomString)
                .put("password", someRandomString)
                .put("email", someRandomString + "@mail.com");

        Response response = RestAssured.given().header("Content-Type", "application/json").
                body(requestBody.toString()).post("http://users.bugred.ru/tasks/rest/doregister");

        int statusCode = response.getStatusCode();
        assertThat(statusCode, is(equalTo(200)));
        System.out.println(response.getBody().asString());
    }
}
