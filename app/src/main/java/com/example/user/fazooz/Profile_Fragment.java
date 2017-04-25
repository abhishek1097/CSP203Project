package com.example.user.fazooz;


import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String susername,sphone_number,saddress,semail,sname;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
//    private static final String ARG_PARAM6 = "param6";
//    private static final String ARG_PARAM7 = "param7";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
//    private String mParam6;
//    private String mParam7;


    public ImageButton button;
    private ImageView imageView;
    private Button camera,gallery;
    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE_REQUEST = 2;
    final int PIC_CROP = 3;
    //captured picture uri
    private Uri picUri;

    public Profile_Fragment() {
        // Required empty public constructor
    }



    public static Profile_Fragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setnames(view);
        photoset(view);
        clickers(view);



        return view;
    }

    private void clickers(View view) {
        usernameclicker(view);
        phonenumberclicker(view);
        addressclicker(view);
        email(view);

    }

    private void email(View view) {

        ImageButton addressb=(ImageButton)view.findViewById(R.id.mail_edit);
        final TextView temail=(TextView)view.findViewById(R.id.mail);
        addressb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.edit_email_dialog,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Type the email");
                final EditText editted_email=(EditText)mview.findViewById(R.id.editemaild);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                semail = editted_email.getText().toString();
                                if(semail.length()>=3&&android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
                                    temail.setText(semail);
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                semail = editted_email.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
            }
        });

    }

    private void addressclicker(View view) {

        ImageButton username=(ImageButton)view.findViewById(R.id.address_edit);
        final TextView taddress=(TextView)view.findViewById(R.id.address);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.edit_address_dialog,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Type the address");
                final EditText editted_address=(EditText)mview.findViewById(R.id.editaddress);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                saddress = editted_address.getText().toString();
                                 if(saddress.length()>=5){
                                    taddress.setText(saddress);
                                    
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                saddress = editted_address.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();




            }
        });

    }

    private void phonenumberclicker(View view) {
        ImageButton ph_no=(ImageButton)view.findViewById(R.id.edit_ph);
        final TextView tphonenumber=(TextView)view.findViewById(R.id.ph_num1);
        ph_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.edit_phonenumber_dialog,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Type the phone number");
                final EditText editted_phonenumber=(EditText)mview.findViewById(R.id.edit_phone_numberdialog);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                sphone_number = editted_phonenumber.getText().toString();

                                if(sphone_number.length()==10){
                                    tphonenumber.setText(sphone_number);
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                sphone_number = editted_phonenumber.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();



            }
        });
    }

    private void usernameclicker(View view) {

        ImageButton username=(ImageButton)view.findViewById(R.id.imageButton3);
        final TextView tusername=(TextView)view.findViewById(R.id.ans_name);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.edit_username_dialog,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Type the username");
                final EditText editted_username=(EditText)mview.findViewById(R.id.edit_username_dialog);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                susername = editted_username.getText().toString();
                                if(susername.length()<3){

                                }
                                else if(susername.length()>=3){
                                    tusername.setText(susername);
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                susername = editted_username.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();

//                Button mLogin = (Button)mview.findViewById(R.id.camera);
//                Button mLogin2 = (Button)mview.findViewById(R.id.gallery);
//                mLogin.setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog.dismiss();
//                                switch (v.getId()) {
//                                    case R.id.camera:
//                                        // code
//                                        try {
//
//                                            //use standard intent to capture an image
//                                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
//                                            File imageFile = new File(imageFilePath);
//                                            picUri = Uri.fromFile(imageFile); // convert path to Uri
//                                            takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT, picUri );
//                                            startActivityForResult(takePictureIntent, CAMERA_CAPTURE);
//                                        }
//                                        catch(ActivityNotFoundException anfe){
//                                            //display an error message
//                                            String errorMessage = "Whoops - your device doesn't support capturing images!";
//
//                                        }
//                                        break;
//
//                                    default:
//                                        break;
//                                }
//                            }
//                        }
//                );
//
//
//                mLogin2.setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog.dismiss();
//                                switch (v.getId()) {
//                                    case R.id.gallery:
//                                        // code
//                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                        // Start the Intent
//                                        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
//                                        break;
//
//                                    default:
//                                        break;
//                                }
//                            }
//                        }
//                );



            }
        });

    }

    private void setnames(View view) {

        TextView name=(TextView)view.findViewById(R.id.ans_usr_name);
        name.setText(mParam1);
        TextView username=(TextView)view.findViewById(R.id.ans_name);
        username.setText(mParam2);
        TextView mobile=(TextView)view.findViewById(R.id.ph_num1);
        mobile.setText(mParam3);
        TextView address=(TextView)view.findViewById(R.id.address);
        address.setText(mParam4);
        TextView email=(TextView)view.findViewById(R.id.mail);
        email.setText(mParam5);

    }

    private void photoset(View view) {


        button = (ImageButton) view.findViewById(R.id.camera_btn);
        imageView = (ImageView) view.findViewById(R.id.yug);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(getActivity());
                View mview =getActivity(). getLayoutInflater().inflate(R.layout.dialog_action,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Select profile photo");
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
                Button mLogin = (Button)mview.findViewById(R.id.camera);
                Button mLogin2 = (Button)mview.findViewById(R.id.gallery);
                mLogin.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (v.getId()) {
                                    case R.id.camera:
                                        // code
                                        try {

                                            //use standard intent to capture an image
                                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
                                            File imageFile = new File(imageFilePath);
                                            picUri = Uri.fromFile(imageFile); // convert path to Uri
                                            takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT, picUri );
                                            startActivityForResult(takePictureIntent, CAMERA_CAPTURE);
                                        }
                                        catch(ActivityNotFoundException anfe){
                                            //display an error message
                                            String errorMessage = "Whoops - your device doesn't support capturing images!";

                                        }
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                );


                mLogin2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                switch (v.getId()) {
                                    case R.id.gallery:
                                        // code
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        // Start the Intent
                                        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                );



            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
//user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE){
                Uri uri = picUri;
                Log.d("picUri", uri.toString());
                performCrop();
            }
            else if(requestCode == PICK_IMAGE_REQUEST){
                picUri= data.getData();
                performCrop();
            }
            //user is returning from cropping the image
            else if(requestCode == PIC_CROP){
//get the returned data
                Bundle extras = data.getExtras();
//get the cropped bitmap
                Bitmap thePic = (Bitmap) extras.get("data");
                //display the returned cropped image
                imageView.setImageBitmap(thePic);

            }
        }
    }


    private void performCrop() {


        try {
//call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
//set crop properties
            cropIntent.putExtra("crop", "true");
//indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
//indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
//retrieve data on return
            cropIntent.putExtra("return-data", true);
//start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";

        }

    }



}
