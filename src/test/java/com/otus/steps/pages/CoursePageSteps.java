package com.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Тогда;
import pages.CoursePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CoursePageSteps {
    @Inject
    private CoursePage coursePage;

    @Если("Кликнуть на курс с названием {string}")
    public void clickOnCourseName(String courseName) {
        coursePage.clickCourseWithName(courseName);
    }

    @Тогда("Кликнуть на курс, который начинается позже всего$")
    public void openCoursesWithMaxStartDate() {
        coursePage.clickCourseWithMaxStartDate();
    }

    @Тогда("Кликнуть на курс, который начинается раньше всего$")
    public void openCoursesWithMinStartDate() {
        coursePage.clickCourseWithMinStartDate();
    }

    @Тогда("Заголовок соответствует названию курса {string}")
    public void checkTitle(String courseName) {
        coursePage.checkTitle(courseName.toLowerCase(Locale.ROOT));
    }

    @ParameterType("\\d{2}\\.\\d{2}\\.\\d{4}")
    public LocalDate mydate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Если("Кликнуть на курс который начинается {mydate}")
    public void clickCourseByDate(LocalDate date) {
        coursePage.clickCourseByDate(date);
    }

    @Затем("Кликнуть на курс который начинается после {mydate}")
    public void clickCourseAfterDate(LocalDate date) {
        coursePage.clickCourseAfterDate(date);
    }
}
