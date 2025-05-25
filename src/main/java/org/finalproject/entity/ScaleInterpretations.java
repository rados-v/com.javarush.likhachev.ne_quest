package org.finalproject.entity;

public class ScaleInterpretations {
    private int[] range;
    private String interpretation;

    public ScaleInterpretations() {
    }

    public ScaleInterpretations(int[] range, String interpretation) {
        this.range = range;
        this.interpretation = interpretation;
    }

    public int[] getRange() {
        return range;
    }

    public void setRange(int[] range) {
        this.range = range;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
