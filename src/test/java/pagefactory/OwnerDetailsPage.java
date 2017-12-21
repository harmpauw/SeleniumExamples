package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OwnerDetailsPage {

    private final WebDriver driver;

    @FindBy(css = "table.table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > b:nth-child(1)")
    private WebElement name;

    public OwnerDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        return name.getText();
    }
}
