package org.finalproject.service;

import org.finalproject.entity.AnswersDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Вспомогательный сервис для подсчета итогов по категориям.
 * Принимает DTO с текущими ответами и возвращает агрегированные числовые значения.
 * Планировался для использования при рефакторинге AnswerAnalysisServlet.
 *
 * Актуально не используется в функционале веб-приложения.
 */

public class AnswersService {

    // Метод для вычисления итоговых значений для всех категорий на основе текущих данных
    public Map<String, Integer> calculateTotals(AnswersDto currentAnswer) {
        Map<String, Integer> totals = new HashMap<>();
        totals.put("extroversionYes", currentAnswer.extroversionYes);
        totals.put("extroversionNo", currentAnswer.extroversionNo);
        totals.put("neuroticismYes", currentAnswer.neuroticismYes);
        totals.put("sincerityYes", currentAnswer.sincerityYes);
        totals.put("sincerityNo", currentAnswer.sincerityNo);

        return totals;
    }
}
