package org.finalproject.service;

import org.finalproject.entity.AnswerScaleCategoryEnum;
import org.finalproject.entity.ScaleInterpretations;
import org.finalproject.repository.AnswerInterpretationRepository;

/**
 * Сервис интерпретации результатов.
 * На основе общего количества набранных баллов по каждой шкале
 * (Экстраверсия, Нейротизм, Искренность) возвращает текстовую интерпретацию.
 * Использует данные из репозитория интерпретаций.
 */

public class AnswerInterpretationService {
    private final AnswerInterpretationRepository questionInterpretationRepository;

    public AnswerInterpretationService(AnswerInterpretationRepository questionInterpretationRepository) {
        this.questionInterpretationRepository = questionInterpretationRepository;
    }


    //  getInterpretation - by category

    public String getInterpretationExtroversion (int score) {
        ScaleInterpretations interpretation = getInterpretationForScore(AnswerScaleCategoryEnum.EXTROVERSION, score);
        return interpretation != null ? interpretation.getInterpretation() : "Интерпретация не найдена";
    }

    public String getInterpretationNeuroticism (int score) {
        ScaleInterpretations interpretation =  getInterpretationForScore(AnswerScaleCategoryEnum.NEUROTICISM, score);
        return interpretation != null ? interpretation.getInterpretation() : "Интерпретация не найдена";
    }

    public String getInterpretationSincerity (int score) {
        ScaleInterpretations interpretation =  getInterpretationForScore(AnswerScaleCategoryEnum.SINCERITY, score);
        return interpretation != null ? interpretation.getInterpretation() : "Интерпретация не найдена";
    }


// getInterpretationForScore - main method

    public ScaleInterpretations getInterpretationForScore(AnswerScaleCategoryEnum scaleCategory, int score) {
        String scaleName = scaleCategory.getDisplayName();
        ScaleInterpretations[] interpretations = questionInterpretationRepository.getInterpretationsForScale(scaleName);

        if (interpretations != null) {
            for (ScaleInterpretations interpretation : interpretations) {
                if (score >= interpretation.getRange()[0] && score <= interpretation.getRange()[1]) {
                    return interpretation;
                }
            }
        }
        return null;
    }
}
