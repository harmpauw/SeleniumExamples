package simplepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPage {
    final private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://google.nl");
    }

    public SearchResultPage searchFor(String s) {
        driver.findElement(By.name("q")).sendKeys(s);
        driver.findElement(By.name("q")).submit();
        return new SearchResultPage(driver);
    }
}
