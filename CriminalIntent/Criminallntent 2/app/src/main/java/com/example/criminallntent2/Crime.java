package com.example.criminallntent2;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //是否报警
    private boolean mCallPolice;

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public void setCallPolice(boolean requiresPolice) {
        mCallPolice = requiresPolice;
    }

    public boolean isCallPolice() {
        return mCallPolice;
    }
    public Crime(){
        mId=UUID.randomUUID();//随机唯一Id
        mDate=new Date();//当前日期
    }

}

