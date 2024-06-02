package project.backend.courses.course.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import project.backend.courses.category.model.Category;
import project.backend.courses.language.model.Language;
import project.backend.courses.lesson.model.Lesson;
import project.backend.user.User;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
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
    @NotNull(message = "Title cannot be null.")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal discountPrice;


    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(
            name="language_id",
            nullable=true
    )
    private Language language;

    private Duration totalDuration;

    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private double rating;

    @NotNull(message = "Image URL cannot be null.")
    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @OneToMany
    private List<Lesson> lessons;

    @Min(value = 0, message = "Enrollment count must be greater than 0")
    private Integer enrollmentCount;

    private CourseState courseState = CourseState.CREATING;

    private TargetAudience targetAudience;

    @ManyToOne
    private User author;

}
