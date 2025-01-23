package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.toolbox.JsonArrayRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;



import org.java_websocket.WebSocket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Leaderboard extends AppCompatActivity implements WebSocketListener{

    Button playButton;
    Button homeButton;
    Button alertsButton;
    private WebSocket webSocket;
    private WebSocketClient webSocketClient;



    private String leaderList = "";

    private TextView msgResponse;


    private static final String URL_STRING_REQ = "ws://10.90.73.143:8080/leaderboardWS";
    private static final String URL_JSON_OBJECT = "http://10.90.73.143:8080/leaderboard/topScores?limit=" + 10;
    private static final String URL_JSON_ARRAY = "http://10.90.73.143:8080/leaderboard/topScores?limit=" + 10;

    ArrayList<Player> players = new ArrayList<>(); //array of players to add to board


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        playButton = findViewById(R.id.playButton);
        homeButton = findViewById(R.id.homeButton);
        alertsButton = findViewById(R.id.alertsButton);
        String username = getIntent().getStringExtra("username");

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startPlaying = new Intent(Leaderboard.this, MainActivity.class);
                startPlaying.putExtra("username", username);
                startActivity(startPlaying);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(Leaderboard.this, HomePage.class);
                goToHome.putExtra("username", username);
                startActivity(goToHome);
            }
        });
        alertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAlerts = new Intent(Leaderboard.this, Alerts.class);
                toAlerts.putExtra("username", username);
                startActivity(toAlerts);
            }
        });

        if(0==1) {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("JohnDoe", "1234", "999", "1"));
            players.add(new Player("JaneDoe", "1235", "950", "2"));
            players.add(new Player("JanDoe", "1235", "708", "3"));
            players.add(new Player("JayDoe", "1235", "510", "4"));
            players.add(new Player("JoneDoe", "1235", "441", "5"));
            players.add(new Player("JonnyDoe", "1234", "339", "6"));
            players.add(new Player("JimDoe", "1235", "200", "8"));
            players.add(new Player("JannyDoe", "1235", "178", "9"));
            players.add(new Player("JonaDoe", "1235", "100", "10"));
            players.add(new Player("JimmyDoe", "1235", "18", "11"));



        }
        //     makeStringReq();
        //     makeJsonObjReq();
        //     makeJsonArrayReq();
        String serverUrl = URL_STRING_REQ;

        // Establish WebSocket connection and set listener
        WebSocketManager.getInstance().connectWebSocket(serverUrl);
        WebSocketManager.getInstance().setWebSocketListener(Leaderboard.this);
    }


    private void setLeaderboard() {
        players = new ArrayList<>();


        CharSequence text = msgResponse.getText();

        leaderList = text.toString();


        //splitting up players
        String[] playerStrings = leaderList.substring(0, leaderList.length()).split(",");
        // starting rank
        int rank = 1;
        for (String playerString : playerStrings) {
            // Split the player string into name and score
            String[] parts = playerString.split(" ");
            String name = parts[0];
            //String extra = parts[1];
            String score = parts[2];

            // Create a new Player object, and add it to the list
            players.add(new Player(name, "000", score, String.valueOf(rank)));

            // Increment the rank for the next player
            rank++;
        }


    }
    private void makeStringReq() {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL_STRING_REQ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the successful response here
                        Log.d("Volley Response", response);

                        msgResponse = findViewById(R.id.msgResponse);
                        msgResponse.setText(response.toString());

                        setLeaderboard();
                        msgResponse= findViewById(R.id.msgResponse);

                        LeaderboardAdapter adapter;
                        adapter = new LeaderboardAdapter(Leaderboard.this, players);

                        ListView listView = findViewById(R.id.playerListView);
                        listView.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any errors that occur during the request
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    private void makeJsonArrayReq() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.GET,
                URL_JSON_ARRAY,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }
    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        runOnUiThread(() -> {
        Log.d("Websocket Response", message);

        msgResponse = findViewById(R.id.msgResponse);
        msgResponse.setText(message.toString());

        setLeaderboard();
        msgResponse= findViewById(R.id.msgResponse);

        LeaderboardAdapter adapter;
        adapter = new LeaderboardAdapter(Leaderboard.this, players);

        ListView listView = findViewById(R.id.playerListView);
        listView.setAdapter(adapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    WebSocketManager.getInstance().sendMessage("ahhhhhh");
                }
            }, 10000); // 10000 milliseconds = 10 seconds
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        runOnUiThread(() -> {
            String closedBy = remote ? "server" : "local";
            Toast.makeText(Leaderboard.this, "---\nconnection closed by " + closedBy + "\nreason: " + reason, Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        runOnUiThread(() -> {
            Toast.makeText(Leaderboard.this, "---\nconnection open by "  + "\nreason: " , Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onWebSocketError(Exception ex) {
        runOnUiThread(() -> {
            Toast.makeText(Leaderboard.this, "---\nconnection closed by "  + "\nreason: " , Toast.LENGTH_SHORT).show();

        });

    }
}


