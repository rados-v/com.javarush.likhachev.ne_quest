package org.finalproject.entity;

import java.util.List;

public class Questions {
    private int id;
    private String questionFormulation;
    private String category;
    private String referenceAnswer;
    private List<Questions> questionsList;


    public Questions(int id, String questionFormulation, String category, String referenceAnswer) {
        this.id = id;
        this.questionFormulation = questionFormulation;
        this.category = category;
        this.referenceAnswer = referenceAnswer;
    }

    public Questions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionFormulation() {
        return questionFormulation;
    }

    public void setQuestionFormulation(String questionFormulation) {
        this.questionFormulation = questionFormulation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }


    @Override
    public String toString() {
        return "Question{id=" + id + ", question='" + questionFormulation + "', category='" + category + "', referenceAnswer='" + referenceAnswer + "'}";
    }
}
