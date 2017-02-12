package edu.washington.clgan.quizdroid;

import java.util.ArrayList;

public class Topic {
    private String title;
    private String desc;
    private ArrayList<Quiz> questions;

    public Topic(String title, String desc, ArrayList<Quiz> questions){
        this.title = title;
        this.desc = desc;
        this.questions = questions;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setQuestions(ArrayList<Quiz> questions){
        this.questions = questions;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc(){
        return desc;
    }

    public ArrayList<Quiz> getQuestions(){
        return questions;
    }
}
