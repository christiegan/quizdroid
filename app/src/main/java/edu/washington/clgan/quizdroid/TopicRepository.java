package edu.washington.clgan.quizdroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TopicRepository implements ITopicRespository {

    public ArrayList<Topic> getTopics(){
        ArrayList<Topic> result = new ArrayList<Topic>();

        Quiz quiz;
        Topic topic;

        String temp = loadJSONFromAsset();
        try {
            JSONArray topicArray = new JSONArray(temp);
            for(int i = 0;i < topicArray.length(); i++){
                ArrayList<Quiz> quizArray = new ArrayList<Quiz>();
                JSONObject e = topicArray.getJSONObject(i);
                String title = e.getString("title");
                String desc = e.getString("desc");
                JSONArray questionArray = e.getJSONArray("questions");
                for(int j = 0; j < questionArray.length(); j++){
                    JSONObject f = questionArray.getJSONObject(j);
                    String text = f.getString("text");
                    int answer = f.getInt("answer");
                    JSONArray answersArray = f.getJSONArray("answers");

                    String[] answers = new String[4];
                    for(int k = 0; k < answersArray.length(); k++){
                        answers[k] = answersArray.get(k).toString();
                    }
                    quiz = new Quiz(text, answer - 1, answers);
                    quizArray.add(quiz);
                }
                topic = new Topic(title, desc, quizArray);
                result.add(topic);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputstream = new FileInputStream("/data/questions.json");
            int size = inputstream.available();
            byte[] buffer = new byte[size];
            inputstream.read(buffer);
            inputstream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
