package org.finalproject.service;

import org.finalproject.entity.AnswerOptionEnum;
import org.finalproject.entity.AnswerScaleCategoryEnum;
import org.finalproject.entity.Questions;
import org.finalproject.repository.QuestionRepository;

import java.util.List;
import java.util.Map;

/**
 * Сервис анализа ответов пользователя.
 * Выполняет подсчет количества ответов по категориям (Экстраверсия, Нейротизм, Искренность),
 * включая отдельный подсчет ответов типа "Да" и "Нет", а также их суммирование.
 * Используется для последующей интерпретации результатов.
 */

public class AnswerAnalysisService {

    private final QuestionRepository questionRepository;
    private  final AnswerProcessingService answerProcessingService;


    public AnswerAnalysisService(QuestionRepository questionRepository, AnswerProcessingService answerProcessingService) {
        this.questionRepository = questionRepository;
        this.answerProcessingService = answerProcessingService;
    }


// countAnswersByCategory - All

    public int countAnswersByCategoryExtroversionAll(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategoryExtroversionYes(reqParameterMap) + countAnswersByCategoryExtroversionNo(reqParameterMap);
    }

    public int countAnswersByCategoryNeuroticismAll(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategoryNeuroticismYes(reqParameterMap);
    }

    public int countAnswersByCategorySincerityAll(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategorySincerityYes(reqParameterMap) + countAnswersByCategorySincerityNo(reqParameterMap);
    }


// countAnswersByCategory - Yes or No

    public int countAnswersByCategoryExtroversionYes(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.EXTROVERSION, AnswerOptionEnum.YES, AnswerOptionEnum.YES);
    }

    public int countAnswersByCategoryExtroversionNo(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.EXTROVERSION, AnswerOptionEnum.NO, AnswerOptionEnum.NO);
    }

    public int countAnswersByCategoryNeuroticismYes(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.NEUROTICISM, AnswerOptionEnum.YES, AnswerOptionEnum.YES);
    }

    public int countAnswersByCategorySincerityYes(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.SINCERITY, AnswerOptionEnum.YES, AnswerOptionEnum.YES);
    }

    public int countAnswersByCategorySincerityNo(Map<String, String[]> reqParameterMap) {
        return countAnswersByCategory(reqParameterMap, AnswerScaleCategoryEnum.SINCERITY, AnswerOptionEnum.NO, AnswerOptionEnum.NO);
    }


// countAnswersByCategory - main method

    public int countAnswersByCategory(
            Map<String, String[]> reqParameterMap,
            AnswerScaleCategoryEnum category,
            AnswerOptionEnum expectedUserAnswer,
            AnswerOptionEnum expectedReferenceAnswer
    ) {
        Map<String, String> answersMap = answerProcessingService.extractAnswers(reqParameterMap);
        List<Questions> allQuestions = questionRepository.getAllQuestions();
        int count = 0;

        for (Questions question : allQuestions) {
            String questionText = question.getQuestionFormulation();
            if (answersMap.containsKey(questionText)) {
                try {
                    AnswerScaleCategoryEnum scaleCategoryAnswer = AnswerScaleCategoryEnum.fromDisplayName(question.getCategory());
                    AnswerOptionEnum userAnswer = AnswerOptionEnum.fromDisplayName(answersMap.get(questionText));
                    AnswerOptionEnum referenceAnswer = AnswerOptionEnum.fromDisplayName(question.getReferenceAnswer());

                    if (scaleCategoryAnswer == category && userAnswer == expectedUserAnswer && referenceAnswer == expectedReferenceAnswer) {
                        count++;
                    }

                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка при обработке вопроса [" + questionText + "], category: " + question.getCategory() + ", причина: " + e.getMessage());
                }
            }
        }
        return count;
    }
}
