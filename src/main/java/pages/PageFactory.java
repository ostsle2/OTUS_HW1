package pages;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import javax.inject.Named;

public interface PageFactory {
    @Named("mainPage")
    MainPage createMainPage(EventFiringWebDriver driver);

    @Named("coursePage")
    CoursePage createCoursePage(EventFiringWebDriver driver);
}
