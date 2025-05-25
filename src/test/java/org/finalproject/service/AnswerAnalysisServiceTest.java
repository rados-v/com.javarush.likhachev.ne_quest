package org.finalproject.service;

import org.finalproject.entity.AnswerOptionEnum;
import org.finalproject.entity.AnswerScaleCategoryEnum;
import org.finalproject.entity.Questions;
import org.finalproject.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AnswerAnalysisServiceTest {

    private QuestionRepository mockQuestionRepository;
    private AnswerAnalysisService mockAnswerAnalysisService;
    private AnswerProcessingService mockAnswerProcessingService;
    private Map<String, String[]> mockParams;

    @BeforeEach
    public void setUp() {
        mockQuestionRepository = mock(QuestionRepository.class);
        mockAnswerProcessingService = mock(AnswerProcessingService.class);
        mockAnswerAnalysisService = spy(new AnswerAnalysisService(mockQuestionRepository, mockAnswerProcessingService));
        mockParams = new HashMap<>();
    }


// testCountAnswersByCategory - All

    @Test
    void testCountAnswersByCategoryExtroversionAll() {
        doReturn(3).when(mockAnswerAnalysisService).countAnswersByCategoryExtroversionYes(mockParams);
        doReturn(2).when(mockAnswerAnalysisService).countAnswersByCategoryExtroversionNo(mockParams);

        int result = mockAnswerAnalysisService.countAnswersByCategoryExtroversionAll(mockParams);
        assertEquals(5, result);

        verify(mockAnswerAnalysisService).countAnswersByCategoryExtroversionYes(mockParams);
        verify(mockAnswerAnalysisService).countAnswersByCategoryExtroversionNo(mockParams);
    }


    @Test
    void testCountAnswersByCategoryNeuroticismAll() {
        doReturn(5).when(mockAnswerAnalysisService).countAnswersByCategoryNeuroticismYes(mockParams);

        int result = mockAnswerAnalysisService.countAnswersByCategoryNeuroticismAll(mockParams);
        assertEquals(5, result);

        verify(mockAnswerAnalysisService).countAnswersByCategoryNeuroticismAll(mockParams);
    }


    @Test
    void testCountAnswersByCategorySincerityAll() {
        doReturn(3).when(mockAnswerAnalysisService).countAnswersByCategorySincerityYes(mockParams);
        doReturn(2).when(mockAnswerAnalysisService).countAnswersByCategorySincerityNo(mockParams);

        int result = mockAnswerAnalysisService.countAnswersByCategorySincerityAll(mockParams);
        assertEquals(5, result);

        verify(mockAnswerAnalysisService).countAnswersByCategorySincerityYes(mockParams);
        verify(mockAnswerAnalysisService).countAnswersByCategorySincerityNo(mockParams);
    }


//    public int countAnswersByCategoryExtroversionYes(Map<String, String[]> reqParameterMap) {
//        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.EXTROVERSION, AnswerOptionEnum.YES, AnswerOptionEnum.YES);
//    }


// countAnswersByCategory - Yes or No

    @Test
    public void testCountAnswersByCategoryExtroversionYes() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"yes"});
        Map<String, String> extractedAnswers = Map.of("Вам нравится быть в центре внимания?", "yes");

        Questions question = new Questions();
        question.setQuestionFormulation("Вам нравится быть в центре внимания?");
        question.setCategory("Экстраверсия");
        question.setReferenceAnswer("yes");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategoryExtroversionYes(reqParams);
        assertEquals(1, result);
    }

    @Test
    void testCountAnswersByCategoryExtroversionNo() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"no"});
        Map<String, String> extractedAnswers = Map.of("Вам нравится быть в центре внимания?", "no");

        Questions question = new Questions();
        question.setQuestionFormulation("Вам нравится быть в центре внимания?");
        question.setCategory("Экстраверсия");
        question.setReferenceAnswer("no");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategoryExtroversionNo(reqParams);
        assertEquals(1, result);
    }

    @Test
    void testCountAnswersByCategoryNeuroticismYes() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"yes"});
        Map<String, String> extractedAnswers = Map.of("Вам нравится быть в центре внимания?", "yes");

        Questions question = new Questions();
        question.setQuestionFormulation("Вам нравится быть в центре внимания?");
        question.setCategory("Невротизм");
        question.setReferenceAnswer("yes");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategoryNeuroticismYes(reqParams);
        assertEquals(1, result);
    }

    @Test
    void testCountAnswersByCategorySincerityYes() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"yes"});
        Map<String, String> extractedAnswers = Map.of("Вам нравится быть в центре внимания?", "yes");

        Questions question = new Questions();
        question.setQuestionFormulation("Вам нравится быть в центре внимания?");
        question.setCategory("Искренность");
        question.setReferenceAnswer("yes");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategorySincerityYes(reqParams);
        assertEquals(1, result);
    }

    @Test
    void testCountAnswersByCategorySincerityNo() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"no"});
        Map<String, String> extractedAnswers = Map.of("Вам нравится быть в центре внимания?", "no");

        Questions question = new Questions();
        question.setQuestionFormulation("Вам нравится быть в центре внимания?");
        question.setCategory("Искренность");
        question.setReferenceAnswer("no");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategorySincerityNo(reqParams);
        assertEquals(1, result);
    }


// countAnswersByCategory - main method

    @Test
    void testCountAnswersByCategory() {
        Map<String, String[]> reqParams = Map.of("question1", new String[]{"yes"});
        Map<String, String> extractedAnswers = Map.of("Вы общительный человек?", "yes");

        Questions question = new Questions();
        question.setQuestionFormulation("Вы общительный человек?");
        question.setCategory("Экстраверсия");
        question.setReferenceAnswer("yes");

        when(mockQuestionRepository.getAllQuestions()).thenReturn(List.of(question));
        when(mockAnswerProcessingService.extractAnswers(reqParams)).thenReturn(extractedAnswers);

        int result = mockAnswerAnalysisService.countAnswersByCategory(
                reqParams,
                AnswerScaleCategoryEnum.EXTROVERSION,
                AnswerOptionEnum.YES,
                AnswerOptionEnum.YES
        );

        assertEquals(1, result);
    }
}