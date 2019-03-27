package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }


    @Step("Log in in System")
    public ProjectsPageSteps logIn(String email, String password) {
        return new ProjectsPageSteps(loginPage.typeEmail(email).typePassword(password).clickLogInButton());
    }

    @Step("Type user email")
    public LoginPageSteps typeEmail(String email) {
        loginPage.typeEmail(email);
        return this;
    }

    @Step("Type user password")
    public LoginPageSteps typePassword(String password) {
        loginPage.typePassword(password);
        return this;
    }

    @Step("Click [Log in] button")
    public ProjectsPageSteps clickLogInButton() {
        return new ProjectsPageSteps(loginPage.clickLogInButton());
    }

}
