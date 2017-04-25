package com.example.user.fazooz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    ProgressBar p;
    ImageView appIcon;
    TextView textView;
    public void init(){
        p=(ProgressBar) findViewById(R.id.progressBar2);
        p.setVisibility(View.INVISIBLE);
        p.postDelayed(new Runnable() {
            public void run() {
                p.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appIcon = (ImageView)findViewById(R.id.splash_screen);
        textView = (TextView)findViewById(R.id.splas_text);
        init();
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_left);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        textView.setAnimation(animation);
        appIcon.setAnimation(anim);




        new Thread(){

            public void run() {
                try {
                    sleep(4000);

                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
