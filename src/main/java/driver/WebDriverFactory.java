package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory implements IDriverFactory {

    private final String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

    @Override
    public EventFiringWebDriver getDriver() {
        switch (this.browserType) {
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
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        }
    }
}
