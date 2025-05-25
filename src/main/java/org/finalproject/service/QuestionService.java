package org.finalproject.service;

import org.finalproject.entity.Questions;
import org.finalproject.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис работы с вопросами.
 * Получает список формулировок всех вопросов из репозитория.
 * Используется для сопоставления ID вопроса с его текстом при обработке пользовательских ответов.
 */

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<String> getAllQuestionFormulation() {
        List<String> formulations = new ArrayList<>();

        for (Questions question : questionRepository.getAllQuestions()) {
            formulations.add(question.getQuestionFormulation());
        }
        return formulations;
    }
}

/*
 * Было принято не использовать следующие методы.
 */

//    public List<Integer> getQuestionIdsByCategory(String category) {
//        List<Questions> allQuestions  = questionRepository.getAllQuestions();
//        List<Integer> filteredIds = new ArrayList<>();
//
//        for (Questions question : allQuestions) {
//            if (question.getCategory().equalsIgnoreCase(category)) {
//                filteredIds.add(question.getId());
//            }
//        }
//        return filteredIds;
//    }
//
//    public List<String> getAllCategory() {
//        List<String> categories = new ArrayList<>();
//
//        for (Questions question : questionRepository.getAllQuestions()) {
//            categories.add(question.getCategory());
//        }
//        return categories;
//    }
//
//    public List<Integer> getAllQuestionIds() {
//        List<Integer> questionIds = new ArrayList<>();
//
//        for (Questions question : questionRepository.getAllQuestions()) {
//            questionIds.add(question.getId());
//        }
//        return questionIds;
//    }
//
//    public int countQuestionsByCategory(String category) {
//        List<Integer> questionIdsByCategory = this.getQuestionIdsByCategory(category);
//        return questionIdsByCategory.size();
//    }
//}
