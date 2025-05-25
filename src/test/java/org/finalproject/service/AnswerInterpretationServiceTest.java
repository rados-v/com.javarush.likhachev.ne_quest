package org.finalproject.service;

import org.finalproject.entity.AnswerScaleCategoryEnum;
import org.finalproject.entity.ScaleInterpretations;
import org.finalproject.repository.AnswerInterpretationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnswerInterpretationServiceTest {

    private AnswerInterpretationRepository mockAnswerInterpretationRepository;
    private AnswerInterpretationService mockAnswerInterpretationService;

    @BeforeEach
    void setUp() {
        mockAnswerInterpretationRepository = mock(AnswerInterpretationRepository.class);
        mockAnswerInterpretationService = new AnswerInterpretationService(mockAnswerInterpretationRepository);
    }


    //  getInterpretation - by category - Extroversion

    @Test
    void testGetInterpretationExtroversion_found() {
        // Создаем пример интерпретации
        ScaleInterpretations interpretation = mock(ScaleInterpretations.class);
        when(interpretation.getInterpretation()).thenReturn("Вы экстраверт!");
        when(interpretation.getRange()).thenReturn(new int[]{1, 10});

        // Настроим поведение мока: репозиторий возвращает интерпретацию для "Экстраверсия"
        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Экстраверсия")).thenReturn(new ScaleInterpretations[]{interpretation});

        // Вызов метода
        String result = mockAnswerInterpretationService.getInterpretationExtroversion(5);

        // Проверка, что метод вернул правильную интерпретацию
        assertEquals("Вы экстраверт!", result);
    }

    @Test
    void testGetInterpretationExtroversion_notFound() {
        // Настроим поведение мока: репозиторий не находит интерпретации для "Экстраверсия"
        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Экстраверсия")).thenReturn(null);

        // Вызов метода
        String result = mockAnswerInterpretationService.getInterpretationExtroversion(5);

        // Проверка, что метод вернул строку "Интерпретация не найдена"
        assertEquals("Интерпретация не найдена", result);
    }


    //  getInterpretation - by category - Neuroticism

    @Test
    void testGetInterpretationNeuroticism_found() {
        ScaleInterpretations interpretation = mock(ScaleInterpretations.class);
        when(interpretation.getInterpretation()).thenReturn("Вы невростеник!");
        when(interpretation.getRange()).thenReturn(new int[]{1, 10});

        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Невротизм")).thenReturn(new ScaleInterpretations[]{interpretation});

        String result = mockAnswerInterpretationService.getInterpretationNeuroticism(5);
        assertEquals("Вы невростеник!", result);
    }

    @Test
    void testGetInterpretationNeuroticism_notFound() {
        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Невротизм")).thenReturn(null);

        String result = mockAnswerInterpretationService.getInterpretationNeuroticism(5);
        assertEquals("Интерпретация не найдена", result);
    }


    //  getInterpretation - by category - Sincerity

    @Test
    void testGetInterpretationSincerity_found() {
        ScaleInterpretations interpretation = mock(ScaleInterpretations.class);
        when(interpretation.getInterpretation()).thenReturn("Вы искренний!");
        when(interpretation.getRange()).thenReturn(new int[]{1, 10});

        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Искренность")).thenReturn(new ScaleInterpretations[]{interpretation});

        String result = mockAnswerInterpretationService.getInterpretationSincerity(5);
        assertEquals("Вы искренний!", result);
    }

    @Test
    void testGetInterpretationSincerity_notFound() {
        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Искренность")).thenReturn(null);

        String result = mockAnswerInterpretationService.getInterpretationSincerity(5);
        assertEquals("Интерпретация не найдена", result);
    }


    // getInterpretationForScore - main method

    @Test
    void testGetInterpretationForScore_found() {
        ScaleInterpretations interpretation = mock(ScaleInterpretations.class);
        when(interpretation.getRange()).thenReturn(new int[]{5, 10});

        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Экстраверсия"))
                .thenReturn(new ScaleInterpretations[]{interpretation});

        ScaleInterpretations result = mockAnswerInterpretationService.getInterpretationForScore(AnswerScaleCategoryEnum.EXTROVERSION, 7);
        assertNotNull(result);
        assertEquals(interpretation, result);
    }

    @Test
    void testGetInterpretationForScore_notFoundInRange() {
        ScaleInterpretations interpretation = mock(ScaleInterpretations.class);
        when(interpretation.getRange()).thenReturn(new int[]{10, 15});

        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Экстраверсия"))
                .thenReturn(new ScaleInterpretations[]{interpretation});

        ScaleInterpretations result = mockAnswerInterpretationService.getInterpretationForScore(AnswerScaleCategoryEnum.EXTROVERSION, 5);
        assertNull(result);
    }

    @Test
    void testGetInterpretationForScore_noInterpretations() {
        when(mockAnswerInterpretationRepository.getInterpretationsForScale("Экстраверсия")).thenReturn(null);

        ScaleInterpretations result = mockAnswerInterpretationService.getInterpretationForScore(AnswerScaleCategoryEnum.EXTROVERSION, 5);
        assertNull(result);
    }
}