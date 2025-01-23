package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProfilePage extends AppCompatActivity {

    Button playButton;
    Button homeButton;
    Button alertsButton;
    Button saveBio;
    EditText bioText;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize your UI elements
        playButton = findViewById(R.id.playButton);
        homeButton = findViewById(R.id.homeButton);
        alertsButton = findViewById(R.id.alertsButton);
        saveBio = findViewById(R.id.editBio);
        bioText = findViewById(R.id.bioText);

        // Load the saved bio from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedBio = sharedPreferences.getString("userBio", "");

        // Set the saved bio to the EditText
        bioText.setText(savedBio);

        ImageView avatar = findViewById(R.id.profilePicture);

        username = getIntent().getStringExtra("username");
        TextView usernameToDisplay = findViewById(R.id.usernameToDisplay);
        usernameToDisplay.setText(username);


        int selectedAvatar = RegisterPage.returnAvatar(); // Get the user's selected avatar

        switch (selectedAvatar) {
            case 1:
                avatar.setImageResource(R.drawable.blackhairboyav);
                break;
            case 2:
                avatar.setImageResource(R.drawable.blackhairgirlav);
                break;
            case 3:
                avatar.setImageResource(R.drawable.blondehairboyav);
                break;
            case 4:
                avatar.setImageResource(R.drawable.blondehairgirlav);
                break;
            case 5:
                avatar.setImageResource(R.drawable.brownhairboyav);
                break;
            case 6:
                avatar.setImageResource(R.drawable.brownhairgirlav);
                break;
            case 7:
                avatar.setImageResource(R.drawable.blackhairboyav2);
                break;
            case 8:
                avatar.setImageResource(R.drawable.blackhairgirlav2);
                break;
            case 9:
                avatar.setImageResource(R.drawable.foxav);
                break;
            case 10:
                avatar.setImageResource(R.drawable.dogav);
                break;
            case 11:
                avatar.setImageResource(R.drawable.catav);
                break;
            case 12:
                avatar.setImageResource(R.drawable.pandaav);
                break;
            case 0:
                avatar.setImageResource(R.drawable.dogav); // Use default if no selection
                break;
        }

        saveBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the bio text from the EditText
                String bio = bioText.getText().toString();
                // Save the bio to shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userBio", bio);
                editor.apply();
                Toast.makeText(ProfilePage.this, "Bio Saved", Toast.LENGTH_SHORT).show();
            }
        });



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlaying = new Intent(ProfilePage.this, MainActivity.class);
                startPlaying.putExtra("username",username);
                startActivity(startPlaying);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(ProfilePage.this, HomePage.class);
                goToHome.putExtra("username",username);
                startActivity(goToHome);
            }
        });
        alertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAlerts = new Intent(ProfilePage.this, Alerts.class);
                toAlerts.putExtra("username",username);
                startActivity(toAlerts);
            }
        });
    }
}