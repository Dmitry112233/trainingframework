import models.Company;
import models.Credential;
import models.User;
import org.testng.annotations.Test;
import utils.RestApiUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;


public class UsersApiTests {

    String email;

    @Test
    public void doRegisterTest() {
        Credential credential = new Credential();
        RestApiUtil restApiUtil = new RestApiUtil();

        Credential responseCredential = credential.getCredentialFromResponse(restApiUtil.
                sendRequest("http://users.bugred.ru/tasks/rest/doregister ", credential.createCredentialJson()));

        assertThat(responseCredential, is(equalTo(credential)));
        assertThat(200, is(equalTo(restApiUtil.getStatusCode())));

    }

    @Test
    public void createUser() {
        User user = new User();
        RestApiUtil restApiUtil = new RestApiUtil();

        User responseUser = user.getUserFromResponse(restApiUtil
                .sendRequest("http://users.bugred.ru/tasks/rest/createuser", user.createUserJson()));
        this.email = responseUser.getEmail();

        assertThat(responseUser, samePropertyValuesAs(user));
        assertThat(200, is(equalTo(restApiUtil.getStatusCode())));

    }

    @Test(dependsOnMethods = "createUser")
    public void createCompanyTest() {
        Company company = new Company(email);
        RestApiUtil restApiUtil = new RestApiUtil();
        Company responseCompany = company.getCompanyFromResponse(restApiUtil
                .sendRequest("http://users.bugred.ru/tasks/rest/createcompany", company.createCompanyJson()));

        assertThat(responseCompany, is(equalTo(company)));
        assertThat(200, is(equalTo(restApiUtil.getStatusCode())));
    }

}