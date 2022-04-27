import annotations.Driver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.CoursePage;
import pages.MainPage;
import runner.MyRunner;

@Slf4j
@RunWith(MyRunner.class)
public class OpenCoursesTest {

    @Driver
    public EventFiringWebDriver driver;

    @Test
    public void openCoursesByName() {
        new MainPage(driver).open();
        CoursePage otusPage = new CoursePage(driver);
        otusPage
                .clickCourseWithName("Специализация Administrator Linux")
                .checkTitle("Специализация Administrator Linux");
    }

    @Test
    public void openCoursesWithMaxStartDate() {
        new MainPage(driver).open();
        CoursePage otusPage = new CoursePage(driver);
        otusPage
                .clickCourseWithMaxStartDate();
    }

    @Test
    public void openCoursesWithMinStartDate() {
        new MainPage(driver).open();
        CoursePage otusPage = new CoursePage(driver);
        otusPage
                .clickCourseWithMinStartDate()
                .checkTitle("Специализация Administrator Linux");
    }
}