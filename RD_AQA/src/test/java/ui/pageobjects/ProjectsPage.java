package ui.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Describe;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage {

    private final SelenideElement newPersonalProjectButton = $(By.xpath("/html/body/section/div[1]/ul/li/a"));
    private final SelenideElement nameProjectInput = $("#form-name");
    private final SelenideElement identifierProjectInput = $("#form-identifier");
    private final SelenideElement SaveButton = $("#project-creation-form > div.js-submit-buttons-rendered > div > button");
    private final  SelenideElement creationFormModalWindow = $("#project-creation-form");



    public SelenideElement getNewPersonalProjectButton() {

        return newPersonalProjectButton;
    }
    public SelenideElement getNameProjectInput() {
        return nameProjectInput;
    }
    public SelenideElement getSaveButton() {
        return SaveButton;
    }
    public SelenideElement getIdentifierProjectInput() {
        return identifierProjectInput;
    }
    public SelenideElement getCreationFormModalWindow() {
        return creationFormModalWindow;
    }
}
