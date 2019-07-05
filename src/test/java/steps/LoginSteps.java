package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before()
    public void setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Given("^I access webdriverunivsity\\.com$")
    public void i_access_webdriverunivsity_com() throws Throwable {
        driver.get("http://www.webdriveruniversity.com");
    }

    @When("^I click on the login portal button$")
    public void i_click_on_the_login_portal_button() throws Throwable {
        wait = new WebDriverWait(driver, 5);
        WebElement loginPortalButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-portal")));
        loginPortalButton.click();
    }

    @When("^I enter a username$")
    public void i_enter_a_username() throws Throwable {
        // Store the current window handle
        String winHandlerBefore = driver.getWindowHandle();

        for (String windowHandle: driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        wait = new WebDriverWait(driver, 5);
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text")));
        usernameInput.sendKeys("webdriver");
    }

    @When("^I enter a \"([^\"]*)\" password$")
    public void i_enter_a_password(String password) throws Throwable {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("^I click on the login button$")
    public void i_click_on_the_login_button() throws Throwable {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("^I should be presented with a succesfful validation alert$")
    public void i_should_be_presented_with_a_succesfful_validation_alert() throws Throwable {
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "validation succeeded");
    }

    @Then("^I should be presented with a unsuccesfful validation alert$")
    public void i_should_be_presented_with_a_unsuccesfful_validation_alert() throws Throwable {
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "validation failed");
    }

}
