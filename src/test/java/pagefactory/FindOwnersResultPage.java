package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FindOwnersResultPage {

    private final WebDriver driver;

    @FindBy(css = "#vets > tbody > tr")
    private List<WebElement> results;

    public FindOwnersResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getNrResults() {
        return results.size();
    }

    public List<Owner> getOwners() {
        //return results.stream().map()
        return null;
    }
}
