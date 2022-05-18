package runner;

import annotations.UrlPrefix;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import runner.pages.BasePage;

@UrlPrefix("/")
public class MainPage extends BasePage<MainPage> {

    @AssistedInject
    MainPage(@Assisted EventFiringWebDriver driver) {
        super(driver);
    }
}
