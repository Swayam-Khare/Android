package com.swayam.wordgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {

    String finalWord;
    List<String> word_list;
    EditText inputField;
    TextView[] wordLayout;
    List<Character> correctWords;
    int numOfGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        correctWords = new ArrayList<>(5);
        wordLayout = new TextView[5];

        wordLayout[0] = findViewById(R.id.first_letter);
        wordLayout[1] = findViewById(R.id.second_letter);
        wordLayout[2] = findViewById(R.id.third_letter);
        wordLayout[3] = findViewById(R.id.fourth_letter);
        wordLayout[4] = findViewById(R.id.fifth_letter);

        inputField = findViewById(R.id.input_field);

        word_list = new ArrayList<>();
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.words));
        while (scanner.hasNext()) {
            word_list.add(scanner.nextLine());
        }

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            String userGuess = inputField.getText().toString();
            inputField.setText("");
            correctWords.clear();
            checkGuess(userGuess);
        });

        startGame();
    }

    private void startGame() {
        numOfGuesses = 6;
        finalWord = word_list.get(new Random().nextInt(word_list.size())).toUpperCase();
        Log.i("GameActivity.class", "Answer: " + finalWord);
        inputField.setText("");
    }

    private void checkGuess(String userGuess) {
        char user;
        char answer;
        numOfGuesses--;
        for (int i = 0; i < userGuess.length(); i++) {
            user = userGuess.charAt(i);
            answer = finalWord.charAt(i);
            String guess = user + "";
            wordLayout[i].setText(guess);

            if (user == answer) {
                correctWords.add(user);
                wordLayout[i].setBackgroundColor(ContextCompat.getColor(this, R.color.correct));

            } else {
                if (!correctWords.contains(user) && contains(finalWord, user)){
                    wordLayout[i].setBackgroundColor(ContextCompat.getColor(this, R.color.half_correct));
                }
                else {
                    wordLayout[i].setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
                }
            }
        }


    }

    private boolean contains(String finalWord, char user) {
        for (int i = 0; i < finalWord.length(); i++) {
            if (finalWord.charAt(i) == user){
                return true;
            }
        }
        return false;
    }
}
