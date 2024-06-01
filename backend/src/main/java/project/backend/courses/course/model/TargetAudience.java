package project.backend.courses.course.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TargetAudience {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED;

    @JsonCreator
    public static TargetAudience forValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return TargetAudience.valueOf(value.toUpperCase());
    }
}
