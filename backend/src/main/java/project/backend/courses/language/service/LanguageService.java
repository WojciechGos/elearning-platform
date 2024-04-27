package project.backend.courses.language.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.language.repository.LanguageRepository;
import project.backend.courses.language.model.Language;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguage(Long languageId) {
        return languageRepository.findById(languageId).orElseThrow(() -> new ResourceNotFoundException("Language not found with id [%s] ".formatted(languageId)));
    }

}
