package com.example.quizapplication;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Lisbon"}, 2));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1));
        questions.add(new Question("Which planet is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2));
        questions.add(new Question("What is the chemical symbol for gold?", new String[]{"Au", "Ag", "Cu", "Fe"}, 1));
        questions.add(new Question("Which of these animals is the largest mammal on Earth?", new String[]{"Elephant", "Giraffe", "Blue Whale", "Hippopotamus"}, 2));
        questions.add(new Question("What is the capital of Japan?", new String[]{"Tokyo", "Osaka", "Kyoto", "Nagoya"}, 0));
        questions.add(new Question("Who painted the Sistine Chapel?", new String[]{"Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"}, 0));
        // Add more questions as needed
        return questions;
    }
}

