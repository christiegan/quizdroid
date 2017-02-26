package edu.washington.clgan.quizdroid;

import android.util.Log;

import java.util.ArrayList;

public class QuizApp extends android.app.Application {
    private static QuizApp singleton;
    private static ArrayList<Topic> topics;

    public static QuizApp getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "being loaded and run");
        singleton = this;
    }

    public ArrayList<Topic> getTopics(){
        return this.topics;
    }

    public void setTopics(ArrayList<Topic> newTopics){
        this.topics = newTopics;
    }
}
