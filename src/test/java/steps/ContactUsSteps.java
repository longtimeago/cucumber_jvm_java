package steps;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactUsSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before()
    public void setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("^I click on the contact us button$")
    public void i_click_on_the_contact_us_button() throws Throwable {
        driver.findElements(By.id("contact-us"));
    }

    @When("^I enter a valid first name$")
    public void i_enter_a_valid_first_name() throws Throwable {
        // Store the current window handle
        @SuppressWarnings("unused")
        String winHandlerBefore = driver.getWindowHandle();

        for (String windowHandle: driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        By firstNameSelector = By.cssSelector("input[name='first_name']");
        wait = new WebDriverWait(driver, 5);
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameSelector));
        firstName.sendKeys("Tom");
    }

    @When("^I enter a valid last name$")
    public void i_enter_a_valid_last_name(DataTable table) throws Throwable {
        List<List<String>> data = table.raw();
        By lastNameSelector = By.cssSelector("input[name='first_last']");
        wait = new WebDriverWait(driver, 5);
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameSelector));
        lastName.sendKeys(data.get(1).get(0));
    }

    @When("^I enter a valid email address$")
    public void i_enter_a_valid_email_address() throws Throwable {
        By emailSelector = By.cssSelector("input[name='email']");
        wait = new WebDriverWait(driver, 5);
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailSelector));
        email.sendKeys("webdriveruniversity@outlook.com");
    }

    @When("^I enter comments$")
    public void i_enter_comments(DataTable table) throws Throwable {
        List<List<String>> data = table.raw();
        WebElement textArea = driver.findElement(By.cssSelector("textarea[name='message']"));
        textArea.sendKeys(data.get(1).get(0) + "\n");
        textArea.sendKeys(data.get(1).get(1));
    }

    @When("^I click on the submit button$")
    public void i_click_on_the_submit_button() throws Throwable {
        driver.findElement(By.cssSelector("input[value='SUBMIT']")).click();
    }

    @Then("^the information should successfully be submitted via the contact us form$")
    public void the_information_should_successfully_be_submitted_via_the_contact_us_form() throws Throwable {
        System.out.println("the information should successfully be submitted via the contact us form");
    }

    @When("^I enter a non valid first name$")
    public void i_enter_a_non_valid_first_name() throws Throwable {
        System.out.println("I enter a non valid first name");
    }

    @When("^I enter a non valid last name$")
    public void i_enter_a_non_valid_last_name() throws Throwable {
        System.out.println("I enter a non valid last name");
    }

    @When("^I enter a non valid email address$")
    public void i_enter_a_non_valid_email_address() throws Throwable {
        System.out.println("I enter a non valid email address");
    }

    @When("^I enter no comments$")
    public void i_enter_no_comments() throws Throwable {
        System.out.println("I enter no comments");
    }

    @Then("^the information should not be successfully be submitted via the contact us form$")
    public void the_information_should_not_be_successfully_be_submitted_via_the_contact_us_form() throws Throwable {
        System.out.println("the information should not be successfully be submitted via the contact us form");
    }

    @Then("^the user should also be notified of the problem$")
    public void the_user_should_also_be_notified_of_the_problem() throws Throwable {
        System.out.println("the user should also be notified of the problem");
    }

}
