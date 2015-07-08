package com.brekol.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by brekol on 08.07.15.
 */
public class Entry {

    private Set<Answer> answers = new TreeSet<Answer>();

    private Question question;

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
