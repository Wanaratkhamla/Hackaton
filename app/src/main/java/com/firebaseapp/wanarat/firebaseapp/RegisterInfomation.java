package com.firebaseapp.wanarat.firebaseapp;

/**
 * Created by Wanarat on 1/12/2017.
 */

public class RegisterInfomation {
    public String firstname;
    public String lastname;
    public String Email;
    public String username;
    public String password;
    public String BirthDay;
    public String old;
    public String weight;
    public String height;
    public String period;
    public String oldworb;

    public RegisterInfomation(String firstname, String lastname, String Email, String username, String password
            , String BirthDay, String old, String weight, String height, String period, String oldworb){
        this.firstname = firstname;
        this.lastname = lastname;
        this.Email = Email;
        this.username = username;
        this.password = password;
        this.BirthDay = BirthDay;
        this.old = old;
        this.weight = weight;
        this.height = height;
        this.period = period;
        this.oldworb = oldworb;


    }
}
