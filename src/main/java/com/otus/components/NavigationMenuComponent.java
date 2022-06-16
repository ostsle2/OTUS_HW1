package com.otus.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.PreparatoryCoursesPage;
import support.GuiceScoped;


public class NavigationMenuComponent extends BaseComponent<NavigationMenuComponent> {

    @FindBy(css = ".nav:not(.hide-xs) .course-categories__nav-box")
    private WebElement navComponent;
    @FindBy(xpath = ".//a[@title='Подготовительные курсы']")
    private WebElement preparatoryCourses;

    @Inject
    public NavigationMenuComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public PreparatoryCoursesPage clickToElementInDropdown(String contextName, String courseName) {
        Actions actions = new Actions(guiceScoped.driver);
        WebElement webContextName =
                guiceScoped.driver.findElement(By.xpath(String.format("//p[contains(text(),'%s')]", contextName)));
        WebElement webCourseName =
                guiceScoped.driver.findElement(By.xpath(String.format(".//a[@title='%s']", courseName)));
        actions.moveToElement(webContextName).click();
        actions.moveToElement(webCourseName).click();
        return new PreparatoryCoursesPage(guiceScoped);
    }
}
