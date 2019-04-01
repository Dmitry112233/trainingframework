import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class UsersApiTests {

    @Test
    public void doRegisterTest() {
        String randomStr = RandomStringUtils.randomAlphabetic(7);
        String email = randomStr + "@mail.com";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", randomStr)
                .put("password", randomStr)
                .put("email", email);

        Response response = RestAssured.given().header("Content-Type", "application/json").
                body(requestBody.toString()).post("http://users.bugred.ru/tasks/rest/doregister");

        assertThat(response.path("name"), is(equalTo(randomStr)));
        assertThat(response.path("email"), is(equalTo(email)));
        assertThat(response.getStatusCode(), is(equalTo(200)));
    }
}
