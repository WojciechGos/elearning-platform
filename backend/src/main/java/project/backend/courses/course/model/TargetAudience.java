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

    public static TargetAudience fromString(String name) {
        for (TargetAudience targetAudience : TargetAudience.values()) {
            if (targetAudience.name().equalsIgnoreCase(name)) {
                return targetAudience;
            }
        }
        throw new IllegalArgumentException("No enum constant " + TargetAudience.class.getCanonicalName() + "." + name);
    }
}
