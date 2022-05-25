package pages;

import com.google.inject.Inject;
import support.GuiceScoped;

public class CategoryLessonsPage extends BasePage<CategoryLessonsPage> {

    @Inject
    public CategoryLessonsPage(GuiceScoped guiceScoped) {
        super(guiceScoped, "");
    }
}
