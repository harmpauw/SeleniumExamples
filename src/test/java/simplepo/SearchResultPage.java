package simplepo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage {
    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public int nrResults() {
        WebElement resultStats = driver.findElement(By.id("resultStats"));
        String text = resultStats.getText();
        Pattern pattern = Pattern.compile("([\\d\\.]*) result");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? Integer.parseInt(matcher.group(1).replace(".","")) : 0;
    }

}

