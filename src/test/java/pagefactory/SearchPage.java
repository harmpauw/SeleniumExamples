package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class SearchPage {
    final private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(className = "row")
    private List<WebElement> rows;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://google.nl");
    }

    public SearchResultPage searchFor(String s) throws InterruptedException {
        searchInput.sendKeys(s);
        Action action = new Actions(driver).doubleClick(searchInput).build();
        action.perform();
        searchInput.submit();
        return PageFactory.initElements(driver, SearchResultPage.class);
    }
}
