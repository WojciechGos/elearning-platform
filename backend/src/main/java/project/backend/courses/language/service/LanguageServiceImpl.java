package project.backend.courses.language.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.language.repository.LanguageRepository;
import project.backend.courses.language.model.Language;
import project.backend.exception.types.ResourceNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguage(Long languageId) {
        return languageRepository.findById(languageId).orElseThrow(() -> new ResourceNotFoundException("Language not found with id [%s] ".formatted(languageId)));
    }

    @Override
    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Language not found with name [%s] ".formatted(name)));
    }

}
