package pages;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import support.GuiceScoped;

@UrlPrefix("/")
public class MainPage extends BasePage<MainPage> {

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped, "/");
    }
}
