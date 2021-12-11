package com.example.geoquiz;

public class Question {
    private int mTextResId;//Resid竟然不行
    private boolean mAnswerTure;
    private int misAnswered;

    public Question(int textResId, boolean answerTrue){
        mTextResId=textResId;
        mAnswerTure=answerTrue;
    }

    public boolean isAnswerTure() {
        return mAnswerTure;
    }

    public void setAnswerTure(boolean answerTure) {
        mAnswerTure = answerTure;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int getIsAnswered() {
        return misAnswered;
    }

    public void setIsAnswered(int isAnswered) {
        this.misAnswered = isAnswered;
    }
}

