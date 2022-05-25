package com.otus.components;

import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;

public abstract class BaseComponent<T> {

    protected GuiceScoped guiceScoped;

    public BaseComponent(GuiceScoped guiceScoped) {
        PageFactory.initElements(guiceScoped.driver, this);
        this.guiceScoped = guiceScoped;
    }

}