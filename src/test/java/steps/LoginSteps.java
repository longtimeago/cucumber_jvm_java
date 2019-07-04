package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before()
    public void setUp() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Given("^User navigates to stackoverflow website$")
    public void user_navigates_to_stackoverflow_website() throws Throwable {
        driver.get("https://stackoverflow.com/");
    }

    @And("^User clicks on the login button on homepage$")
    public void user_clicks_on_the_login_button_on_homepage() throws Throwable {
        WebElement logInLink = driver.findElement(By.xpath("//a[contains(text(), 'Log in')]"));
        logInLink.click();
    }

    @And("^User enters a valid username$")
    public void user_enters_a_valid_username() throws Throwable {
        wait = new WebDriverWait(driver, 5);
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("webdriveruniversity2@mail.com");
    }

    @And("^User enters a valid password$")
    public void user_enters_a_valid_password() throws Throwable {
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Yellow321!");
    }

    @When("^User clicks on the login button$")
    public void user_clicks_on_the_login_button() throws Throwable {
        WebElement logInButton = driver.findElement(By.id("submit-button"));
        logInButton.click();
    }

    @Then("^User should be taken to the successful login page$")
    public void user_should_be_taken_to_the_successful_login_page() throws Throwable {
        wait = new WebDriverWait(driver, 5);
        By locator = By.xpath("//a[contains(text(), 'Ask Question')]");
        WebElement askQuestionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(askQuestionButton.isDisplayed());
        Assert.assertTrue(driver.getPageSource().contains("Top Questions"));
    }
}
