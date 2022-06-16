package com.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Затем;
import pages.PreparatoryCoursesPage;

public class PreparatoryPageSteps {
    @Inject
    private PreparatoryCoursesPage preparatoryCoursesPage;

    @Затем("Найти самый дорогой курс")
    public void selectMaxCourse() {
        preparatoryCoursesPage.printMostExpensiveCourse();
    }

    @Затем("Найти самый дешевый курс")
    public void selectMinCourse() {
        preparatoryCoursesPage.printCheapestCourse();
    }
}
