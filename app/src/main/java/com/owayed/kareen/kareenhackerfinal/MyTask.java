package com.owayed.kareen.kareenhackerfinal;

import java.util.Date;

public class MyTask {
    private String key;// key: uniqqe id for each object. have to be..
    private String title;
    private String text;
    private int important;
    private int necessary;
    private Date creatDate;
    private Date DueDate;
    private String owner;

    public MyTask() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTirle() {
        return title;
    }

    public void setTirle(String tirle) {
        this.title = tirle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getNecessary() {
        return necessary;
    }

    public void setNecessary(int necessary) {
        this.necessary = necessary;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", important=" + important +
                ", necessary=" + necessary +
                ", creatDate=" + creatDate +
                ", DueDate=" + DueDate +
                '}';
    }
    public void setOwner(String owner){
        this.owner=owner;
    }
}








