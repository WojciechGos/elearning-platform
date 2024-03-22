package project.backend.courses.lesson;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Lesson {
    @SequenceGenerator(
            name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lesson_sequence"
    )
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Duration duration;


}
