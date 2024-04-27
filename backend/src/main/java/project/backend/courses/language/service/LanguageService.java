package project.backend.courses.language.service;

import project.backend.courses.language.model.Language;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface LanguageService {
    List<Language> getLanguages();

    Language getLanguage(Long languageId);

}
