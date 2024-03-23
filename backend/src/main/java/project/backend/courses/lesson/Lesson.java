package project.backend.courses.lesson;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.backend.courses.lessonResource.LessonResource;

import java.time.Duration;
import java.util.List;

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
    @Column(columnDefinition = "TEXT")
    private String content;
    private int lessonNumber;
    private String videoUrl;
    private Duration duration;
    @OneToMany
    private List<LessonResource> lessonResources;

    public Lesson(String title, String description, String content, int lessonNumber, String videoUrl) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.lessonNumber = lessonNumber;
        this.videoUrl = videoUrl;
    }

}
