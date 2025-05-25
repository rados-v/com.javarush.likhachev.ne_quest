package org.finalproject.service;

import org.finalproject.entity.Questions;
import org.finalproject.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



class QuestionServiceTest {

    private QuestionRepository questionRepository;
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionRepository = mock(QuestionRepository.class);
        questionService = new QuestionService(questionRepository);
    }

    @Test
    public void testGetAllQuestionFormulation_returnsCorrectFormulations() {
        Questions q1 = mock(Questions.class);
        Questions q2 = mock(Questions.class);

        when(q1.getQuestionFormulation()).thenReturn("Question 1?");
        when(q2.getQuestionFormulation()).thenReturn("Question 2?");

        when(questionRepository.getAllQuestions()).thenReturn(Arrays.asList(q1, q2));

        List<String> result = questionService.getAllQuestionFormulation();

        assertEquals(2, result.size());
        assertEquals("Question 1?", result.get(0));
        assertEquals("Question 2?", result.get(1));

        verify(questionRepository, times(1)).getAllQuestions();
        verify(q1, times(1)).getQuestionFormulation();
        verify(q2, times(1)).getQuestionFormulation();
    }

    @Test
    public void testGetAllQuestionFormulation_emptyList() {
        when(questionRepository.getAllQuestions()).thenReturn(List.of());

        List<String> result = questionService.getAllQuestionFormulation();

        assertEquals(0, result.size());
        verify(questionRepository, times(1)).getAllQuestions();
    }
}