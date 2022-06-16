package com.otus.components;

/**
 * Navigation menu options.
 */

public enum NavigationMenuOptionsEnum {
    КУРСЫ("Курсы"),
    СОБЫТИЯ("События"),
    ПОДГОТОВИТЕЛЬНЫЕ_КУРСЫ("Подготовительные курсы");

    private String optionName;

    NavigationMenuOptionsEnum(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }

}

