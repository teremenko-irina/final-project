package ui.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public class DashboardPage {
    private final SelenideElement title = $(".logo");
    private final SelenideElement dropdownMenu = $("#dropdown");
    private final SelenideElement logoutButton = $("#dropdown > ul > li:nth-child(6) > a");
    private final SelenideElement dropdown = $(".avatar-letter");
    private final SelenideElement projectsManagementButton = $("#dropdown > ul > li:nth-child(4) > a");


    // adding task
    private final SelenideElement tableProjectsListTitle = $(".table-list-header-menu");
    private final SelenideElement sortProjectsIcon = $(".dropdown-menu-link-icon dropdown-menu");
    private final SelenideElement submenuProject = $(".dropdown-submenu-open");
    private final SelenideElement tableProjectTitle = $(".table-list-title ");


    @Step("Select an option from the dropdown")
    public DashboardPage select(String visibleText) {
        dropdown.click();
        dropdownMenu.$x("//a[contains(text(),'" + visibleText + "')]").click();
        return this;
    }

    @Step("Logout")
    public LogInPage logout() {
        dropdown.click();
        logoutButton.click();
        return new LogInPage();
    }


    public SelenideElement getTitle() {
        return title;
    }

    public SelenideElement getDropdown() {
        return dropdown;
    }

    public SelenideElement getDropdownMenu() {
        return dropdownMenu;
    }

    public SelenideElement getLogoutButton() {
        return logoutButton;
    }

    public SelenideElement getProjectsManagementButton() {
        return projectsManagementButton;
    }


    // adding task
    @Step("Creating new task")
    public SelenideElement getTableProjectsListTitle() {
        return tableProjectsListTitle;
    }

    public SelenideElement getSortProjectsIcon() {
        return sortProjectsIcon;
    }

    public SelenideElement getSubmenuProject() {
        return submenuProject;
    }

    public SelenideElement getTableProjectTitle() {
        return tableProjectTitle;
    }


}



