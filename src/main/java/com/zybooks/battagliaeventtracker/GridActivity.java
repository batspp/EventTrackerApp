package com.zybooks.battagliaeventtracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_EDIT_EVENT = 1;
    private static final int REQUEST_SMS_PERMISSION = 123;

    private EventsDatabaseHelper databaseHelper;
    private Cursor eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new EventsDatabaseHelper(this);
        eventList = databaseHelper.getAllEvents();
        eventAdapter = new EventAdapter(cursorToEventList(eventList));
        recyclerView.setAdapter(eventAdapter);

        FloatingActionButton fabAddEvent = findViewById(R.id.fabAddEvent);
        fabAddEvent.setOnClickListener(this::onClick);

        RadioButton permissionRadioButton = findViewById(R.id.permissionRadioButton);
        permissionRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                toggleSmsPermission();
            }
        });
    }

    private List<Event> cursorToEventList(Cursor cursor) {
        List<Event> eventList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String eventName = cursor.getString(cursor.getColumnIndex(EventsDatabaseHelper.COLUMN_EVENT_NAME));
                eventList.add(new Event(eventName));
            } while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
        return eventList;
    }

    private void toggleSmsPermission() {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request permission from the user
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
        } else {
            // Permission already granted, inform the user
            Toast.makeText(this, "SMS permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, inform the user
                Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_EDIT_EVENT && resultCode == RESULT_OK && data != null) {
            // Retrieve the new event data from the intent
            String eventName = data.getStringExtra("eventName");
            // Add the new event to the database
            long result = new Event(eventName).saveToDatabase(databaseHelper);
            // Event saved successfully, update the UI
            if (result != -1) eventAdapter.notifyDataSetChanged();
            else {
                // Error occurred while saving event, inform the user
                Toast.makeText(this, "Failed to save event", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onClick(View v) {
        // Start AddEditActivity to add a new event
        Intent intent = new Intent(GridActivity.this, AddEditActivity.class);
        startActivityForResult(intent, REQUEST_ADD_EDIT_EVENT);
    }
}