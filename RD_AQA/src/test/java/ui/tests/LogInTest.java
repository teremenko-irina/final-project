package ui.tests;


import com.codeborne.selenide.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pageobjects.LogInPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;


public class LogInTest extends BaseTest {

    @Test
    @Description("User logins with invalid username")
    public static void testLogInNegative() {
        // Configure Selenide to keep the browser window open
        Configuration.holdBrowserOpen = true;

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
      //  System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");

        Configuration.browser = "chrome";

        //Configuration.browser = "firefox";

        Configuration.headless = false;


        Selenide.open("http://localhost/login");

        $("button[type='submit']").shouldBe(Condition.visible);
        SelenideElement submitButton = $("button[type='submit']");


// Negative case with invalid username and password
        $("#form-username").setValue("invalidLogin");
        $("#form-password").setValue("admin");
        submitButton.click();

        $("p.alert.alert-error").shouldBe(Condition.visible);
        $("p.alert.alert-error").shouldHave(text("Bad username or password"));

        // Refresh the page before the next negative case
        Selenide.open("http://localhost:80/login");

        $("button[type='submit']").shouldBe(Condition.visible);

        // Negative case with empty username field
        $("#form-username").setValue("");
        $("#form-password").setValue("admin");

        submitButton.click();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "http://localhost/login"
                , "The logIn was not successful");
    }

    private final static String NAME = "IraTerem_admin";
    private final static String PASS = "QAsipsip";

    @Test
    @Description("User logins with valid username")
    public static void testLogInPositive() {

        new LogInPage().logIn(NAME, PASS);

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "http://localhost/dashboard"
                , "The logIn was not successful");
    }
}


