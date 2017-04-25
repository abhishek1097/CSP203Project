package com.example.user.fazooz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CurrentActivity extends AppCompatActivity {
    String username,name,email,address,category,restraunt,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        Intent intent = getIntent();
        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");

    }




//
//    public void ordernow(View view){
//        Intent myintent;
//        myintent = new Intent(this,OrderActivity.class);
//        myintent.putExtra("name", name);
//        myintent.putExtra("username",username);
//        myintent.putExtra("email",email);
//        myintent.putExtra("mobile",mobile);
//        myintent.putExtra("category",category);
//        myintent.putExtra("address",address);
//        myintent.putExtra("restraunt",restraunt);
//        startActivity(myintent);
//    }



}
