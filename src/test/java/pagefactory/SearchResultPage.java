package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchResultPage {
    private final WebDriver driver;

    @FindBy(id = "resultStats")
    private WebElement resultStats;

    @FindBy(css = "#search .g")
    private List<WebElement> searchResults;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public int nrResults() {
        String text = resultStats.getText();
        Pattern pattern = Pattern.compile("([\\d\\.]*) result");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? Integer.parseInt(matcher.group(1).replace(".","")) : 0;
    }

    public List<String> getResultTitles() {
        return null;
    }
}
