package com.zybooks.battagliaeventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity extends AppCompatActivity {

    private EditText editTextEventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // Initialize UI components
        editTextEventName = findViewById(R.id.editTextEventName);

        // Set up event listener for save button
        Button buttonSaveEvent = findViewById(R.id.buttonSaveEvent);
        buttonSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

    // Method to handle saving the event
    private void saveEvent() {
        // Get data from UI components
        String eventName = editTextEventName.getText().toString().trim();

        // Validate data (optional)
        if (eventName.isEmpty()) {
            editTextEventName.setError("Please enter event name");
            editTextEventName.requestFocus();
            return;
        }

        // Create an intent to pass back the new event data to the calling activity (GridActivity)
        Intent resultIntent = new Intent();
        resultIntent.putExtra("eventName", eventName);
        setResult(RESULT_OK, resultIntent);

        // Finish the activity to return to the calling activity (GridActivity)
        finish();
    }
}