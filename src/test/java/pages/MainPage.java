package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@UrlPrefix("/")
public class MainPage extends BasePage<MainPage> {

    public MainPage(EventFiringWebDriver driver) {
        super(driver);
    }
}
