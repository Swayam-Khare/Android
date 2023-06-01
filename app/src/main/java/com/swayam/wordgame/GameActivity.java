package com.swayam.wordgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SuppressLint("SetTextI18n")
public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = "GameActivity.java";
    String finalWord;
    List<String> word_list;
    EditText inputField;
    TextView[] wordLayout, allWordLayout;
    List<Character> correctWords;
    int numOfGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        correctWords = new ArrayList<>(5);
        wordLayout = new TextView[5];
        allWordLayout = new TextView[30];

        allWordLayout[0] = findViewById(R.id.first_letter1);
        allWordLayout[1] = findViewById(R.id.second_letter1);
        allWordLayout[2] = findViewById(R.id.third_letter1);
        allWordLayout[3] = findViewById(R.id.fourth_letter1);
        allWordLayout[4] = findViewById(R.id.fifth_letter1);
        allWordLayout[5] = findViewById(R.id.first_letter2);
        allWordLayout[6] = findViewById(R.id.second_letter2);
        allWordLayout[7] = findViewById(R.id.third_letter2);
        allWordLayout[8] = findViewById(R.id.fourth_letter2);
        allWordLayout[9] = findViewById(R.id.fifth_letter2);
        allWordLayout[10] = findViewById(R.id.first_letter3);
        allWordLayout[11] = findViewById(R.id.second_letter3);
        allWordLayout[12] = findViewById(R.id.third_letter3);
        allWordLayout[13] = findViewById(R.id.fourth_letter3);
        allWordLayout[14] = findViewById(R.id.fifth_letter3);
        allWordLayout[15] = findViewById(R.id.first_letter4);
        allWordLayout[16] = findViewById(R.id.second_letter4);
        allWordLayout[17] = findViewById(R.id.third_letter4);
        allWordLayout[18] = findViewById(R.id.fourth_letter4);
        allWordLayout[19] = findViewById(R.id.fifth_letter4);
        allWordLayout[20] = findViewById(R.id.first_letter5);
        allWordLayout[21] = findViewById(R.id.second_letter5);
        allWordLayout[22] = findViewById(R.id.third_letter5);
        allWordLayout[23] = findViewById(R.id.fourth_letter5);
        allWordLayout[24] = findViewById(R.id.fifth_letter5);
        allWordLayout[25] = findViewById(R.id.first_letter6);
        allWordLayout[26] = findViewById(R.id.second_letter6);
        allWordLayout[27] = findViewById(R.id.third_letter6);
        allWordLayout[28] = findViewById(R.id.fourth_letter6);
        allWordLayout[29] = findViewById(R.id.fifth_letter6);

        inputField = findViewById(R.id.input_field);

        word_list = new ArrayList<>();
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.words));
        while (scanner.hasNext()) {
            word_list.add(scanner.nextLine());
        }
        scanner.close();

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            String userGuess = inputField.getText().toString();
            inputField.setText("");
            correctWords.clear();
            checkGuess(userGuess);
            highlightNextSlots();
        });

        startGame();
    }

    private void startGame() {
        numOfGuesses = 6;
        finalWord = word_list.get(new Random().nextInt(word_list.size())).toUpperCase();
        Log.i(LOG_TAG, "Answer: " + finalWord);
        inputField.setText("");

        wordLayout[0] = allWordLayout[0];
        wordLayout[1] = allWordLayout[1];
        wordLayout[2] = allWordLayout[2];
        wordLayout[3] = allWordLayout[3];
        wordLayout[4] = allWordLayout[4];

        for (TextView wordView : allWordLayout) {
            wordView.setText("");
            wordView.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.normal_txt_bg, getTheme()));
        }
    }

    private void highlightNextSlots() {
        switch (numOfGuesses) {
            case 5:
                wordLayout[0] = findViewById(R.id.first_letter2);
                wordLayout[1] = findViewById(R.id.second_letter2);
                wordLayout[2] = findViewById(R.id.third_letter2);
                wordLayout[3] = findViewById(R.id.fourth_letter2);
                wordLayout[4] = findViewById(R.id.fifth_letter2);
                break;
            case 4:
                wordLayout[0] = findViewById(R.id.first_letter3);
                wordLayout[1] = findViewById(R.id.second_letter3);
                wordLayout[2] = findViewById(R.id.third_letter3);
                wordLayout[3] = findViewById(R.id.fourth_letter3);
                wordLayout[4] = findViewById(R.id.fifth_letter3);
                break;
            case 3:
                wordLayout[0] = findViewById(R.id.first_letter4);
                wordLayout[1] = findViewById(R.id.second_letter4);
                wordLayout[2] = findViewById(R.id.third_letter4);
                wordLayout[3] = findViewById(R.id.fourth_letter4);
                wordLayout[4] = findViewById(R.id.fifth_letter4);
                break;
            case 2:
                wordLayout[0] = findViewById(R.id.first_letter5);
                wordLayout[1] = findViewById(R.id.second_letter5);
                wordLayout[2] = findViewById(R.id.third_letter5);
                wordLayout[3] = findViewById(R.id.fourth_letter5);
                wordLayout[4] = findViewById(R.id.fifth_letter5);
                break;
            default:
                wordLayout[0] = findViewById(R.id.first_letter6);
                wordLayout[1] = findViewById(R.id.second_letter6);
                wordLayout[2] = findViewById(R.id.third_letter6);
                wordLayout[3] = findViewById(R.id.fourth_letter6);
                wordLayout[4] = findViewById(R.id.fifth_letter6);
        }
    }

    private void checkGuess(String userGuess) {

        if (userGuess.length() != 5) {
            Toast.makeText(this, "Enter a 5 letter guess", Toast.LENGTH_SHORT).show();
            return;
        }

        char user;
        char answer;

        numOfGuesses--;

        for (int i = 0; i < userGuess.length(); i++) {
            user = userGuess.charAt(i);
            answer = finalWord.charAt(i);
            wordLayout[i].setText(user + "");

            if (user == answer) {
                correctWords.add(user);
            }
        }

        for (int i = 0; i < userGuess.length(); i++) {
            user = userGuess.charAt(i);
            answer = finalWord.charAt(i);

            if (user == answer) {
                wordLayout[i].setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.correct_txt_bg, getTheme()));
            }
            else if (!correctWords.contains(user) && contains(finalWord, user)) {
                wordLayout[i].setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.halfcorrect_txt_bg, getTheme()));
            } else {
                wordLayout[i].setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.incorrect_txt_bg, getTheme()));
            }
        }

        if (correctWords.size() == 5) {
            showAlertDialog("Correct!", "You guessed the word: ", "New Word");
            return;
        }

        if (numOfGuesses < 1) {
            showAlertDialog("Nice Try!", "The word was: ", "Try Again");
        }
    }

    private void showAlertDialog(String title, String message, String buttonText) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        dialogBuilder
                .setTitle(title)
                .setCancelable(false)
                .setMessage(message + finalWord)
                .setNegativeButton(buttonText, (d, e) -> {
                    startGame();
                    d.dismiss();
                })
                .setPositiveButton("Exit", (d, e) -> {
                    d.dismiss();
                    finish();
                });

        dialogBuilder.create().show();
    }

    private boolean contains(String finalWord, char user) {
        for (int i = 0; i < finalWord.length(); i++) {
            if (finalWord.charAt(i) == user) {
                return true;
            }
        }
        return false;
    }
}
