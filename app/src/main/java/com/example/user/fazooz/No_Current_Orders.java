package com.example.user.fazooz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class No_Current_Orders extends AppCompatActivity {
    String username,name,email,address,category,restraunt,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no__current__orders);
        Intent intent = getIntent();
        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");
    }


    @Override
    public boolean  onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        Intent myIntent=new Intent(this,Fazooz.class);
        myIntent.putExtra("username",username);
        myIntent.putExtra("name",name);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("address",address);
        setResult(RESULT_OK,myIntent);
        finish();
    }

}
