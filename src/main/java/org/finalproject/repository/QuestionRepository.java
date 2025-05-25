package org.finalproject.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.finalproject.entity.Questions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class QuestionRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileName = "Questions.json";
    private List<Questions> questions;

    public QuestionRepository() {
        this.questions = loadQuestions();
    }

    public int getQuestionsCount() {
        return questions.size();
    }

    public List<Questions> getAllQuestions() {
        return questions;
    }

    public List<Questions> loadQuestions() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл не найден: " + fileName);
            }

            return objectMapper.readValue(inputStream, new TypeReference<List<Questions>>() {});

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке данных из JSON", e);
        }
    }

    public List<Questions> getQuestionsForRange(int starIndex, int batchSize) {
        int endIndex = Math.min(starIndex + batchSize, questions.size());
        return questions.subList(starIndex, endIndex);
    }
}