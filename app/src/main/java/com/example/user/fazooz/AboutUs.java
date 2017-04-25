package com.example.user.fazooz;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {

public  Button button10;

    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    gmail();
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        button10 = (Button) view.findViewById(R.id.button6);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "customercarefoodzie@gmail.com"));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }

            }
        });
        return view;
    }
//
//    public void init(View view){
////
//        Log.e("afsdfsf", "init: ");
//    }


}
