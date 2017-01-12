package com.firebaseapp.wanarat.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Wanarat on 1/12/2017.
 */

public class MomProfile extends AppCompatActivity implements View.OnClickListener {
    private TextView firstnameText,lastnameText,oldText,oldwombText, lastText;
    private Button btnboold,btnuntral, btnEdit;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebasedatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momprofile);
        firstnameText = (TextView) findViewById(R.id.momname);
        lastnameText = (TextView) findViewById(R.id.lastname);
        oldText = (TextView) findViewById(R.id.Textold);
        oldwombText = (TextView) findViewById(R.id.oldwomb);
        lastText = (TextView) findViewById(R.id.lastperiod);
        btnboold = (Button) findViewById(R.id.btn_blood);
        btnuntral = (Button) findViewById(R.id.btn_ultrasound);
        btnEdit = (Button) findViewById(R.id.btn_Edit);
        btnboold.setOnClickListener(this);
        btnuntral.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebasedatabase = FirebaseDatabase.getInstance();
        GetData();

    }

    public void GetData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        databaseReference = firebasedatabase.getReference(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("firstname").getValue(String.class);
                String Value1 = dataSnapshot.child("lastname").getValue(String.class);
                String Value2 = dataSnapshot.child("old").getValue(String.class);
                //String Value3 = dataSnapshot.child("lastname").getValue(String.class);
                //String Value4 = dataSnapshot.child("lastname").getValue(String.class);
                firstnameText.setText(value);
                lastnameText.setText(Value1);
                oldText.setText(Value2);
                Log.d("Error>>", value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnboold){
            startActivity(new Intent(this, Blood.class));
        }
        if (v == btnuntral) {
            startActivity(new Intent(this, Testuploadimage.class));
        }
        if (v == btnEdit) {

        }
    }
}
