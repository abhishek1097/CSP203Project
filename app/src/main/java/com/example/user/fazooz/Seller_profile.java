package com.example.user.fazooz;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Seller_profile extends AppCompatActivity {
    public ImageButton button;
    boolean c1=false;
    boolean c2=false;
    boolean c3=false;
    boolean c4=false;


    String insertURL1="http://10.1.1.19/~2015CSB1002/PHPLAB/update_name.php";
    String insertURL2="http://10.1.1.19/~2015CSB1002/PHPLAB/update_number.php";
    String insertURL3="http://10.1.1.19/~2015CSB1002/PHPLAB/update_address.php";
    String insertURL4="http://10.1.1.19/~2015CSB1002/PHPLAB/update_email.php";



    private ImageView imageView;
    private Button camera,gallery;
    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE_REQUEST = 2;
    final int PIC_CROP = 3;
    //captured picture uri
    private Uri picUri;
    RequestQueue requestQueue;
    String susername,sphone_number,saddress,semail,sname;
    String username,name,email,address,category,restraunt,mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        String activity_name=this.getClass().getSimpleName();
       // Toast.makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();

        if(status=="0"){
            Intent myintent;
            myintent = new Intent(this,Internet_Status.class);
            myintent.putExtra("activity name", activity_name);

            startActivity(myintent);
            finish();
        }

        Intent intent = getIntent();

        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");
       // Toast.makeText(getBaseContext(),username, Toast.LENGTH_LONG).show();
        sname=name;
        sphone_number=mobile;
        semail=email;
        saddress=address;
        setnames();
    //    photoset();
        clickers();


    }

    private void clickers() {
        usernameclicker();
        phonenumberclicker();
        addressclicker();
        email();

    }
    private void email() {

        ImageButton addressb=(ImageButton)findViewById(R.id.alok_mail_edit);
        final TextView temail=(TextView)findViewById(R.id.alok_mail);
        addressb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder = new  AlertDialog.Builder(Seller_profile.this);
                View mview = getLayoutInflater().inflate(R.layout.edit_email_dialog,null);
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
                                    email=semail;
                                    c4=true;
                                }
                                else
                                {
                                    semail=email;
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //   semail = editted_email.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
            }
        });

    }

    private void addressclicker() {

        ImageButton username=(ImageButton)findViewById(R.id.alok_address_edit);
        final TextView taddress=(TextView)findViewById(R.id.alok_address);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(Seller_profile.this);
                View mview = getLayoutInflater().inflate(R.layout.edit_address_dialog,null);
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
                                    address=saddress;
                                    c3=true;

                                }
                                else
                                {
                                    saddress=address;
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //saddress = editted_address.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();




            }
        });

    }

    private void phonenumberclicker() {
        ImageButton ph_no=(ImageButton)findViewById(R.id.alok_edit_ph);
        final TextView tphonenumber=(TextView)findViewById(R.id.alok_ph_num1);
        ph_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(Seller_profile.this);
                View mview =getLayoutInflater().inflate(R.layout.edit_phonenumber_dialog,null);
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
                                    mobile=sphone_number;
                                    c2=true;
                                }
                                else
                                {
                                    sphone_number=mobile;
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // sphone_number = editted_phonenumber.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();



            }
        });
    }

    private void usernameclicker() {

        final ImageButton usernameq=(ImageButton)findViewById(R.id.alok_imageButton3);
        final TextView tusername=(TextView)findViewById(R.id.alok_ans_name);
        usernameq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(Seller_profile.this);
                View mview = getLayoutInflater().inflate(R.layout.edit_username_dialog,null);
                mbuilder.setView(mview);
                mbuilder  .setTitle("Type the name");
                final EditText editted_username=(EditText)mview.findViewById(R.id.edit_username_dialog);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                sname = editted_username.getText().toString();
                                if(sname.length()<3){
                                    sname=name;


                                    //////////////////////////////////////////////////////////////////////////////



                                }
                                else if(sname.length()>=3){
                                    tusername.setText(sname);
                                    name=sname;
                                    c1=true;
                                }
                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //  susername = editted_username.getText().toString();
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

    private void setnames() {

        TextView name1=(TextView)findViewById(R.id.alok_ans_usr_name);
        name1.setText(username);
        TextView username1=(TextView)findViewById(R.id.alok_ans_name);
        username1.setText(name);
        TextView mobile1=(TextView)findViewById(R.id.alok_ph_num1);
        mobile1.setText(mobile);
        TextView address1=(TextView)findViewById(R.id.alok_address);
        address1.setText(address);
        TextView email1=(TextView)findViewById(R.id.alok_mail);
        email1.setText(email);

    }

//    private void photoset() {
//
//
//        button = (ImageButton) findViewById(R.id.alok_camera_btn);
//        imageView = (ImageView) findViewById(R.id.alok_yug);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final AlertDialog.Builder mbuilder =        new AlertDialog.Builder(Seller_profile.this);
//                View mview =getLayoutInflater().inflate(R.layout.dialog_action,null);
//                mbuilder.setView(mview);
//                mbuilder  .setTitle("Select profile photo");
//                final AlertDialog dialog = mbuilder.create();
//                dialog.show();
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
//
//
//
//            }
//        });
//
//
//
//    }


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
        updateinfo();
        Intent myIntent=new Intent(this,Fazooz.class);
        myIntent.putExtra("name",sname);
        myIntent.putExtra("email",semail);
        myIntent.putExtra("mobile",sphone_number);
        myIntent.putExtra("address",saddress);
        setResult(RESULT_OK,myIntent);
        finish();
    }

    private void updateinfo() {


        if(c1)
        {



            StringRequest request=new StringRequest(Request.Method.POST, insertURL1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>parameters=new HashMap<String, String>();

                    parameters.put("username",username);
                    parameters.put("name",sname);

                    return parameters;
                }
            };
            Log.d("TAG7","sdding the item to the database");
            Log.d("TAG8",restraunt);
            Log.d("TAG8",username);
            Log.d("TAG8",sname);
            requestQueue.add(request);




        }
        if(c2)
        {



            StringRequest request=new StringRequest(Request.Method.POST, insertURL2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>parameters=new HashMap<String, String>();

                    parameters.put("username",username);
                    parameters.put("mobile",sphone_number);

                    return parameters;
                }
            };
            Log.d("TAG7","sdding the item to the database");
            Log.d("TAG8",restraunt);
            Log.d("TAG8",username);
            Log.d("TAG8",sphone_number);
            requestQueue.add(request);



        }
        if(c3)
        {



            StringRequest request=new StringRequest(Request.Method.POST, insertURL3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>parameters=new HashMap<String, String>();

                    parameters.put("username",username);
                    parameters.put("address",saddress);

                    return parameters;
                }
            };
            Log.d("TAG7","sdding the item to the database");
            Log.d("TAG8",restraunt);
            Log.d("TAG8",username);
            Log.d("TAG8",saddress);
            requestQueue.add(request);




        }
        if(c4)
        {



            StringRequest request=new StringRequest(Request.Method.POST, insertURL4, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>parameters=new HashMap<String, String>();

                    parameters.put("username",username);
                    parameters.put("email",semail);

                    return parameters;
                }
            };
            Log.d("TAG7","sdding the item to the database");
            Log.d("TAG8",restraunt);
            Log.d("TAG8",username);
            Log.d("TAG8",semail);
            requestQueue.add(request);




        }


    }


}
