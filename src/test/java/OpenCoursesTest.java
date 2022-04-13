import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pages.CoursePage;

@Slf4j
public class OpenCoursesTest extends BaseTest {

    @Test
    public void openCoursesPositiveTest() throws InterruptedException {
        CoursePage otusPage = new CoursePage(driver);

        otusPage.openCoursePage()
                .clickCourseWithName("C++")
                .openCoursePage()
                .clickCourseWithMinStartDate()
                .openCoursePage()
                .clickCourseWithMaxStartDate();
    }
}