package bdd;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pagefactory.FindOwnersPage;
import pagefactory.OwnerDetailsPage;
import pagefactory.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerSteps {

    WebDriver driver;
    private FindOwnersPage page;
    private String lastName;

    public OwnerSteps() {
        driver = new WebDriverFactory("firefox",null).getWebDriver();
    }

    @Given("^There's an owner \"([^\"]*)\"$")
    public void there_s_an_owner(String name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String lastName) throws Throwable {
        this.lastName = lastName;
        driver.get("http://localhost:8080/owners/find");
        page = PageFactory.initElements(driver, FindOwnersPage.class);
    }

    @Then("^I should get the details of \"([^\"]*)\"$")
    public void i_should_get_the_details_of(String name) throws Throwable {
        OwnerDetailsPage details = page.searchForOneOwner(lastName);
        assertEquals(name,details.getName());
    }

    @Before
    public void setup() {
        driver = new WebDriverFactory("firefox", null).getWebDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
