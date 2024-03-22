package project.backend.courses.course;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.backend.courses.category.Category;
import project.backend.courses.language.Language;
import project.backend.courses.lesson.Lesson;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;


    @OneToMany
    private List<Category> categories;

    @ManyToOne
    private Language language;

    private Duration totalDuration;

    @OneToMany
    private List<Lesson> lessons;
    private int enrollmentCount;
    private int durationInMinutes;
    private CourseState courseState = CourseState.CREATING;


}
