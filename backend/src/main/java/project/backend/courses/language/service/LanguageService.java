package project.backend.courses.language.service;

import project.backend.courses.language.model.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getLanguages();

    Language getLanguage(Long languageId);

    Language getLanguageByName(String name);

}
