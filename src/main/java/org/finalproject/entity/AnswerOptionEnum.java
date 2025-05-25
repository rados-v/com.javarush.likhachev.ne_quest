package org.finalproject.entity;

public enum AnswerOptionEnum {
    YES("yes"),
    NO("no");

    private final String displayName;

    AnswerOptionEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AnswerOptionEnum fromDisplayName(String name) {
        for (AnswerOptionEnum answerOption : AnswerOptionEnum.values()) {
            if (answerOption.getDisplayName().equalsIgnoreCase(name)) {
                return answerOption;
            }
        }
        throw new IllegalArgumentException("Неизвестный ответ: " + name);
    }
}