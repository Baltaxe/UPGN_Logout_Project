package com.upgenix.pages;

import com.upgenix.utilities.ConfigurationReader;
import com.upgenix.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;


public class DashboardPage {

    static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(@href,'login')]")
    public static WebElement loginMenu;

    @FindBy(xpath = "//span[@class='oe_topbar_name']")
    private static WebElement userIDMenu;
    @FindBy(id = "login")

    private static WebElement emailBox;

    @FindBy(id = "password")
    private static WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement loginBtn;

    @FindBy(xpath = "//a[.='Log out']")
    private static WebElement logOutMenu;

    @FindBy(xpath = "//h4[@class='modal-title']")
    private static WebElement sessionEndMsg;

    public static void goTo() {
        Driver.getDriver().get(ConfigurationReader.getProperty("homepageurl"));
        loginMenu.click();
        emailBox.clear();
        passwordBox.clear();
    }

    public static void ValidCredentials(String username, String password) {

        emailBox.sendKeys("salesmanager15@info.com");
        passwordBox.sendKeys("salesmanager");
        loginBtn.click();
    }

    public static void isAtDashboardPage() {

        wait.until(ExpectedConditions.visibilityOf(userIDMenu));
        System.out.println("Dashboard Page Verified");
    }

    public static void logout() {
        userIDMenu.click();
        logOutMenu.click();
    }

    public static void isAtLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(emailBox));
        String loginPageTitle = "Login | Best solution for startups";

        Assert.assertEquals(loginPageTitle, Driver.getDriver().getTitle());
        System.out.println("Login Page Verified");
    }

    public static void sessionEndPage() {
        wait.until(ExpectedConditions.visibilityOf(sessionEndMsg));
        System.out.println(sessionEndMsg.getText());
        Driver.getDriver().navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(emailBox));
        String loginPageTitle = "Login | Best solution for startups";

        Assert.assertEquals(loginPageTitle, Driver.getDriver().getTitle());
        System.out.println("Login Page Verified");
    }

    public static void closeTab() throws InterruptedException {
        String link = "window.open('https://www.google.com','_blank');";
        ((JavascriptExecutor) Driver.getDriver()).executeScript(link);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.open('https://qa.upgenix.net/web?#menu_id=115&action=120&active_id=channel_inbox','_blank');");

        Set<String> allWindows = Driver.getDriver().getWindowHandles();
        Set<String> windows = new HashSet<>();
        String otherWindow = "";

        for (String each : allWindows) {
            Thread.sleep(1000);
            Driver.getDriver().switchTo().window(each);
            if (Driver.getDriver().getCurrentUrl().contains("upgenix")) {
                windows.add(each);
            } else {
                otherWindow = each;
            }
        }

        for (String each : windows) {
            Driver.getDriver().switchTo().window(each);
            Driver.getDriver().close();
        }
        Driver.getDriver().switchTo().window(otherWindow);
    }

}

