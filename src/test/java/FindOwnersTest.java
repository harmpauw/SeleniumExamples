import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pagefactory.FindOwnersPage;
import pagefactory.FindOwnersResultPage;
import pagefactory.OwnerDetailsPage;
import pagefactory.WebDriverFactory;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindOwnersTest {

    WebDriver driver;
    static WebDriverFactory webDriverFactory;
    static ApplicationUrlResolver urlResolver;

    @BeforeAll
    public static void setupContext() {
        String browser = System.getProperty("browser","firefox");
        String remoteUrl = System.getProperty("remoteUrl");
        webDriverFactory = new WebDriverFactory(browser,remoteUrl);

        urlResolver = new ApplicationUrlResolver("http://localhost:8080/");
    }

    @BeforeEach
    void setup() throws MalformedURLException {
        driver = webDriverFactory.getWebDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    private FindOwnersPage gotoFindOwners() {
        driver.get(urlResolver.resolve("/owners/find"));
        return PageFactory.initElements(driver,FindOwnersPage.class);
    }

    @Test
    void showsDetailsWhenSearchingForUniqueOwner() throws InterruptedException {
        FindOwnersPage findPage = gotoFindOwners();
        OwnerDetailsPage details = findPage.searchForOneOwner("Black");

        assertEquals("Jeff Black", details.getName());

    }

    @Test
    void showsListWhenSearchingForMultipleOwners() throws InterruptedException {
        FindOwnersPage findPage = gotoFindOwners();
        FindOwnersResultPage resultsPage = findPage.searchForMultipleOwners("Davis");
        assertEquals(2,resultsPage.getNrResults(), "Number of results wrong");
    }

    @Test
    void showsAllOwnersWhenEnteringBlankLastName() throws InterruptedException {
        FindOwnersPage findPage = gotoFindOwners();
        FindOwnersResultPage resultsPage = findPage.searchForMultipleOwners("");
        assertEquals(10,resultsPage.getNrResults(), "Number of results wrong");
    }

    @Test
    void showsErrorWhenSearchingForNonexistingOwner() throws InterruptedException {
        FindOwnersPage findPage = gotoFindOwners();
        findPage = findPage.searchForNonExistingOwner("nonexisting");

        assertTrue(findPage.getHelpMessage().equals("has not been found"));
    }

}
