package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.model.Course;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.lesson.repository.LessonRepository;
import project.backend.courses.lessonResource.model.LessonResource;
import project.backend.courses.lessonResource.service.LessonResourceService;
import project.backend.courses.utils.file.response.FileResponse;
import project.backend.courses.utils.file.service.FileService;
import project.backend.exception.types.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonResourceService lessonResourceService;
    private final FileService fileService;
    private final LessonDTOMapper lessonDTOMapper;

    @Override
    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLesson(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id [%s] ".formatted(lessonId)));
    }

    @Override
    public Lesson createLesson(LessonDTO lesson) {

        return lessonRepository.save(new Lesson(
                lesson.title(),
                lesson.description(),
                lesson.content(),
                lesson.lessonNumber(),
                lesson.videoUrl()
        ));
    }

    public LessonDTO updateLesson(Long lessonId, LessonDTO lesson) {
        Lesson lessonToUpdate = getLesson(lessonId);
        if (lesson.title() != null)
            lessonToUpdate.setTitle(lesson.title());
        if (lesson.description() != null)
            lessonToUpdate.setDescription(lesson.description());
        if (lesson.content() != null)
            lessonToUpdate.setContent(lesson.content());
        if (lesson.lessonNumber() != null)
            lessonToUpdate.setLessonNumber(lesson.lessonNumber());
        if (lesson.videoUrl() != null)
            lessonToUpdate.setVideoUrl(lesson.videoUrl());
        return lessonDTOMapper.toDTO(lessonRepository.save(lessonToUpdate));
    }

    public void deleteLesson(Long lessonId) {
        Lesson lesson = getLesson(lessonId);
        if (lesson.getVideoUrl() != null) {
            fileService.deleteFile(lesson.getVideoUrl());
        }
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


    @Override
    public String getSignedUrlForDownloadLessonVideo(Long lessonId) {
        Lesson lesson = getLesson(lessonId);
        return fileService.generateDownloadUrl(lesson.getVideoUrl(), "video/mp4");
    }

    @Override
    public FileResponse getSignedUrlForUploadLessonVideo(Long lessonId) {
        Lesson lesson = getLesson(lessonId);

        // if video already exists in S3, delete it
        if (lesson.getVideoUrl() != null) {
            fileService.deleteFile(lesson.getVideoUrl());
        }

        Course course = lesson.getCourse();

        String fileName = "private/courses/" + course.getId() + "/lessons/" + UUID.randomUUID().toString();
        System.out.println(fileName);

        lesson.setVideoUrl(fileName);
        lessonRepository.save(lesson);

        String url = fileService.generateUploadUrl(fileName, "video/mp4");

        return new FileResponse(url);
    }

    @Override
    public void deleteVideoFromLesson(Long lessonId) {
        Lesson lesson = getLesson(lessonId);
        if (lesson.getVideoUrl() != null) {
            fileService.deleteFile(lesson.getVideoUrl());
            lesson.setVideoUrl(null);
            lessonRepository.save(lesson);
        }
    }

}
