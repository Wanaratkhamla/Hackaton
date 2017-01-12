package com.firebaseapp.wanarat.firebaseapp;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestGetDatabase extends AppCompatActivity {
    private TextView textView;
    private DatabaseReference database;
    private FirebaseDatabase firebasedatabase;
    private Button readdata;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_get_database);
        textView = (TextView) findViewById(R.id.Getdata);
        readdata = (Button) findViewById(R.id.readData);

        firebasedatabase = FirebaseDatabase.getInstance();
        //FirebaseUser user = auth.getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        database = firebasedatabase.getReference(uid+"/name");

        Toast.makeText(this, "Infomation Saved..."+ uid, Toast.LENGTH_LONG).show();
        readdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        textView.setText(value);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }


}
