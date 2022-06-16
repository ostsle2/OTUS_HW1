package waiters;

import org.openqa.selenium.WebDriver;

/**
 * Набор стандартных ожиданий.
 *
 * @author Pavel Balahonov <p.balahonov@corp.mail.ru>
 */
public class StandartWaiter {

    private WebDriver driver = null;

    public StandartWaiter(WebDriver driver) {
        this.driver = driver;
    }

}
