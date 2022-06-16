package pages;


import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PreparatoryCoursesPage extends BasePage<PreparatoryCoursesPage> {

    @Inject
    public PreparatoryCoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped, "");
    }

    @FindBy(xpath = ".//div[contains(@class, 'price')]")
    List<WebElement> prices;

    private static final String COURSES_BY_TEXT =
    ".//*[text()[contains(normalize-space(.),'%s')]]//ancestor::div[contains(@class, 'lessons__new-item-container')]";

    public static final Comparator<WebElement> SORT_WEB_ELEMENTS_BY_PRICE =
            Comparator.comparing(el -> Integer.parseInt(el.getText().replaceAll("\\s+₽", "")));

    public void printCheapestCourse() {
        List<WebElement> elements =
                prices.stream().sorted(PreparatoryCoursesPage.SORT_WEB_ELEMENTS_BY_PRICE).collect(Collectors.toList());
        List<WebElement> filteredElements =
                elements.stream().filter(element -> !element.getText().contains(elements.get(0).getText()))
                        .collect(Collectors.toList());
        if (filteredElements.isEmpty()) {
            log.info("Среди всех курсов нет самого дешевого");
            return;
        }
        filteredElements.forEach(webElement -> log.info("Самые дешевые курсы: "
                + guiceScoped.driver.findElement(By.xpath(String.format(COURSES_BY_TEXT, webElement.getText())))
                .getText()));
    }

    public void printMostExpensiveCourse() {
        List<WebElement> elements =
                prices.stream().sorted(PreparatoryCoursesPage.SORT_WEB_ELEMENTS_BY_PRICE).collect(Collectors.toList());
        List<WebElement> filteredElements = elements.stream()
                .filter(element -> !element.getText().contains(elements.get(elements.size() - 1).getText()))
                .collect(Collectors.toList());
        if (filteredElements.isEmpty()) {
            log.info("Среди всех курсов нет самого дорого");
            return;
        }
        filteredElements.forEach(webElement -> log.info("Самые дорогие курсы: "
                + guiceScoped.driver.findElement(By.xpath(String.format(COURSES_BY_TEXT, webElement.getText())))
                .getText()));
    }

}
