package project.backend.courses.course.repository;

import org.springframework.data.jpa.domain.Specification;
import project.backend.courses.course.model.Course;

import java.util.List;

public class CourseSpecification {

    public static Specification<Course> hasStatus(List<String> statuses) {
        return (course, cq, cb) -> statuses == null ? null : course.get("status").in(statuses);
    }

    public static Specification<Course> hasKeyword(String keyword) {
        return (course, cq, cb) -> {
            if (keyword == null) {
                return null;
            } else {
                return cb.like(cb.lower(course.get("title")), "%" + keyword.toLowerCase() + "%");
            }
        };
    }

    public static Specification<Course> hasCategory(List<String> categories) {
        return (course, cq, cb) -> categories == null ? null : course.get("category").in(categories);
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
        return (course, cq, cb) -> targetAudiences == null ? null : course.get("targetAudience").in(targetAudiences);
    }

    public static Specification<Course> hasLanguage(List<String> languages) {
        return (course, cq, cb) -> languages == null ? null : course.get("language").in(languages);
    }
}