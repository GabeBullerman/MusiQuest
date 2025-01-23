package com.example.musiquest;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.color.utilities.Score;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    int  LevelID = 1;
    Button menuBtn;
    Button BtnA;
    Button BtnB;
    Button BtnC;
    Button BtnD;

    Button submit;

    TextView questionview;

    ImageView Question;

    ImageView Book;
    ImageView BookStars1;


    ImageView Star1;
    ImageView Star2;
    ImageView Star3;

    TextView score;
    TextView ScorePopup;
    int currentQuestion = 0; //current question
    int scoreNum =0;// tracks score
    int questionCount =0;// stops users from playing last question forever

    int NumStars = 0; //number of stars

    String selectedAnswer = "";
    int User = 2;

    Dialog myDialog;
    Button replay;
    Button MainMenu;
    Button NextLevel;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myDialog = new Dialog(this);
        menuBtn= findViewById(R.id.menuBtn);
        //questions and answers
        questionview= findViewById(R.id.Question1);
        Question= findViewById(R.id.LevelQuestion);


        //Game Graphics
        Book= findViewById(R.id.Book);

        BookStars1= findViewById(R.id.Stars1);

        //game buttons and views
        score= findViewById(R.id.Score);
        BtnA= findViewById(R.id.BtnA);
        BtnB= findViewById(R.id.BtnB);
        BtnC= findViewById(R.id.BtnC);
        BtnD= findViewById(R.id.BtnD);
        submit= findViewById(R.id.submit);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        BtnA.setOnClickListener(this);
        BtnB.setOnClickListener(this);
        BtnC.setOnClickListener(this);
        BtnD.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadNewQuestion();



    }
    void loadNewQuestion(){
        //change text on question and each question
        questionview.setText(QandA.Q1[currentQuestion]);
        BtnA.setText(QandA.AO1[currentQuestion][0]);
        BtnB.setText(QandA.AO1[currentQuestion][1]);
        BtnC.setText(QandA.AO1[currentQuestion][2]);
        BtnD.setText(QandA.AO1[currentQuestion][3]);
        //change image
        if(currentQuestion==0){
            Question.setImageResource(R.drawable.l1q1);
        }
        else if(currentQuestion==1){
            Question.setImageResource(R.drawable.l1q2);
        }
        else if(currentQuestion==2){
            Question.setImageResource(R.drawable.l1q3);
            //Add Graphic
            Book.setImageResource(R.drawable.book_two);
        }
        else if(currentQuestion==3){
            Question.setImageResource(R.drawable.l1q4);
        }
        else if(currentQuestion==4){
            Question.setImageResource(R.drawable.l1q5);
        }
        else if(currentQuestion==5){
            Question.setImageResource(R.drawable.l1q6);
            //Add Graphic
            Book.setImageResource(R.drawable.book_three);
        }
        else if(currentQuestion==6){
            Question.setImageResource(R.drawable.l1q7);
        }
        else if(currentQuestion==7){
            Question.setImageResource(R.drawable.l1q8);
        }
        else if(currentQuestion==8){
            Question.setImageResource(R.drawable.l1q9);
            //Add Graphic
            Book.setImageResource(R.drawable.book_four);
        }
        else if(currentQuestion==9){
            Question.setImageResource(R.drawable.l1q10);
        }
        else if(currentQuestion==10){
            Question.setImageResource(R.drawable.l1q11);
            //Add Graphic
            Book.setImageResource(R.drawable.book_five);
        }

    }
    public void ShowPopup() {

        myDialog.setContentView(R.layout.stats_popup);

        MainMenu = myDialog.findViewById(R.id.MainMenu);
        NextLevel = myDialog.findViewById(R.id.NextLevel);
        ScorePopup = myDialog.findViewById(R.id.ScorePopup);

        Star1= myDialog.findViewById(R.id.Star1);
        Star2= myDialog.findViewById(R.id.Star2);
        Star3= myDialog.findViewById(R.id.Star3);

        if(scoreNum < currentQuestion/3){
            //hide stars
            Star1.setVisibility(View.INVISIBLE);
            Star2.setVisibility(View.INVISIBLE);
            Star3.setVisibility(View.INVISIBLE);
            //set score display
            ScorePopup.setText("You Got 0 Stars");
            NumStars = 0;

        }else if(scoreNum <= currentQuestion/2){
            //Hide stars
            Star2.setVisibility(View.INVISIBLE);
            Star3.setVisibility(View.INVISIBLE);
            // set score display
            ScorePopup.setText("You Got 1 Stars!");
            NumStars = 1;
        }else if(scoreNum <= currentQuestion-(currentQuestion/3)){
            //Hide stars
            Star3.setVisibility(View.INVISIBLE);
            // set score display
            ScorePopup.setText("You Got 2 Stars!!");
            NumStars = 2;
        }else{
            ScorePopup.setText("You Got 3 Stars!!!");
            NumStars = 3;
        }

        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(GameActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        myDialog.show();
    }
    @Override
    public void onClick(View v) {
        //setting color for buttons
        BtnA.setBackgroundColor(Color.BLACK);
        BtnB.setBackgroundColor(Color.BLACK);
        BtnC.setBackgroundColor(Color.BLACK);
        BtnD.setBackgroundColor(Color.BLACK);

        Button clickedButton = (Button) v;
        //if submiting answer and there are more questions
        if(clickedButton.getId() == R.id.submit && currentQuestion+1 != QandA.Q1.length){
            //correct answer?
            if(selectedAnswer.equals(QandA.A1[currentQuestion])) {
                scoreNum++;
                score.setText("Score: " + scoreNum);
            }

            //next question
            currentQuestion++;
            questionCount++;
            loadNewQuestion();
        }
        //there are no more questions level over
        else if (clickedButton.getId() == R.id.submit && currentQuestion+1 == QandA.Q1.length) {
            if(selectedAnswer.equals(QandA.A1[currentQuestion]) && currentQuestion == questionCount) {
                scoreNum++;
                score.setText("Score: " + scoreNum);
            }
            questionCount++;

            //eng game Graphics
            BookStars1.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(BookStars1, "rotation", 0f, 360f);
            animator.setDuration(3000);  // Set the animation duration to 6 seconds

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (1 == 1) {
                        JSONObject postData = new JSONObject();
                        try {
                            //User = LoginPage.getUsername();
                            postData.put("ID", 1);
                            postData.put("Score", scoreNum);
                            //postData.put("Level", LevelID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Ahhhhh this needs changed
                        String serverUrl = "http://10.90.73.143:8080/leaderboard/addScore?accountId=" + User + "&score="+scoreNum ;
                        // IDK what this line is but its needed
                        com.android.volley.RequestQueue requestQueue;
                        JsonObjectRequest request;
                        // Create a request queue
                        requestQueue = Volley.newRequestQueue(GameActivity.this);

                        // Define the request
                        request = new JsonObjectRequest(Request.Method.POST, serverUrl, postData, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //working?
                                Toast.makeText(GameActivity.this, "Score posted to server!", Toast.LENGTH_SHORT).show();
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        if (error instanceof NetworkError) {
                                            // Handle network-related errors (e.g., no internet connection)
                                            Toast.makeText(GameActivity.this, "Network error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ServerError) {
                                            // Handle server errors (e.g., 5xx HTTP status codes)
                                            Toast.makeText(GameActivity.this, "Server error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof AuthFailureError) {
                                            // Handle authentication failures
                                            Toast.makeText(GameActivity.this, "Authentication failure", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ParseError) {
                                            // Handle parsing errors
                                            Toast.makeText(GameActivity.this, "Parse error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof TimeoutError) {
                                            // Handle timeout errors
                                            Toast.makeText(GameActivity.this, "Timeout error occurred", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Handle other Volley errors
                                            Toast.makeText(GameActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        requestQueue.add(request);
                        ;
                    }

                    ShowPopup();
                }

            });

            animator.start();
        }
        //selecting anwser
        else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }

    }

}