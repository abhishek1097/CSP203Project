package com.example.user.fazooz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Dubey_Order extends AppCompatActivity {
int minteger=0,minteger2=0;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dubey__order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Dubey Canteen");


    }


    RadioButton radioButton;

    public void rad(View view){
        radioButton=(RadioButton)findViewById(R.id.button4);
        if (radioButton.isChecked()) {
            if (!flag) {
                radioButton.setChecked(true);
                //  rdbtnMale.setChecked(false);
                flag = true;
                //flagmale = false;
            } else {
                flag = false;
                radioButton.setChecked(false);
                //rdbtnMale.setChecked(false);
            }
        }
        if(!radioButton.isChecked()){
            minteger = 0;
            minteger2 = 0;

        }
        else {
            minteger = 1;
            minteger2 = 10;
        }
        display2(minteger2);
        display(minteger);
    };


    public void increaseInteger(View view) {
        radioButton=(RadioButton)findViewById(R.id.button4);
        radioButton.setChecked(true);
        flag=true;
        minteger = minteger + 1;
        minteger2 = minteger2 + 10;
        display(minteger);
        display2(minteger2);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        minteger2 = minteger2 -10;
        if(minteger<0){
            minteger=0;
            minteger2=0;
            Toast.makeText(this,"Minimum quantity can't be negative !",Toast.LENGTH_SHORT).show();}

       if(minteger==0){
           radioButton=(RadioButton)findViewById(R.id.button4);
        radioButton.setChecked(false);
       flag=false;}
        display(minteger);
        display2(minteger2);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.textView7);
        displayInteger.setText("" + number);
    }
    private void display2(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.cost);
        displayInteger.setText("Rs: " + number);
    }


//    public void startlays(View view){
//        startActivity(new Intent(this,Cart.class));
//    }
    public void callphone(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:9494555549"));
        startActivity(intent);
    }
}
