package com.azens1995.bootcampfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Variable declaration
    // visibility, type, variableName
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvRegister;

    // constants for the username and password
    public static final String LOGIN_USERNAME = "azens";
    public static final String LOGIN_PASSWORD = "test";
    public static final String LOGIN_STATUS = "login_status";
    public static final String LOGIN_PREF = "login_preferences";

    //shared preferences
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //variable initialization
        edtUsername = findViewById(R.id.input_username_main);
        edtPassword = findViewById(R.id.input_password_main);
        btnLogin = findViewById(R.id.login_main_button);
        tvRegister = findViewById(R.id.text_signUp_main);

        sharedPref = getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        //checking if user is already login
        boolean status = sharedPref.getBoolean(LOGIN_STATUS, false);
        Log.d(TAG, "onCreate: "+status);
        // If user is already logged in, open the home activity rather than login
        if (status){
            startHomeActivity();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if username and password are empty
                if( !(edtUsername.getText().toString().isEmpty() && edtPassword.getText().toString().isEmpty())){
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();
                    // check if username and password are as intended
                    if (username.equalsIgnoreCase(LOGIN_USERNAME) && password.equalsIgnoreCase(LOGIN_PASSWORD)){
                        // You are successful in authenticating user
                        // Do the things you wish to do
                        // Open new activity maybe

                        // sharedPreferences Key, Value pair
                        // for shared preferences

                        editor.putBoolean(LOGIN_STATUS, true);
                        editor.commit();
                        startHomeActivity();
                    }else {
                        // The user has input the wrong username or password
                        // Display the information to the user
                        Toast.makeText(LoginActivity.this, "Username or password is wrong. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    // Username or password is empty
                    // display the user message about it
                    Toast.makeText(LoginActivity.this, "Username or Password is empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Action to take care of when the user clicks the register text
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "You can register here.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Intent to open the home activity if the user is logged in
    private void startHomeActivity() {
        Intent loginSuccessIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(loginSuccessIntent);
        // finish to end the activity
        finish();
    }
}
