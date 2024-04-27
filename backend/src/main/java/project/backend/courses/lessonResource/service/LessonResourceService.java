package project.backend.courses.lessonResource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.lessonResource.model.LessonResource;
import project.backend.courses.lessonResource.repository.LessonResourceRepository;
import project.backend.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class LessonResourceService {

    private final LessonResourceRepository lessonResourceRepository;


    public LessonResource getLessonResource(Long lessonResourceId) {
        return lessonResourceRepository.findById(lessonResourceId).orElseThrow(() -> new ResourceNotFoundException("LessonResource not found with id [%s] ".formatted(lessonResourceId)));
    }

    public LessonResource createLessonResource(LessonResource lessonResource) {
        return lessonResourceRepository.save(lessonResource);
    }

    public LessonResource updateLessonResource(Long id, LessonResource lessonResource) {
        LessonResource updatedLessonResource = lessonResourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LessonResource not found with id [%s] ".formatted(id)));

        if(lessonResource.getTitle() != null)
            updatedLessonResource.setTitle(lessonResource.getTitle());

        if(lessonResource.getDescription() != null)
            updatedLessonResource.setDescription(lessonResource.getDescription());

        if(lessonResource.getDownloadUrl() != null)
            updatedLessonResource.setDownloadUrl(lessonResource.getDownloadUrl());


        return lessonResourceRepository.save(updatedLessonResource);
    }

    public void deleteLessonResource(Long lessonResourceId) {
        lessonResourceRepository.deleteById(lessonResourceId);
    }

}
