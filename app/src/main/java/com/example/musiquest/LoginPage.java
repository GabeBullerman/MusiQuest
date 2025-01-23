package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.musiquest.HomePage;
import com.example.musiquest.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    Button registerButton;

    static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize UI elements after setContentView
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_string = username.getText().toString();
                String pass_string = password.getText().toString();


                // Create a JSON object to send to the server
                JSONObject postData = new JSONObject();
                try {
                    postData.put("username", user_string);
                    postData.put("password", pass_string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (user_string.equals("1") && pass_string.equals("1")) {
                    Intent goToHome = new Intent(LoginPage.this, HomePage.class);
                    goToHome.putExtra("username", user_string);
                    startActivity(goToHome);
                    Toast.makeText(LoginPage.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    ;
                }

                // Define the URL of your login endpoint
                String serverUrl = "http://yourserver.com/login";

                // Create a request queue
                com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);

                // Create a JsonObjectRequest for the GET request
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, serverUrl, postData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Check if the response contains a "success" field or some other indicator of success
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        user = user_string; // Store the username for later use if needed
                                        Intent goToHome = new Intent(LoginPage.this, HomePage.class);
                                        startActivity(goToHome);
                                        Toast.makeText(LoginPage.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginPage.this, "Invalid Credentials. Try again", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(LoginPage.this, "Error parsing server response", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error response
                                Toast.makeText(LoginPage.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                // Add the request to the request queue
                requestQueue.add(request);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegisterPage = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(toRegisterPage);
            }
        });
    }

    public static String getUsername() {
        return user;
    }
}
