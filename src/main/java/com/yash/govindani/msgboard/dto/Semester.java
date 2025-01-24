package com.yash.govindani.msgboard.dto;

public class Semester {
    private int code;
    private String title;
    public Semester() {
        this.code = -1;
        this.title = null;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
}