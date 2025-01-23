package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Dialog;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class HomePage extends AppCompatActivity {
    Button playButton;
    Button profileButton;
    Button leaderboardButton;
    Button alertsButton;





    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        playButton = findViewById(R.id.playButton);
        profileButton = findViewById(R.id.profileButton);
        leaderboardButton = findViewById(R.id.leaderboardButton);
        alertsButton = findViewById(R.id.alertsButton);
        String username = getIntent().getStringExtra("username");


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlaying = new Intent(HomePage.this, MainActivity.class);
                startPlaying.putExtra("username",username);
                startActivity(startPlaying);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfilePage = new Intent(HomePage.this, ProfilePage.class);
                toProfilePage.putExtra("username", username);
                startActivity(toProfilePage);
            }
        });
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLeaderboard = new Intent(HomePage.this, Leaderboard.class);
                toLeaderboard.putExtra("username", username);
                startActivity(toLeaderboard);
            }
        });
        alertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAlerts = new Intent(HomePage.this, Alerts.class);
                toAlerts.putExtra("username", username);
                startActivity(toAlerts);
            }
        });
    }
}