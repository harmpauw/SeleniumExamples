import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleRunner {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://google.nl");
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.name("q")).submit();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        driver.quit();
    }
}
