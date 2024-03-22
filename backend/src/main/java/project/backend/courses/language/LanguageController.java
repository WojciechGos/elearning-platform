package project.backend.courses.language;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/languages")
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {
        return new ResponseEntity<>(languageService.getLanguages(), HttpStatus.OK);
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<Language> getLanguage(@PathVariable("languageId") Long languageId) {
        return new ResponseEntity<>(languageService.getLanguage(languageId), HttpStatus.OK);
    }
}
