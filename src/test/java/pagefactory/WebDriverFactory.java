package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private final String browser;
    private final String remoteUrl;

    public WebDriverFactory(String browser, String remoteUrl) {

        this.browser = browser;
        this.remoteUrl = remoteUrl;
    }

    public WebDriver getWebDriver() {
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
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Bad remote url provided",e);
            }
            // http://cdin90days-sonar.westeurope.cloudapp.azure.com:4445/wd/hub
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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return driver;
    }
}
