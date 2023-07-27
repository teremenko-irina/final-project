package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
       // Configuration.headless = true;

        Selenide.open("http://localhost/login");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }


}

