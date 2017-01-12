package com.firebaseapp.wanarat.firebaseapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Wanarat on 1/11/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText nameText,lastnameText,EmailText,UsernameText, PasswordText;
    private Button btnbirth,btnsubmit;
    private DatabaseReference databaseReference;
    private TextView birthText;
    String MonthTH;
    Calendar dateTime = Calendar.getInstance();
    private ProgressDialog progressDialog;
    static  int old,day,months,years;

    private FirebaseAuth firebaseauth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        progressDialog = new ProgressDialog(this);

        firebaseauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nameText = (EditText) findViewById(R.id.Firstname);
        lastnameText = (EditText) findViewById(R.id.Lastname);
        EmailText = (EditText) findViewById(R.id.Email);
        UsernameText = (EditText) findViewById(R.id.Username);
        PasswordText = (EditText) findViewById(R.id.Password);
        btnbirth = (Button) findViewById(R.id.btn_birth);
        btnsubmit = (Button) findViewById(R.id.btn_submit);
        birthText = (TextView) findViewById(R.id.Birth);
        btnbirth.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);




    }
    private  void updateDate()
    {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR,year);
            dateTime.set(Calendar.MONTH,month);
            dateTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            String Day = String.valueOf(dateTime.get(Calendar.DAY_OF_MONTH));
            String Month = String.valueOf(dateTime.get(Calendar.MONTH)+1);
            String Year = String.valueOf(dateTime.get(Calendar.YEAR));
            day = Integer.parseInt(Day);
            months = Integer.parseInt(Month);
            years = Integer.parseInt(Year);
            birthText.setText(Day + " / " + SetTextMonth(Month) +" / " + Year);
            old = GetOld(day, months, years);

        }
    };

    public static int GetOld(int day,int month,int year){
        int old = 0,nowyear,nowday,nowmonth,sum =0;
        Calendar c = Calendar.getInstance();
        String Time = String.valueOf(c.get(Calendar.MONTH)+1);
        String Time1 = String.valueOf(c.get(Calendar.YEAR));
        String Time2 = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        nowyear = Integer.parseInt(Time1);
        nowday = Integer.parseInt(Time2);
        nowmonth = Integer.parseInt(Time);
        sum = nowyear-year;
        if(year == nowyear){
            old = sum;
        }else{
            if(month == nowmonth){
                if(day == nowday){
                    old = sum;
                }
                if(day > nowday){
                    old = sum;
                }else{
                    old = sum-1;
                }
            }
            if (month>nowmonth){
                old = sum-1;
            }
            if(month<nowmonth){
                old = sum;
            }
        }
        return old;
    }

    public String SetTextMonth(String month){
        if (month.equals("1")){
            MonthTH = "มกราคม";
        }
        if (month.equals("2")){
            MonthTH = "กุมภาพันธ์";
        }
        if (month.equals("3")){
            MonthTH = "มีนาคม";
        }
        if (month.equals("4")){
            MonthTH = "เมษายน";
        }
        if (month.equals("5")){
            MonthTH = "พฤษภาคม";
        }
        if (month.equals("6")){
            MonthTH = "มิถุนายน";
        }
        if (month.equals("7")){
            MonthTH = "กรกฎาคม";
        }
        if (month.equals("8")){
            MonthTH = "สิงหาคม";
        }
        if (month.equals("9")){
            MonthTH = "กันยายน";
        }
        if (month.equals("10")){
            MonthTH = "ตุลาคม";
        }
        if (month.equals("11")){
            MonthTH = "พฤศจิกายน";
        }
        if (month.equals("12")){
            MonthTH = "ธันวาคม";
        }
        return MonthTH;
    }
    private void registerUser(int old){
        final String firstname = nameText.getText().toString().trim();
        final String lastname = lastnameText.getText().toString().trim();
        final String Email = EmailText.getText().toString().trim();
        final String username = UsernameText.getText().toString().trim();
        String usernameSuccess = username + "@healthcare.com";
        final String password = PasswordText.getText().toString().trim();
        final String BirthDay = birthText.getText().toString().trim();
        final String olds = String.valueOf(old);

        if (TextUtils.isEmpty(firstname)){
            //email is empty
            Toast.makeText(this, "Please Enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(lastname)){
            //password is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Email)){
            //password is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(username)){
            //password is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(BirthDay)){
            //password is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User ....");
        progressDialog.show();



        firebaseauth.createUserWithEmailAndPassword(usernameSuccess,password) //Add your firebase
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseauth.getCurrentUser();
                            RegisterInfomation data = new RegisterInfomation(firstname,lastname,Email,username,password,BirthDay,
                                    olds," "," "," ","1");

                            databaseReference.child(user.getUid()).setValue(data);
                            Toast.makeText(Register.this, "Infomation Saved...", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),NavigationDrawer.class));

                        }else{
                            Log.d("Error >>>", "onComplete: Not TOaST");
                            Toast.makeText(Register.this, "๊Username ซ้ำกัน", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v == btnbirth) {
            updateDate();
        }
        if (v == btnsubmit) {
            registerUser(old);
        }
    }


}
