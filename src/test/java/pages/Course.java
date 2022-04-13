package pages;

import java.time.LocalDate;

public class Course {
    private final String name;
    private final LocalDate startDate;

    public Course(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public static Course max(Course x, Course y) {
        return x.getStartDate().isAfter(y.getStartDate()) ? x : y;
    }

    public static Course min(Course x, Course y) {
        return x.getStartDate().isBefore(y.getStartDate()) ? x : y;
    }
}
