package project.backend.courses.language.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import project.backend.courses.language.model.Language;

import java.util.List;

public interface LanguageController {
    ResponseEntity<List<Language>> getLanguages();
    ResponseEntity<Language> getLanguage(@PathVariable("languageId") Long languageId);
}
