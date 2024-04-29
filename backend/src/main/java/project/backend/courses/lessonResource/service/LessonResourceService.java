package project.backend.courses.lessonResource.service;

import project.backend.courses.lessonResource.model.LessonResource;

public interface LessonResourceService {
    LessonResource getLessonResource(Long lessonResourceId);

    LessonResource createLessonResource(LessonResource lessonResource);

    LessonResource updateLessonResource(Long id, LessonResource lessonResource);
    void deleteLessonResource(Long lessonResourceId);

}
