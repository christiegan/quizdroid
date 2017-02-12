package edu.washington.clgan.quizdroid;

import java.util.ArrayList;

public class Quiz {
    private String text;
    private int answer;
    private String[] answers;

    public Quiz(String text, int answer, String[] answers){
        this.text = text;
        this.answer = answer;
        this.answers = answers;
    }

    public ArrayList<String> getQuestionAnswer(){
        ArrayList<String> array = new ArrayList<String>();
        array.add(text);
        for(int i = 0; i < answers.length; i++){
            array.add(answers[i]);
        }
        return array;
    }

    public int getCorrectAns(){
        return answer;
    }
}
