package com.brekol.model;

/**
 * Created by brekol on 08.07.15.
 */
public class Question {

    private String text;

    public Question(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
