package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.NavigationMenuComponent;
import com.otus.components.NavigationMenuOptionsEnum;
import com.otus.dobj.Category;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ru.Если;

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

    @Если("^В контекстном меню (КУРСЫ|СОБЫТИЯ) клинуть на поле (ПОДГОТОВИТЕЛЬНЫЕ_КУРСЫ)$")
    public void clickToCourseInDropdown(NavigationMenuOptionsEnum arg1, NavigationMenuOptionsEnum arg2) {
        navigationMenuComponent.clickToElementInDropdown(arg1.getOptionName(), arg2.getOptionName());
    }

}
