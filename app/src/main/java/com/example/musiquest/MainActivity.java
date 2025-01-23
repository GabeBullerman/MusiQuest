package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.interfaces.DSAPrivateKey;

public class MainActivity extends AppCompatActivity {

    Button Lvl1Play;
    Button Dashboard;
    Button Play;
    Button Cancel;

    Intent playLevel;
    Dialog myDialog;
    String level = "";
    TextView levelText;
    TextView UserView;
    ImageView ship;
    String User = "1";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        //setting views
        myDialog = new Dialog(this);
        Lvl1Play= findViewById(R.id.Lvl1Play);
        Dashboard= findViewById(R.id.Dashboard);
        UserView= findViewById(R.id.userView);

        //finding User
        //finding Users name
       // User = LoginPage.getUsername();
        User = User +"'s Islands";
        UserView.setText(User);

        ship = findViewById(R.id.ship);
        // Set an OnTouchListener for the entire screen
        // Set an OnTouchListener for the entire screen
        //this allows the ship to move around the screen
        View mainLayout = findViewById(R.id.backgroundmap); // Replace with your main layout ID
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Calculate the new coordinates based on the touch event
                float newX = event.getX() - ship.getWidth() / 2;
                float newY = event.getY() - ship.getHeight() / 2;

                // Move the object to the new coordinates
                ship.setX(newX);
                ship.setY(newY);

                // Optional: Animate the object
                Animation animation = new TranslateAnimation(0, newX - ship.getX(), 0, newY - ship.getY());
                animation.setDuration(1000);
                ship.startAnimation(animation);

                return true;
            }
        });


        //start buttons
        //level 1
        Lvl1Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //set text
                level = "Play Level 1?";

                //set button action
                playLevel = new Intent(MainActivity.this, GameActivity.class);
                ShowPopup();
            }
        });
        Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDashboard = new Intent(MainActivity.this, HomePage.class);
                toDashboard.putExtra("username",username);
                startActivity(toDashboard);
            }
        });
    }
    public void ShowPopup() {

        myDialog.setContentView(R.layout.map_popup);

        //buttons
        Play = myDialog.findViewById(R.id.Play);
        Cancel = myDialog.findViewById(R.id.Cancel);
        //view
        levelText = myDialog.findViewById(R.id.textView3);

        levelText.setText(level);


        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(playLevel);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();
            }
        });

        myDialog.show();


    }
    private void moveObjectToClick(View v, MotionEvent event) {
        float x = v.getX();
        float y = v.getY();

        float newX = event.getX();
        float newY = event.getY();

        Animation animation = new TranslateAnimation(0, newX - x, 0, newY - y);
        animation.setDuration(3000);


        v.startAnimation(animation);

        // Update the position of the object after the animation (optional)
        v.setX(newX);
        v.setY(newY);
    }
}




