package ui.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;



public class LogInPage {

    private final SelenideElement userNameField = $("#form-username");
    private final SelenideElement passwordField = $("#form-password");
    private final SelenideElement signInButton =  $("button[type='submit']");




    @Step("The user logs in with creds [{0}, {1}]")
    public DashboardPage logIn(String userName, String password) {
        getUserNameField().shouldBe(Condition.visible).sendKeys(userName);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInButton().shouldBe(Condition.visible).click();
        return new DashboardPage();
    }

    public SelenideElement getUserNameField() {

        return userNameField;
    }

    public SelenideElement getPasswordField() {

        return passwordField;
    }

    public SelenideElement getSignInButton() {

        return signInButton;
    }

    public void open() {
    }
}

