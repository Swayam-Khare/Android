package com.swayam.wordgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {

    String finalWord;
    List<String> word_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        word_list = new ArrayList<>();
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.words));
        while (scanner.hasNext()) {
            word_list.add(scanner.nextLine());
        }

        startGame();
    }

    private void startGame() {
        finalWord = word_list.get(new Random().nextInt(word_list.size()));
    }
}