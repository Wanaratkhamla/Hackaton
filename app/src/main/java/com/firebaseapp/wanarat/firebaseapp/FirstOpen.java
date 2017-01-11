package com.firebaseapp.wanarat.firebaseapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;


public class FirstOpen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        class MyApp extends android.app.Application {
            @Override
            public void onCreate() {
                super.onCreate();
                setOffile();
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_open);
    }
    CountDownTimer cdt = new CountDownTimer(3000, 1000) {
        public void onTick(long millisUntilFinished) { //Delay Time your App
            // Tick
        }
        public void onFinish() { //After Delay Time
            // Finish
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }.start();

    public void setOffile(){   //open App Offline
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
