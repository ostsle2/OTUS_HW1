import annotations.Driver;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import runner.pages.CoursePage;
import runner.MainPage;
import runner.pages.PageFactory;
import runner.MyRunner;

@Slf4j
@RunWith(MyRunner.class)
public class OpenCoursesTest {

    @Driver
    public EventFiringWebDriver driver;

    @Inject
    private PageFactory pageFactory;

    @Test
    public void openCoursesByName() {
        MainPage mainPage = pageFactory.createMainPage(driver);
        mainPage.open();

        CoursePage otusPage = pageFactory.createCoursePage(driver);
        otusPage
                .clickCourseWithName("Специализация Administrator Linux")
                .checkTitle("Специализация Administrator Linux");
    }

    @Test
    public void openCoursesWithMaxStartDate() {
        MainPage mainPage = pageFactory.createMainPage(driver);
        mainPage.open();

        CoursePage otusPage = pageFactory.createCoursePage(driver);
        otusPage
                .clickCourseWithMaxStartDate();
    }

    @Test
    public void openCoursesWithMinStartDate() {
        MainPage mainPage = pageFactory.createMainPage(driver);
        mainPage.open();

        CoursePage otusPage = pageFactory.createCoursePage(driver);
        otusPage
                .clickCourseWithMinStartDate()
                .checkTitle("Специализация PHP Developer");
    }
}