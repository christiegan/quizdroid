package edu.washington.clgan.quizdroid;

import java.util.ArrayList;

public class Topic {
    private int imageID;
    private String title;
    private String shortDescr;
    private String longDescr;
    private ArrayList<Quiz> questions;

    public Topic(String title){
        this.title = title;
        this.imageID = R.drawable.clipart;
    }

    public void setShortDescr(String shortDescr){
        this.shortDescr = shortDescr;
    }

    public void setLongDescr(String longDescr){
        this.longDescr = longDescr;
    }

    public void setQuestions(ArrayList<Quiz> questions){
        this.questions = questions;
    }

    public String getTitle(){
        return title;
    }

    public int getImageID(){
        return imageID;
    }

    public String getShortDescr(){
        return shortDescr;
    }

    public String getLongDescr(){
        return longDescr;
    }

    public ArrayList<Quiz> getQuestions(){
        return questions;
    }
}
