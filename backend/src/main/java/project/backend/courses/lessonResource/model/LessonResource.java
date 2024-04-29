package project.backend.courses.lessonResource.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LessonResource {
    @SequenceGenerator(
            name = "lesson_resource_sequence",
            sequenceName = "lesson_resource_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lesson_resource_sequence"
    )
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private URL downloadUrl;


}
