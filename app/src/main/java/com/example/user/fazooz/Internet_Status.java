package com.example.user.fazooz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Internet_Status extends AppCompatActivity {
String acivity_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet__status);
        Intent intent = getIntent();
        acivity_name= intent.getStringExtra("activity name");
        Button internet_button=(Button)findViewById(R.id.reload);
        internet_button.setOnClickListener(new View.OnClickListener() {
            //
            @Override
            public void onClick(View v) {
                MyGlobals myGlobals = new MyGlobals(getApplicationContext());
                boolean boo;
                boo=myGlobals.isNetworkConnected();
                String status ="0";
                if(boo==true)
                    status="1";
                if(status=="1"){
//                   Intent myintent = new Intent(this,acivity_name.class);
//                    myintent.putExtra("activity name", activity_name);

//                    startActivity(myintent);
                    finish();
                }

            }
        });
    }
}
