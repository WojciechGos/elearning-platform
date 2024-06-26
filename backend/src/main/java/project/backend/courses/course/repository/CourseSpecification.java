package project.backend.courses.course.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.model.TargetAudience;

import java.util.List;
import java.util.stream.Collectors;

public class CourseSpecification {

    public static Specification<Course> hasCourseState(List<CourseState> statuses) {
        return (course, cq, cb) -> {
            if (statuses == null || statuses.isEmpty()) {
                return null;
            } else {
                return course.get("courseState").in(statuses);
            }
        };
    }
    public static Specification<Course> hasKeyword(String keyword) {
        return (course, cq, cb) -> {
            if (keyword == null)
                return null;
            if(keyword.isEmpty() || keyword.isBlank())
                return null;

            return cb.like(cb.lower(course.get("title")), "%" + keyword.toLowerCase() + "%");
        };
    }

    public static Specification<Course> hasCategory(List<String> categories) {
        return (course, cq, cb) -> {
            if (categories == null)
                return null;
            if (categories.isEmpty())
                return null;
            Predicate categories1 = course.get("categories").get("name").in(categories);
            return categories1;
        };
    }

    public static Specification<Course> priceBetween(Double minPrice, Double maxPrice) {
        return (course, cq, cb) -> {
            if (minPrice == null && maxPrice == null) return null;
            if (minPrice == null) return cb.lessThanOrEqualTo(course.get("price"), maxPrice);
            if (maxPrice == null) return cb.greaterThanOrEqualTo(course.get("price"), minPrice);
            return cb.between(course.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<Course> minRating(Double minRating) {
        return (course, cq, cb) -> minRating == null ? null : cb.greaterThanOrEqualTo(course.get("rating"), minRating);
    }

    public static Specification<Course> hasTargetAudience(List<String> targetAudiences) {
        return (course, cq, cb) -> {
            if (targetAudiences == null) {
                return null;
            } else {
                List<TargetAudience> targetAudienceList = targetAudiences.stream()
                        .map(TargetAudience::fromString)
                        .collect(Collectors.toList());
                return course.get("targetAudience").in(targetAudienceList);
            }
        };
    }
    public static Specification<Course> hasLanguage(List<String> languages) {
        return (course, cq, cb) -> languages == null ? null : course.get("language").get("name").in(languages);
    }
}