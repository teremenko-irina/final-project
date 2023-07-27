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
import ui.pageobjects.ProjectsPage;

public class CreateProjectTest {

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
    public void testCreateProject() {


        LogInPage loginPage = new LogInPage();
        loginPage.open();
        DashboardPage dashboardPage = loginPage.logIn("IraTerem_admin", "QAsipsip");

        dashboardPage = new DashboardPage();
        dashboardPage.getDropdown().click();
        dashboardPage.getDropdownMenu().shouldBe(Condition.visible);
        dashboardPage.getProjectsManagementButton().click();

        ProjectsPage projectsPage = new ProjectsPage();
        projectsPage.getNewPersonalProjectButton().click();

        projectsPage.getNameProjectInput().setValue(nameProjectInput);
        projectsPage.getIdentifierProjectInput().setValue(identifierProjectInput);
        projectsPage.getSaveButton().click();

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "http://localhost/projects"
                , "The projects page is not opened");
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "http://localhost/projects"
                , "The project was not created");

    }

    private final static String nameProjectInput = "MyNextProject";
    private final static String identifierProjectInput = "MYNEXTPROJECT";
}




