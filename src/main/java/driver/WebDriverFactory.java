package driver;

import com.google.inject.Inject;
import support.GuiceScoped;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory implements IDriverFactory {
    public GuiceScoped guiceScoped;

    @Inject
    public WebDriverFactory(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
    }

    @Override
    public EventFiringWebDriver getDriver() {
        switch (guiceScoped.browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new EventFiringWebDriver(new ChromeDriver());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new EventFiringWebDriver(new FirefoxDriver());
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                return new EventFiringWebDriver(new OperaDriver());
            }
            default -> {
                try {
                    throw new DriverTypeNotSupported(guiceScoped.browserName);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        }
    }
}
