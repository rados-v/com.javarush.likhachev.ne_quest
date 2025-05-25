package org.finalproject.entity;

public enum AnswerScaleCategoryEnum {
    EXTROVERSION("Экстраверсия"),
    NEUROTICISM("Невротизм"),
    SINCERITY("Искренность");

    private final String displayName;

    AnswerScaleCategoryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AnswerScaleCategoryEnum fromDisplayName(String name) {
        for (AnswerScaleCategoryEnum category : AnswerScaleCategoryEnum.values()) {
            if (category.getDisplayName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Неизвестная категория: " + name);
    }
}
