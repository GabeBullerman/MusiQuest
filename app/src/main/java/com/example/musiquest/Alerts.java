package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Alerts extends AppCompatActivity {

    Button playButton;
    Button homeButton;
    Button liveChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        playButton = findViewById(R.id.playButton);
        homeButton = findViewById(R.id.homeButton);
        liveChatButton = findViewById(R.id.liveChatButton);
        String username = getIntent().getStringExtra("username");
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlaying = new Intent(Alerts.this, MainActivity.class);
                startPlaying.putExtra("username",username);
                startActivity(startPlaying);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHome = new Intent(Alerts.this, HomePage.class);
                goToHome.putExtra("username",username);
                startActivity(goToHome);
            }
        });
        liveChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toChat = new Intent(Alerts.this, WebSocketMain.class);
                toChat.putExtra("username",username);
                startActivity(toChat);
            }
        });

    }
}