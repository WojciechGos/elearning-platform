package project.backend.courses.language.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Language {
    @SequenceGenerator(
            name = "language_sequence",
            sequenceName = "language_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "language_sequence"
    )
    private Long id;
    private String name;

    public Language(String name) {
        this.name = name;
    }
}
