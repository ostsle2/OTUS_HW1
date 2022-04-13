import driver.*;
import listeners.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BaseTest {

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new WebDriverFactory().getDriver();
        driver.register(new MouseListener());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Драйвер для браузера запущен, установлено неявное ожидание = {} сек", 5);
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }

}
