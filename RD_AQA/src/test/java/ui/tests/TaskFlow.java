package ui.tests;

import api.tests.CreateUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.WebDriverRunner.url;


public class TaskFlow {

    static final String TEST_TASK_NAME = "Awesome Test Task";
    static final String TEST_TASK_DESCRIPTION = "Description of test task";
    static final String TEST_TASK_COMMENT = "Task is tested";

    @Test
    public static void LogIn() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        // LogIn part

        Selenide.open("http://localhost/login");

        System.out.println("OPEN LOGIN PAGE");

        CreateUser cu = new CreateUser();

        Selenide.$("#form-username").setValue(cu.getUser());
        Selenide.$("#form-password").setValue(cu.getPass());
        Selenide.$("button.btn.btn-blue").click();

        Assert.assertEquals(url().contains("/dashboard"), true, "Dashboard page is not opened");

        // Dashboard part

        System.out.println("OPEN DASHBOARD PAGE");

        // Click on created Project
        Selenide.$(byText(cu.getProjectName())).shouldBe(Condition.visible).click();
        makePause();

        // Click on Backlog add task icon
        WebElement backlogDropdown = Selenide.$(By.xpath("//a[contains(text(), 'Backlog')]/ancestor::div[contains(@class, 'board-column-expanded')]")).shouldBe(Condition.visible);
        WebElement addIcon = backlogDropdown.findElement(By.cssSelector(".board-add-icon"));
        addIcon.click();
        makePause();

        // Enter description of task in modal window
        Selenide.$("[name='title']").val(TEST_TASK_NAME);
        Selenide.$("[name='description']").val(TEST_TASK_DESCRIPTION);
        Selenide.$("[title='Assign to me']").click(); // assign task to user
        Selenide.$("[type='submit']").click();
        makePause();

        // Click on created task
        Selenide.$(byText(TEST_TASK_NAME)).shouldBe(Condition.visible).click();
        makePause();

        // Ready
        moveTaskStatus("Ready");
        Selenide.$(byText("Start now")).click();// start time
        makePause();

        // Work in progress
        moveTaskStatus("Work in progress");
        Selenide.$(byText("Add a comment")).shouldBe(Condition.visible).click();
        makePause();
        Selenide.$("#modal-box textarea[name='comment']").val(TEST_TASK_COMMENT);
        makePause();
        Selenide.$x("//div[@id='modal-box']//div[@class='form-actions']//button[text()='Save']").shouldBe(Condition.visible).click();
        makePause();

        // Done
        moveTaskStatus("Done");

        // Close task
        Selenide.$(byText("Close this task")).shouldBe(Condition.visible).click();
        makePause();
        Selenide.$("#modal-box button#modal-confirm-button").click();
        makePause();

        // Back to main Dashboard page
        Selenide.$(".logo a").click();
        makePause();
        Selenide.$(byText("My tasks")).shouldBe(Condition.visible).click();
        makePause();

        // Check is not any task assigned to user
        String expectedText = "There is nothing assigned to you.";
        Selenide.$("p.alert").shouldHave(Condition.exactText(expectedText));

        // Well Done!
        Selenide.closeWebDriver();
    }

    private static void makePause() {
        Selenide.sleep(1000);
    }

    private static void moveTaskStatus(String status) {
        Selenide.$(byText("Move position")).shouldBe(Condition.visible).click();
        makePause();

        Selenide.$x("//label[contains(text(), 'Column')]/following-sibling::select")
                .selectOption(status);
        Selenide.$x("//div[@id='modal-box']//div[@class='form-actions']//button[text()='Save']").shouldBe(Condition.visible).click();
        makePause();
    }
}
