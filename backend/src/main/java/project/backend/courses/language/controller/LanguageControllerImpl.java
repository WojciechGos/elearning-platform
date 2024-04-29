package project.backend.courses.language.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.courses.language.service.LanguageService;
import project.backend.courses.language.model.Language;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/languages")
public class LanguageControllerImpl implements LanguageController {
    private final LanguageService languageService;

    @Override
    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {
        return new ResponseEntity<>(languageService.getLanguages(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{languageId}")
    public ResponseEntity<Language> getLanguage(@PathVariable("languageId") Long languageId) {
        return new ResponseEntity<>(languageService.getLanguage(languageId), HttpStatus.OK);
    }
}
