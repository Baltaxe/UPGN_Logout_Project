package com.upgenix.stepDefs;

import com.upgenix.pages.DashboardPage;
import com.upgenix.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs {
    DashboardPage dashboardPage = new DashboardPage();

    @Given("user is on the dashboard page")
    public void userIsOnTheDashboardPage() {
        DashboardPage.goTo();
        DashboardPage.ValidCredentials("salesmanager15@info.com", "salesmanager");
        DashboardPage.isAtDashboardPage();
    }

    @When("user clicks the logout button")
    public void userClicksTheLogoutButton() {
        DashboardPage.logout();

    }

    @Then("user sees the login page")
    public void userSeesTheLoginPage() {
        DashboardPage.isAtLoginPage();
    }

    @And("user clicks the <Step Back_Previous Page> button")
    public void userClicksTheStepBackPreviousPageButton() {
        Driver.getDriver().navigate().back();
    }

    @And("user sees the <session expired message>")
    public void userSeesTheSessionExpiredMessage() {
        DashboardPage.sessionEndPage();
    }

    @When("user clicks the tab close button")
    public void userClicksTheTabCloseButton() throws InterruptedException {
        DashboardPage.closeTab();
    }
}



