package edu.washington.clgan.quizdroid;

import java.util.ArrayList;

public class TopicRepository implements ITopicRespository {
    public ArrayList<Topic> getTopics(){
        ArrayList<Topic> array = new ArrayList<Topic>();
        Topic math = new Topic("Math");
        math.setLongDescr("You will begin taking a Mathematics quiz. Mathematics is the study of topics such as quantity (numbers), structure, space, and change.");
        math.setShortDescr("There are 5 questions.");
        ArrayList<Quiz> mathQuestions = new ArrayList<Quiz>();
        Quiz mq1 = new Quiz("QUESTION 1: Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?",
                "74.05", "75.85", "62.9", "67.3", 1);
        Quiz mq2 = new Quiz("QUESTION 2: Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?",
                "74.05", "75.85", "62.9", "67.3", 1);
        Quiz mq3 = new Quiz("QUESTION 3: Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?",
                "74.05", "75.85", "62.9", "67.3", 1);
        Quiz mq4 = new Quiz("QUESTION 4: Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?",
                "74.05", "75.85", "62.9", "67.3", 1);
        Quiz mq5 = new Quiz("QUESTION 5: Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?",
                "74.05", "75.85", "62.9", "67.3", 1);
        mathQuestions.add(mq1);
        mathQuestions.add(mq2);
        mathQuestions.add(mq3);
        mathQuestions.add(mq4);
        mathQuestions.add(mq5);
        math.setQuestions(mathQuestions);
        array.add(math);

        Topic english = new Topic("English");
        english.setLongDescr("You will begin taking an English quiz. The meaning of English as a subject is to educate on the English language in general and to aid in the " +
                "understanding and employment of the language. The subject of English is most often split into two main topics; English Literature and English Language.");
        english.setShortDescr("There are 5 questions.");
        ArrayList<Quiz> englishQuestions = new ArrayList<Quiz>();
        Quiz eq1 = new Quiz("QUESTION 1: Sorry, you can't borrow my pencil. I ..... it myself.",
                "was using", "using", "use", "am using", 3);
        Quiz eq2 = new Quiz("QUESTION 2: Sorry, you can't borrow my pencil. I ..... it myself.",
                "was using", "using", "use", "am using", 3);
        Quiz eq3 = new Quiz("QUESTION 3: Sorry, you can't borrow my pencil. I ..... it myself.",
                "was using", "using", "use", "am using", 3);
        Quiz eq4 = new Quiz("QUESTION 4: Sorry, you can't borrow my pencil. I ..... it myself.",
                "was using", "using", "use", "am using", 3);
        Quiz eq5 = new Quiz("QUESTION 5: Sorry, you can't borrow my pencil. I ..... it myself.",
                "was using", "using", "use", "am using", 3);
        englishQuestions.add(eq1);
        englishQuestions.add(eq2);
        englishQuestions.add(eq3);
        englishQuestions.add(eq4);
        englishQuestions.add(eq5);
        english.setQuestions(englishQuestions);
        array.add(english);

        Topic physics = new Topic("Physics");
        physics.setLongDescr("You will begin taking a Physics quiz. Physics is the branch of science concerned with the nature and properties of matter and energy.");
        physics.setShortDescr("There are 5 questions.");
        ArrayList<Quiz> physicsQuestions = new ArrayList<Quiz>();
        Quiz pq1 = new Quiz("QUESTION 1: The tendency of objects to resist changes in motion.",
                "Inertia", "Force", "Mechanical Equilibrium", "Equilibrium rule", 0);
        Quiz pq2 = new Quiz("QUESTION 2: The tendency of objects to resist changes in motion.",
                "Inertia", "Force", "Mechanical Equilibrium", "Equilibrium rule", 0);
        Quiz pq3 = new Quiz("QUESTION 3: The tendency of objects to resist changes in motion.",
                "Inertia", "Force", "Mechanical Equilibrium", "Equilibrium rule", 0);
        Quiz pq4 = new Quiz("QUESTION 4: The tendency of objects to resist changes in motion.",
                "Inertia", "Force", "Mechanical Equilibrium", "Equilibrium rule", 0);
        Quiz pq5 = new Quiz("QUESTION 5: The tendency of objects to resist changes in motion.",
                "Inertia", "Force", "Mechanical Equilibrium", "Equilibrium rule", 0);
        physicsQuestions.add(pq1);
        physicsQuestions.add(pq2);
        physicsQuestions.add(pq3);
        physicsQuestions.add(pq4);
        physicsQuestions.add(pq5);
        physics.setQuestions(physicsQuestions);
        array.add(physics);
        return array;
    }

}
