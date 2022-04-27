package pages;

import annotations.UrlPrefix;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@UrlPrefix("/")
public class CoursePage extends BasePage {
    protected final String BASE_URL = "https://otus.ru";


    public CoursePage(EventFiringWebDriver driver) {
        super(driver);
    }

    public CoursePage openCoursePage() {
        driver.get(BASE_URL);
        return this;
    }

    private List<Course> parseCourses() {
        List<Course> courseBlocks = new ArrayList<>();
        List<WebElement> courseBlockWebElements = driver.findElements((By.xpath("//div[@class = 'lessons__new-item-container' and not(descendant::div[contains(text(),'О дате старта будет объявлено позже') or contains(text(), 'В ')])]")));
        for (WebElement courseBlockWebElem : courseBlockWebElements) {
            String name = courseBlockWebElem.findElement((By.xpath(".//div[contains(@class, 'lessons__new-item-title')]"))).getText().trim();
            String startDateText = courseBlockWebElem.findElement((By.xpath(".//div[@class = 'lessons__new-item-start'] | .//div[@class = 'lessons__new-item-courses']//following-sibling::div[@class = 'lessons__new-item-time']"))).getText().trim();
            LocalDate startDate = parseStringToDate(startDateText);
            Course courseBlock = new Course(name, startDate);
            courseBlocks.add(courseBlock);
        }
        return courseBlocks;
    }

    private boolean isDateWithYear(String date) {
        String nextYear = Integer.toString(LocalDate.now().plusYears(1).getYear());
        return date.contains(nextYear);
    }

    private LocalDate parseStringToDate(String date) {
        String newDate = date.trim();
        String currentYear = Integer.toString(LocalDate.now().getYear());
        boolean hasPrefix = newDate.contains("С ");
        newDate = hasPrefix ? newDate.substring(2) : newDate;

        if (isDateWithYear(date)) {
            newDate = newDate.substring(0, newDate.indexOf(" года"));
        } else {
            newDate = hasPrefix ? newDate : newDate.substring(0, newDate.indexOf(" ", newDate.indexOf(" ") + 1));
            newDate = newDate.concat(" " + currentYear);
        }

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM uuuu")
                        .withLocale(new Locale("ru"));
        return LocalDate.parse(newDate, dateTimeFormatter);
    }


    private WebElement searchCourseByName(String courseName) {
        WebElement courseWebElement = null;
        try {
            List<WebElement> courseNames = driver.findElements((By.xpath("//div[@class = 'lessons']/a")));
            for (WebElement course : courseNames) {
                if (course.getText().contains(courseName)) {
                    courseWebElement = course;
                    break;
                }
            }
            return courseWebElement;
        } catch (Exception e) {
            throw new RuntimeException("Unable to find course");
        }
    }

    private String searchCourseBySortDate(boolean isMin) {
        Course courseBlock = parseCourses().stream().reduce(isMin ? Course::min : Course::max).orElse(null);
        assert courseBlock != null;
        log.info("Название курса {}", courseBlock.getName());
        return courseBlock.getName();
    }


    public CoursePage clickCourseWithName(String courseName) {
        Actions actions = new Actions(driver);
        if (courseName != null) {
            log.info("Клик по курсу {}", courseName);
            WebElement course = searchCourseByName(courseName);
            System.out.println(course.getText());
            System.out.println(course);
            actions.moveToElement(course);
            course.click();
        }
        return this;
    }


    public CoursePage clickCourseWithMaxStartDate() {
        Actions actions = new Actions(driver);
        WebElement course = searchCourseByName(searchCourseBySortDate(false));
        actions.moveToElement(course);
        course.click();
        return this;
    }

    public CoursePage clickCourseWithMinStartDate() {
        Actions actions = new Actions(driver);
        WebElement course = searchCourseByName(searchCourseBySortDate(true));
        actions.moveToElement(course);
        course.click();
        return this;
    }
}
