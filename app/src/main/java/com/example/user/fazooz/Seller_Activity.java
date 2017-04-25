package com.example.user.fazooz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Seller_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_);
        Intent intent = getIntent();

        String name  = intent.getStringExtra("name");
        TextView name1 = (TextView)findViewById(R.id.seller_name);
        name1.setText("Hello "+name);
    }
}
