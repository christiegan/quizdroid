package edu.washington.clgan.quizdroid;

import java.util.ArrayList;

public class Quiz {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int correctAns;

    public Quiz(String question, String answerA, String answerB, String answerC, String answerD, int correctAns){
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAns = correctAns;
    }

    public ArrayList<String> getQuestionAnswer(){
        ArrayList<String> array = new ArrayList<String>();
        array.add(question);
        array.add(answerA);
        array.add(answerB);
        array.add(answerC);
        array.add(answerD);
        return array;
    }

    public int getCorrectAns(){
        return correctAns;
    }
}
