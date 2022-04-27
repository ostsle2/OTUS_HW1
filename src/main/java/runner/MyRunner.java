package runner;

import annotations.Driver;
import driver.WebDriverFactory;
import listeners.MouseListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyRunner extends Runner {

    private final Class testClass;

    public MyRunner(Class testClass) {
        super();
        this.testClass = testClass;
    }

    private EventFiringWebDriver driver = null;
    private WebDriverWait wait = null;

    public void setUp(Object testClass) throws Exception {
        log.info("setUp started");
        driver = new WebDriverFactory().getDriver();
        driver.register(new MouseListener());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Драйвер для браузера запущен, установлено неявное ожидание = {} сек", 5);
        wait = new WebDriverWait(driver, 5);
        setDriver(testClass);
        log.info("setUp finished");
    }

    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public Description getDescription() {
        return Description
                .createTestDescription(testClass, "My runner description");
    }

    @Override
    public void run(RunNotifier notifier) {
        log.info("running the tests from MyRunner: " + testClass);
        try {
            Object testObject = testClass.getDeclaredConstructor().newInstance();
            for (Method method : testClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    setUp(testObject);
                    notifier.fireTestStarted(Description
                            .createTestDescription(testClass, method.getName()));
                    method.invoke(testObject);
                    notifier.fireTestFinished(Description
                            .createTestDescription(testClass, method.getName()));
                    cleanUp();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Field> getAnnotatedFields(Class<? extends Annotation> annotation, Class<?> testClass) {
        Set<Field> set = new HashSet<>();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    set.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }
        return set;
    }

    public void setDriver(Object testClass) {
        Set<Field> fields = getAnnotatedFields(Driver.class, testClass.getClass());
        for (Field field : fields) {
            if (field.getType().getName().equals(EventFiringWebDriver.class.getName())) {
                AccessController.doPrivileged((PrivilegedAction<Void>)
                        () -> {
                            try {
                                field.setAccessible(true);
                                field.set(testClass, driver);
                            } catch (IllegalAccessException e) {
                                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                            }
                            return null;
                        }
                );
            }
        }
    }
}