package com.otus.steps.pages;

import com.google.inject.Inject;
//import com.otus.driver.DriverFactory;
import io.cucumber.java.ru.Если;
import listeners.MouseListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import support.GuiceScoped;
import driver.WebDriverFactory;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.MainPage;

public class MainPageSteps {


    @Inject
    private WebDriverFactory driverFactory;
    @Inject
    private GuiceScoped guiceScoped;
    @Inject
    private MainPage mainPage;

    @Пусть("^Открыта главная страница otus в браузере (chrome|firefox|opera)$")
    public void openMainPage(String browserName) {
        guiceScoped.browserName = browserName;
        guiceScoped.driver = driverFactory.getDriver();
        mainPage.open();
    }

    @Тогда("Главная страница открыта и заголовок {string}")
    public void pageShouldBeOpened(String expectedHeader) {
        mainPage.pageHeaderShouldBeSameAs(expectedHeader);
    }


}
