package pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected EventFiringWebDriver driver;

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage waitUntilPageIsLoaded(WebDriverWait wait, String urlPath) {
        wait.until(ExpectedConditions.urlContains(urlPath));
        return this;
    }
}