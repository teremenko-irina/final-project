package ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageobjects.DashboardPage;
import ui.pageobjects.LogInPage;


public class LogOutTest {

    @BeforeMethod
    public void setUp() {

        Configuration.holdBrowserOpen = true;

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");

        Configuration.browser = "chrome";
        //Configuration.browser = "firefox";
        Configuration.headless = false;
        Selenide.open("http://localhost/login");

    }

    @AfterMethod
    public void tearDown() {

    }

    @Test
    public void testLogout() {

        LogInPage loginPage = new LogInPage();
        loginPage.open();
        DashboardPage dashboardPage = loginPage.logIn("IraTerem_admin", "QAsipsip");


        dashboardPage = new DashboardPage();
        dashboardPage.getDropdown().click();
        dashboardPage.getDropdownMenu().shouldBe(Condition.visible);
        dashboardPage.getLogoutButton().click();



        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "http://localhost/login"
                , "The logout was not successful");
    }
}

