package com.example.musiquest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPage extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText email;
    private static String user_string;
    Button createAccount;
    Button backToLogin;
    String[] account_types = {"Student", "Teacher", "Solo"};
    AutoCompleteTextView AccountTypeDropdown;
    ArrayAdapter<String> AccountTypes;
    Button selectAvatar;
    Dialog avatarSelect;
    ImageButton av1;
    ImageButton av2;
    ImageButton av3;
    ImageButton av4;
    ImageButton av5;
    ImageButton av6;
    ImageButton av7;
    ImageButton av8;
    ImageButton av9;
    ImageButton av10;
    ImageButton av11;
    ImageButton av12;
    Button confirmSelection;
    static int avSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Initialize UI elements after setContentView
        email = findViewById(R.id.userEmail);
        username = findViewById(R.id.newUsername);
        password = findViewById(R.id.newPassword);
        createAccount = findViewById(R.id.createAccountButton);
        backToLogin = findViewById(R.id.backToLoginButton);
        AccountTypeDropdown = findViewById(R.id.AccountTypeDropdown);
        AccountTypes = new ArrayAdapter<>(RegisterPage.this, R.layout.account_dropdown, account_types);
        AccountTypeDropdown.setAdapter(AccountTypes);
        selectAvatar = findViewById(R.id.selectAvatarButton);
        avatarSelect = new Dialog(this);
        avSelection = 0;

        AccountTypeDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String AccountType = parent.getItemAtPosition(position).toString();
                if (AccountType.equals("Student")) {
                    Toast.makeText(RegisterPage.this, "Student Account Selected", Toast.LENGTH_SHORT).show();
                } else if (AccountType.equals("Teacher")) {
                    Toast.makeText(RegisterPage.this, "Teacher Account Selected", Toast.LENGTH_SHORT).show();
                } else if (AccountType.equals("Solo")) {
                    Toast.makeText(RegisterPage.this, "Solo Account Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToLogin = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(backToLogin);
            }
        });

        selectAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAvatars();
            }
        });



        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_string = email.getText().toString();
                String user_string = username.getText().toString();
                String pass_string = password.getText().toString();
                int userID = 1;

                if (email_string.length() == 0 || user_string.length() == 0 || pass_string.length() == 0) {
                    // if credentials are empty
                    Toast.makeText(RegisterPage.this, "Please Fill Out All Credentials", Toast.LENGTH_SHORT).show();
                } else if (!email_string.endsWith("@gmail.com") && !email_string.endsWith("@hotmail.com") && !email_string.endsWith("@yahoo.com") && !email_string.endsWith("@iastate.edu")) {
                    Toast.makeText(RegisterPage.this, "Please Enter A Valid Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    // if email is valid
                    com.android.volley.RequestQueue requestQueue;
                    JsonObjectRequest request; if (pass_string.length() < 8) {
                        Toast.makeText(RegisterPage.this, "Password Must Contain AT LEAST 8 Characters", Toast.LENGTH_SHORT).show();
                    } else if (!pass_string.matches(".*[!@#$%^&*].*")) {
                        Toast.makeText(RegisterPage.this, "Password Must Contain 1 Or More Special Character(s)", Toast.LENGTH_SHORT).show();
                    } else if (!pass_string.matches(".*[1234568790].*")) {
                        Toast.makeText(RegisterPage.this, "Password Must Contain 1 Or More Number(s)", Toast.LENGTH_SHORT).show();
                    } else if(returnAvatar() == 0){
                        Toast.makeText(RegisterPage.this, "Select an Avatar", Toast.LENGTH_SHORT).show();
                    } else if(AccountTypes.equals(null)){
                        Toast.makeText(RegisterPage.this, "Select Account Type", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Registration successful, send data to the server
                        // Construct user JSON object
                        JSONObject postData = new JSONObject();
                        try {
                            postData.put("email", email_string);
                            postData.put("name", user_string);
                            postData.put("password", pass_string);
                            postData.put("bio", "bruh");
                            postData.put("profilePicture", avSelection);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String serverUrl = "http://10.90.73.143:8080/solouser";

                        // Create a request queue
                        requestQueue = Volley.newRequestQueue(RegisterPage.this);

                        // Define the request
                        request = new JsonObjectRequest(Request.Method.POST, serverUrl, postData, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(RegisterPage.this, "Registration successful, posted to server!", Toast.LENGTH_SHORT).show();
                                Intent toHomePage = new Intent(RegisterPage.this, HomePage.class);
                                toHomePage.putExtra("username", user_string);
                                startActivity(toHomePage);
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        if (error instanceof NetworkError) {
                                            // Handle network-related errors (e.g., no internet connection)
                                            Toast.makeText(RegisterPage.this, "Network error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ServerError) {
                                            // Handle server errors (e.g., 5xx HTTP status codes)
                                            Toast.makeText(RegisterPage.this, "Server error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof AuthFailureError) {
                                            // Handle authentication failures
                                            Toast.makeText(RegisterPage.this, "Authentication failure", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ParseError) {
                                            // Handle parsing errors
                                            Toast.makeText(RegisterPage.this, "Parse error occurred", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof TimeoutError) {
                                            // Handle timeout errors
                                            Toast.makeText(RegisterPage.this, "Timeout error occurred", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Handle other Volley errors
                                            Toast.makeText(RegisterPage.this, "An error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        requestQueue.add(request);
                        ;
                    }
                    // Add the request to the request queue
                }
                }
            });
        }


    public static String getUsername() {
        return user_string;
    }
    public static int returnAvatar(){
        return avSelection;
    }

    public void showAvatars() {
        avatarSelect.setContentView(R.layout.avatar_selection);
        av1= avatarSelect.findViewById(R.id.av1);
        av2= avatarSelect.findViewById(R.id.av2);
        av3= avatarSelect.findViewById(R.id.av3);
        av4= avatarSelect.findViewById(R.id.av4);
        av5= avatarSelect.findViewById(R.id.av5);
        av6= avatarSelect.findViewById(R.id.av6);
        av7= avatarSelect.findViewById(R.id.av7);
        av8= avatarSelect.findViewById(R.id.av8);
        av9= avatarSelect.findViewById(R.id.av9);
        av10= avatarSelect.findViewById(R.id.av10);
        av11= avatarSelect.findViewById(R.id.av11);
        av12= avatarSelect.findViewById(R.id.av12);
        confirmSelection= avatarSelect.findViewById(R.id.ConfirmSelection);

        av1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 1;
                Toast.makeText(RegisterPage.this, "Avatar 1 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 2;
                Toast.makeText(RegisterPage.this, "Avatar 2 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 3;
                Toast.makeText(RegisterPage.this, "Avatar 3 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 4;
                Toast.makeText(RegisterPage.this, "Avatar 4 Selected", Toast.LENGTH_SHORT).show();
            }
        });av5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                avSelection = 5;
                Toast.makeText(RegisterPage.this, "Avatar 5 Selected", Toast.LENGTH_SHORT).show();
            }
        });

        av6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 6;
                Toast.makeText(RegisterPage.this, "Avatar 6 Selected", Toast.LENGTH_SHORT).show();
            }
        });

        av7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 7;
                Toast.makeText(RegisterPage.this, "Avatar 7 Selected", Toast.LENGTH_SHORT).show();
            }
        });

        av8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 8;
                Toast.makeText(RegisterPage.this, "Avatar 8 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 9;
                Toast.makeText(RegisterPage.this, "Avatar 9 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 10;
                Toast.makeText(RegisterPage.this, "Avatar 10 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 11;
                Toast.makeText(RegisterPage.this, "Avatar 11 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        av12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avSelection = 12;
                Toast.makeText(RegisterPage.this, "Avatar 12 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        confirmSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterPage.this, "Selection: Avatar " + avSelection + " Confirmed", Toast.LENGTH_SHORT).show();;
                avatarSelect.dismiss();
            }
        });
        avatarSelect.show();
    }
}
