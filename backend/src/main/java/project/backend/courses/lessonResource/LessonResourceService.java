package project.backend.courses.lessonResource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonResourceService {

    private final LessonResourceRepository lessonResourceRepository;

    public List<LessonResource> getLessonResources() {
        return lessonResourceRepository.findAll();
    }

    public LessonResource getLessonResource(Long lessonResourceId) {
        return lessonResourceRepository.findById(lessonResourceId).orElseThrow(() -> new ResourceNotFoundException("LessonResource not found with id [%s] ".formatted(lessonResourceId)));
    }

    public LessonResource createLessonResource(LessonResource lessonResource) {
        return lessonResourceRepository.save(lessonResource);
    }

    public LessonResource updateLessonResource(LessonResource lessonResource) {
        return lessonResourceRepository.save(lessonResource);
    }

    public void deleteLessonResource(Long lessonResourceId) {
        lessonResourceRepository.deleteById(lessonResourceId);
    }

}
