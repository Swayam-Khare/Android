package com.swayam.wordgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(v -> {
            playButton.setElevation(0f);
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        });

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(v -> {
            exitButton.setElevation(0f);
            finish();
        });
    }
}