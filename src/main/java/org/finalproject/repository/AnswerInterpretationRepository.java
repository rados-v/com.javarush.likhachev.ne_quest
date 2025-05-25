package org.finalproject.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.finalproject.entity.ScaleInterpretations;

import java.io.InputStream;
import java.util.Map;

public class AnswerInterpretationRepository {

    private final String fileName = "ScaleInterpretations.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, ScaleInterpretations[]> scaleInterpretations;

    public AnswerInterpretationRepository() {
        this.scaleInterpretations = loadInterpretations();
    }

    public Map<String, ScaleInterpretations[]> loadInterpretations() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл scales.json не найден");
            }
            return objectMapper.readValue(inputStream, new TypeReference<Map<String, ScaleInterpretations[]>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке интерпретаций из JSON", e);
        }
    }

    public ScaleInterpretations[] getInterpretationsForScale(String scaleName) {
        return scaleInterpretations.get(scaleName);
    }
}
