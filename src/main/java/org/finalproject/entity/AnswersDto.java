package org.finalproject.entity;


/*
 * Актуально не используется.
 * Планировалось использовать при рефакторинге AnswerAnalysisServlet.java.
 */


public class AnswersDto {
    public int extroversionYes;
    public int extroversionNo;
    public int neuroticismYes;
    public int sincerityYes;
    public int sincerityNo;

    public AnswersDto(int extroversionYes, int extroversionNo, int neuroticismYes, int sincerityYes, int sincerityNo) {
        this.extroversionYes = extroversionYes;
        this.extroversionNo = extroversionNo;
        this.neuroticismYes = neuroticismYes;
        this.sincerityYes = sincerityYes;
        this.sincerityNo = sincerityNo;
    }

}
