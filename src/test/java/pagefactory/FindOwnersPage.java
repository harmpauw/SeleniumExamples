package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindOwnersPage {
    private final WebDriver driver;
    @FindBy(name = "lastName")
    private WebElement searchBox;

    @FindBy(css = "#lastName span.help-inline > div > p")
    private WebElement helpMessage;

    public FindOwnersPage(WebDriver driver) {
        this.driver = driver;
    }

    public OwnerDetailsPage searchForOneOwner(String lastName) {
        fillinForm(lastName);
        return PageFactory.initElements(driver,OwnerDetailsPage.class);
    }

    public FindOwnersResultPage searchForMultipleOwners(String lastName) {
        fillinForm(lastName);
        return PageFactory.initElements(driver, FindOwnersResultPage.class);
    }

    public FindOwnersPage searchForNonExistingOwner(String lastName) {
        fillinForm(lastName);
        return this;
    }

    public String getHelpMessage() {
        return helpMessage.getText();
    }

    private void fillinForm(String lastName) {
        searchBox.sendKeys(lastName);
        searchBox.submit();
    }
}
