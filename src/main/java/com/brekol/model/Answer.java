package com.brekol.model;

/**
 * Created by brekol on 08.07.15.
 */
public class Answer implements Comparable<Answer> {

    private String text;

    private boolean correct;
    private char leadingCharacter;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
        this.leadingCharacter = text.charAt(0);
    }

    public char getLeadingCharacter() {
        return leadingCharacter;
    }

    public void setLeadingCharacter(char leadingCharacter) {
        this.leadingCharacter = leadingCharacter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public int compareTo(Answer other) {
        return text.compareTo(other.getText());
    }
}
