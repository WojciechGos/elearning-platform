package project.backend.courses.language.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.backend.courses.language.model.Language;
import project.backend.courses.language.repository.LanguageRepository;
import project.backend.exception.types.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceImplTest {

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Test
    void testGetLanguages() {
        // Arrange
        Language language1 = new Language();
        Language language2 = new Language();
        List<Language> expectedLanguages = Arrays.asList(language1, language2);
        when(languageRepository.findAll()).thenReturn(expectedLanguages);

        // Act
        List<Language> actualLanguages = languageService.getLanguages();

        // Assert
        assertEquals(expectedLanguages, actualLanguages);
        verify(languageRepository, times(1)).findAll();
    }

    @Test
    void testGetLanguage() {
        // Arrange
        Long languageId = 1L;
        Language expectedLanguage = new Language();
        expectedLanguage.setId(languageId);
        when(languageRepository.findById(languageId)).thenReturn(Optional.of(expectedLanguage));

        // Act
        Language actualLanguage = languageService.getLanguage(languageId);

        // Assert
        assertEquals(expectedLanguage, actualLanguage);
        verify(languageRepository, times(1)).findById(languageId);
    }

    @Test
    void testGetLanguage_NotFound() {
        // Arrange
        Long languageId = 1L;
        when(languageRepository.findById(languageId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> languageService.getLanguage(languageId),
                "Expected getLanguage to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Language not found with id [1]"));
        verify(languageRepository, times(1)).findById(languageId);
    }

    @Test
    void testGetLanguageByName() {
        // Arrange
        String languageName = "English";
        Language expectedLanguage = new Language();
        expectedLanguage.setName(languageName);
        when(languageRepository.findByName(languageName)).thenReturn(Optional.of(expectedLanguage));

        // Act
        Language actualLanguage = languageService.getLanguageByName(languageName);

        // Assert
        assertEquals(expectedLanguage, actualLanguage);
        verify(languageRepository, times(1)).findByName(languageName);
    }

    @Test
    void testGetLanguageByName_NotFound() {
        // Arrange
        String languageName = "NonExistentLanguage";
        when(languageRepository.findByName(languageName)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> languageService.getLanguageByName(languageName),
                "Expected getLanguageByName to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Language not found with name [NonExistentLanguage]"));
        verify(languageRepository, times(1)).findByName(languageName);
    }
}