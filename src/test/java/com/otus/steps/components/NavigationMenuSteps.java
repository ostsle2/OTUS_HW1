package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.NavigationMenuComponent;
import com.otus.dobj.Category;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ru.Если;

import java.util.List;
import java.util.Map;

public class NavigationMenuSteps {

    @DataTableType
    public Category categories(Map<String, String> entry) {
        return new Category(
                entry.get("name"),
                entry.get("header"));
    }

    @Inject
    private NavigationMenuComponent navigationMenuComponent;

    @Если("Кликнуть на категорию курса {string}")
    public void clickNavMenuItem(String itemName) {
        navigationMenuComponent.clickNavItem(itemName);
    }

    @Если("Кликнуть на курс")
    public void testClickCourse(List<Category> categories) {
        for(Category category : categories) {
            navigationMenuComponent.clickNavItem(category.getName());
        }
    }

    @Если("^В контекстном меню (Курсы|События|Преподавателям|О нас) клинуть на поле (Подготовительные курсы)$")
    public void clickToCourseInDropdown(String contextName, String courseName) {
        navigationMenuComponent.clickToElementInDropdown(contextName, courseName);
    }

}
