package com.example.geoquiz;

public class Question {
    private int mTextResId;//Resid竟然不行
    private boolean mAnswerTure;
    private boolean  mAnswered;

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

    public boolean IsAnswered() {
        return mAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.mAnswered = isAnswered;
    }
}

