package edu.washington.clgan.quizdroid;

import android.util.Log;

import java.util.ArrayList;

public class QuizApp extends android.app.Application {
    private static ArrayList<Topic> topics;
    private static QuizApp singleton;

    public static QuizApp getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "being loaded and run");
        TopicRepository tr = new TopicRepository();
        topics = tr.getTopics();
        singleton = this;
    }

    public ArrayList<Topic> getTopics(){
        return this.topics;
    }
}
