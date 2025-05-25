package org.finalproject.service;

import org.finalproject.repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис обработки пользовательских ответов.
 * Извлекает данные из параметров HTTP-запроса, сопоставляя ответы с формулировками вопросов.
 * Подсчитывает количество ответов "Да" и "Нет" и возвращает их в виде карты.
 * Также используется другими сервисами для анализа.
 *
 * ps: Автор осознает, что данные методы нарушают правила слоев между сервисом и сервлетов.
 */

public class AnswerProcessingService {

    private  final QuestionService questionService;

    public AnswerProcessingService(QuestionService questionService) {
        this.questionService = questionService;
    }


    public Map<String, String> extractAnswers(Map<String, String[]> parameterMap) {
        Map<String, String> answersMap = new HashMap<>();
        List<String> allQuestionFormulation = questionService.getAllQuestionFormulation();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();

            if (paramName.startsWith("answer_")) {
                String idPart = paramName.substring(7);

                if (idPart.matches("\\d+")) {
                    int questionId = Integer.parseInt(idPart);

                    if (questionId > 0 && questionId <= allQuestionFormulation.size()) {
                        String answer = entry.getValue()[0];
                        String questionFormulation = allQuestionFormulation.get(questionId - 1);
                        answersMap.put(questionFormulation, answer);
                    }
                }
            }
        }
        return answersMap;
    }


    public Map<String, Integer> countAnswers(Map<String, String[]> parameterMap) {
        int answersYes = 0;
        int answersNo = 0;

        for (String paramName : parameterMap.keySet()) {
            if (paramName.startsWith("answer_")) {
                String[] values = parameterMap.get(paramName);
                if (values != null && values.length > 0) {
                    String answer = values[0];
                    if ("yes".equals(answer)) {
                        answersYes++;
                    } else if ("no".equals(answer)) {
                        answersNo++;
                    }
                }
            }
        }

        Map<String, Integer> answerCounts = new HashMap<>();
        answerCounts.put("answersYes", answersYes);
        answerCounts.put("answersNo", answersNo);

        return answerCounts;
    }
}
