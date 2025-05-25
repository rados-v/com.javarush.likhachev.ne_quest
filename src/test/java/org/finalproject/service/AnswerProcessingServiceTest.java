package org.finalproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnswerProcessingServiceTest {

    private QuestionService mockQuestionService;
    private AnswerProcessingService mockAnswerProcessingService;

    @BeforeEach
    void setUp() {
        mockQuestionService = mock(QuestionService.class);
        mockAnswerProcessingService = new AnswerProcessingService(mockQuestionService);
    }


    @Test
    void testExtractAnswers_validInput() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("answer_1", new String[]{"yes"});
        parameterMap.put("answer_2", new String[]{"no"});

        List<String> questions = Arrays.asList("Вопрос 1", "Вопрос 2", "Вопрос 3");
        when(mockQuestionService.getAllQuestionFormulation()).thenReturn(questions);

        Map<String, String> result = mockAnswerProcessingService.extractAnswers(parameterMap);

        assertEquals(2, result.size());
        assertEquals("yes", result.get("Вопрос 1")); // индекс 0
        assertEquals("no", result.get("Вопрос 2"));  // индекс 1
    }


    @Test
    void testExtractAnswers_withInvalidKey() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("not_answer", new String[]{"yes"});
        parameterMap.put("answer_abc", new String[]{"no"});

        List<String> questions = Arrays.asList("Вопрос 1", "Вопрос 2");
        when(mockQuestionService.getAllQuestionFormulation()).thenReturn(questions);

        Map<String, String> result = mockAnswerProcessingService.extractAnswers(parameterMap);

        assertEquals(0, result.size());
    }


    @Test
    public void testCountAnswers_validInput() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("answer_1", new String[]{"yes"});
        parameterMap.put("answer_2", new String[]{"no"});
        parameterMap.put("answer_3", new String[]{"yes"});
        parameterMap.put("other_param", new String[]{"ignored"});

        Map<String, Integer> result = mockAnswerProcessingService.countAnswers(parameterMap);

        assertEquals(2, result.get("answersYes"));
        assertEquals(1, result.get("answersNo"));
    }

    @Test
    public void testCountAnswers_emptyInput() {
        Map<String, String[]> parameterMap = new HashMap<>();

        Map<String, Integer> result = mockAnswerProcessingService.countAnswers(parameterMap);

        assertEquals(0, result.get("answersYes"));
        assertEquals(0, result.get("answersNo"));
    }

    @Test
    public void testCountAnswers_nullAndInvalidValues() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("answer_1", new String[]{null});
        parameterMap.put("answer_2", new String[]{});
        parameterMap.put("answer_3", null);
        parameterMap.put("answer_4", new String[]{"maybe"});

        Map<String, Integer> result = mockAnswerProcessingService.countAnswers(parameterMap);

        assertEquals(0, result.get("answersYes"));
        assertEquals(0, result.get("answersNo"));
    }

}