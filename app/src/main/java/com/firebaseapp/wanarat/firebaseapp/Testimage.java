package com.firebaseapp.wanarat.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.firebaseapp.wanarat.firebaseapp.R.id.imageView;

public class Testimage extends AppCompatActivity {
    private ImageView imtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimage);
        imtest = (ImageView) findViewById(R.id.image123);
        Picasso.with(this).load(R.drawable.blood).into(imtest);
    }
}
