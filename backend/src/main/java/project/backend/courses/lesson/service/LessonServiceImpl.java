package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.courses.lesson.repository.LessonRepository;
import project.backend.courses.lessonResource.model.LessonResource;
import project.backend.courses.lessonResource.service.LessonResourceService;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl {

    private final LessonRepository lessonRepository;
    private final LessonResourceService lessonResourceService;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }


    public Lesson getLesson(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id [%s] ".formatted(lessonId)));
    }

    public Lesson createLesson(LessonRequest lesson) {

        return lessonRepository.save(new Lesson(
                lesson.title(),
                lesson.description(),
                lesson.content(),
                lesson.lessonNumber(),
                lesson.videoUrl()
        ));
    }

    public Lesson updateLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @Transactional
    public LessonResource addLessonResourceToLesson(final Long lessonId, final LessonResource lessonResource) {
        LessonResource createdLessonResource = lessonResourceService.createLessonResource(lessonResource);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id [%s] ".formatted(lessonId)));
        lesson.getLessonResources().add(createdLessonResource);
        lessonRepository.save(lesson);
        return createdLessonResource;
    }

}
