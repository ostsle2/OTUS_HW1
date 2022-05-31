package pages;

import annotations.UrlPrefix;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasePage<T> {

    protected GuiceScoped guiceScoped;
    private String path;

    public BasePage(GuiceScoped guiceScoped, String path) {
        this.guiceScoped = guiceScoped;
        this.path = path;
        PageFactory.initElements(guiceScoped.driver, this);
    }

    @FindBy(tagName = "h1")
    private WebElement header;

    private String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
    }

    private String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }

        return "";
    }

    public T open() {
        guiceScoped.driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public <Page> Page page(final Class<Page> clazz) {
        try {
            Constructor<Page> constructor = clazz.getConstructor(WebDriver.class);

            return convertInstanceOfObject(constructor.newInstance(guiceScoped.driver), clazz);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> T convertInstanceOfObject(final Object o, final Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void checkTitle(String expectedValue) {
        String actualTitle = this.getPageTitle().toLowerCase(Locale.ROOT);
        Assertions.assertTrue(actualTitle.contains(expectedValue));
    }

    public String getPageTitle() {
        return guiceScoped.driver.getTitle();
    }

    public T pageHeaderShouldBeSameAs(String header) {
        assertThat(this.header.getText())
                .as("Заголовок на странице %s: ", this.header.getText())
                .isEqualTo(header);

        return (T) this;
    }
}