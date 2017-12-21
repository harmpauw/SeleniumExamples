import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageObjectTest {

    private static WebDriver driver;

    @BeforeAll
    private static void setup() throws URISyntaxException, MalformedURLException {
        loadWebDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    private static void loadWebDriver() throws MalformedURLException {
        String browser = System.getProperty("browser","firefox");
        String remoteUrl = System.getProperty("remoteUrl");
        driver = getWebDriver(browser, remoteUrl);
    }

    private static WebDriver getWebDriver(String browser, String remoteUrl) throws MalformedURLException {
        WebDriver driver;
        if (remoteUrl != null) {
            DesiredCapabilities capabilities;
            if (browser.equals("firefox")) {
                capabilities = DesiredCapabilities.firefox();
            } else if (browser.equals("chrome")) {
                capabilities = DesiredCapabilities.chrome();
            } else {
                throw new IllegalArgumentException("browser not supported");
            }
            driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } else {
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException("browser not supported");
            }
        }
        return driver;
    }

    @AfterAll
    private static void tearDown() {
        driver.quit();
    }

    @Test
    void simplePageObject() {
        simplepo.SearchPage searchPage = new simplepo.SearchPage(driver);
        simplepo.SearchResultPage results = searchPage.searchFor("selenium");
        assertEquals(28900000,results.nrResults());
    }

    @Test
    void pageFactory() throws InterruptedException {
        pagefactory.SearchPage searchPage = PageFactory.initElements(driver, pagefactory.SearchPage.class);
        pagefactory.SearchResultPage results = searchPage.searchFor("selenium");
        assertEquals(28900000,results.nrResults());
    }
}
