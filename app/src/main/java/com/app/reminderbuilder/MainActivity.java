package com.app.reminderbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import app.dev.reminder.ReminderBuilder;
import app.dev.reminder.exception.ReminderException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPressed(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        try {
            ReminderBuilder.with(this)
                    .setCallingIntent(intent)
                    .setNotificationTitle("Reminder Demo library")
                    .setNotificationBody("New event reminder for you")
                    .start(this,502);
        } catch (ReminderException e) {
            Log.d("Failed to start",""+e.getLocalizedMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //todo
    }
}