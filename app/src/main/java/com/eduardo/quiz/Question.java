package com.eduardo.quiz;

public class Question {

    private int id;
    private boolean isAnswerTrue;

    public Question() {
    }

    public Question(int id, boolean isAnswerTrue) {
        this.id = id;
        this.isAnswerTrue = isAnswerTrue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        isAnswerTrue = answerTrue;
    }
}
