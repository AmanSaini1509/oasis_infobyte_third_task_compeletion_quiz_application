package com.example.quizapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button nextButton;

    private static final String TAG = "QuizApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);

        questions = Quiz.getQuestions();
        showQuestion();

        nextButton.setOnClickListener(view -> {
            int selectedOption = optionsGroup.getCheckedRadioButtonId();
            if (selectedOption == -1) {
                Toast.makeText(MainActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton radioButton = findViewById(selectedOption);
            int answerIndex = optionsGroup.indexOfChild(radioButton);

            Log.d(TAG, "Selected answer index: " + answerIndex);
            Log.d(TAG, "Correct answer index: " + questions.get(currentQuestionIndex).getCorrectAnswer());

            if (answerIndex == questions.get(currentQuestionIndex).getCorrectAnswer()) {
                score++;
            }
            Log.d(TAG, "Current score: " + score);

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                showQuestion();
            } else {
                showScore();
            }
        });
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionText.setText(question.getQuestion());
        optionsGroup.removeAllViews();

        for (String option : question.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextColor(getResources().getColor(R.color.black));
            optionsGroup.addView(radioButton);
        }
    }

    private void showScore() {
        Intent intent = new Intent(MainActivity.this, Score.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", questions.size());
        startActivity(intent);
        // Finish MainActivity to prevent going back to it
        finish();
    }
}