package com.firebaseapp.wanarat.firebaseapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogoApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstlogo);

        CountDownTimer cdt = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) { //Delay Time your App
                // Tick
            }
            public void onFinish() { //After Delay Time
                // Finish
                startActivity(new Intent(getApplicationContext(),FirstOpen.class));
            }
        }.start();
    }
}
