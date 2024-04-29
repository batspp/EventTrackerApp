package com.zybooks.battagliaeventtracker;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check SMS permission
        if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.SEND_SMS)) {
            // Permission already granted, proceed with sending SMS
            sendSMS();
        } else {
            // Permission not granted, request permission from the user
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with sending SMS
                sendSMS();
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "SMS permission denied. The app will continue to function.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSMS() {
        // Implement sending SMS logic here
        // This is just a placeholder method
        Toast.makeText(this, "Sending SMS...", Toast.LENGTH_SHORT).show();
    }
}