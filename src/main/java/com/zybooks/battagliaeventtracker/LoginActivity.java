package com.zybooks.battagliaeventtracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonSignIn, buttonCreateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonCreateProfile = findViewById(R.id.buttonCreateProfile);

        buttonSignIn.setOnClickListener(v -> signIn());

        buttonCreateProfile.setOnClickListener(v -> createProfile());
    }

    private void signIn() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        LoginDatabaseHelper databaseHelper = new LoginDatabaseHelper(this);

        // Perform authentication here (check username and password)
        // If authentication successful, proceed to next screen
        // Otherwise, display an error message
        // For demonstration purposes, assume authentication is successful
        if (databaseHelper.authenticateUser(username, password)) {
            // Authentication successful, navigate to the next screen
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            // Start new activity
            Intent intent = new Intent(LoginActivity.this, GridActivity.class);
            startActivity(intent);
        } else {
            // Authentication failed, display an error message
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void createProfile() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        LoginDatabaseHelper databaseHelper = new LoginDatabaseHelper(this);

        // Perform profile creation here (add username and password to database)
        // For demonstration purposes, just show a Toast message

        if (databaseHelper.doesUserExist(username)) {
            // User already exists, display a message
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            // Add new user to the database
            databaseHelper.addUser(username, password);
            // For demonstration purposes, just show a Toast message
            Toast.makeText(this, "Profile created successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace this with your authentication logic
        // For demonstration purposes, return true if username and password match hardcoded values
        return username.equals("admin") && password.equals("admin");
    }
}