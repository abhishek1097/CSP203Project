package com.example.user.fazooz;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_Up extends AppCompatActivity {
    private static final String TAG = "Signup";
    String sUsername;
    int decider=0;
    boolean username_allowed=true;
    boolean restraunt_allowed=true;
    String category1;
    String restraunt;
    //String username1;
    RequestQueue requestQueue;

    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/register.php";
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/show_login_info.php";

    RadioButton radioButton,radioButton1;

    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_username)
    EditText _usernameText;
    @Bind(R.id.input_address)
    EditText _addressText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_mobile)
    EditText _mobileText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        ButterKnife.bind(this);

        requestQueue= Volley.newRequestQueue(getApplicationContext());


        RadioButton radioButton = (RadioButton)findViewById(R.id.seller_login_radio_button);
        radioButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final AlertDialog.Builder mbuilder = new  AlertDialog.Builder(Sign_Up.this);
                View mview = getLayoutInflater().inflate(R.layout.seller_dialog,null);
                mbuilder.setView(mview);
               final EditText editText=(EditText)mview.findViewById(R.id.Kitchen_edit);
                mbuilder.setTitle("Name of your Restaurant");
                mbuilder.setCancelable(false);
                mbuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                sUsername = editText.getText().toString();

                                dialog.dismiss();
                            }
                        });
                mbuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                sUsername = editText.getText().toString();
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        if(radioButton1.isChecked())
            decider=1;
        else decider=0;

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Sign_Up.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");

        final String name = _nameText.getText().toString();
        final String username = _usernameText.getText().toString();
        final String address = _addressText.getText().toString();
        final String email = _emailText.getText().toString();
        final String mobile = _mobileText.getText().toString();
        final String password = _passwordText.getText().toString();
        restraunt=sUsername;
        final String username1=username;
        //category1;
        if(decider==1)
        {
            category1="buyer";
            restraunt="";
        }
        else
        {
            category1="seller";
        }
        final String restraunt1=restraunt;

        Log.e("TAG101","-----------------"+category1);
        Log.e("TAG102","-----------------"+restraunt);


        //String reEnterPassword = _reEnterPasswordText.getText().toString();


        /////////////////////////////////////////////////////////////////////

        Log.d("TAG2","here it is now");
        //username1=username;
        Log.e("TAG11","--------------------------------------------------");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                showURL,null,new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response) {
                Log.d("TAG123","Response: " + response.toString());
                try {
                    Log.d("TAG3","in_try_1");
                    JSONArray entries = response.getJSONArray("entries");
                    Log.d("TAG33","in_try_2");
                    boolean uname_present;
                    boolean res_present;
                    uname_present=false;
                    res_present=false;
                    for(int i=0;i<entries.length();i++)
                    {
                        Log.e("TAG","uycre");
                        JSONObject student=entries.getJSONObject(i);
                        Log.e("TAG0","this is what u are looking for");
                        String username2=student.getString("username");
                        String restraunt2=student.getString("restraunt");

                        if(username1.equals(username2)) {
                            uname_present=true;
                            break;
                        }
                        if(restraunt1.equals(restraunt2) && !restraunt1.equals(""))
                        {
                            res_present=true;
                        }

                        Log.e("TAG0000",username2);
                        Log.e("TAG00001",username1);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    //result.append("=====\n");
                    if(uname_present) {

                        //output.append("Username already in use\n");

                        Log.e("TAG000000000001","USERNAME IN USE");
                        username_allowed=false;
                        Toast.makeText(getApplicationContext(),"Username is already used",Toast.LENGTH_SHORT).show();


                        Log.e("TAG10101","Finally Username not allowed go again");
                        onSignupFailed();
                        return;
                    }
                    else if(res_present)
                    {
                        Log.e("TAG000000000001","RESTRAUNT IN USE");
                        restraunt_allowed=false;

                        Toast.makeText(getApplicationContext(),"Restaurant name is already used",Toast.LENGTH_SHORT).show();

                        Log.e("TAG10101","Finally Restraunt name not allowed go again");
                        onSignupFailed();
                        return;
                    }
                    else
                    {

                        progressDialog.show();
                        //output.append("Creating account...\n");
                        Log.e("TAG00000000000","CREATING ACCOUNT");
                        ////////////////////////////////////////////////////////
                        StringRequest request=new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
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

                                parameters.put("name",name);
                                parameters.put("category",category1);
                                parameters.put("username",username);
                                //parameters.put("password",password.getText().toString());
                                parameters.put("address",address);
                                parameters.put("email",email);
                                parameters.put("mobile",mobile);
                                parameters.put("restraunt",restraunt);



                                String hash_pswd=computeMD5Hash(password);

                                parameters.put("password",hash_pswd);

                                Log.d("TAG12340","\n\n\n"+computeMD5Hash(password));
                                        /*
                                        parameters.put("name",name.getText().toString());
                                        parameters.put("eno",mobile.getText().toString());
                                        parameters.put("bp",category.getText().toString());
                                        parameters.put("pin",address.getText().toString());
                                        parameters.put("state",email.getText().toString());
                                        */



                                return parameters;
                            }
                        };
                        Log.d("TAG7","end_2");
                        Log.d("TAG8",name);
                        requestQueue.add(request);
                    }
                }
                catch(JSONException e){
                    Log.d("TAG4","in_catch");
                    e.printStackTrace();

                }
            }
        },new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){

            }

        });
        Log.d("TAG5","end");
        requestQueue.add(jsonObjectRequest);
        /*
        if(!username_allowed) {
            Log.e("TAG10101","Finally Username not allowed go again");
            onSignupFailed();
            return;
        }
        else {
        */
            Log.d("TAG6","end_here");
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            new Thread() {

                                public void run() {
                                    try {

                                        sleep(2);
                                        Intent myIntent;

                                        if (!username_allowed) {

                                            myIntent=new Intent(Sign_Up.this, Sign_Up.class);
                                            //Toast.makeText(getApplication(), "Username Already Present", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(!restraunt_allowed) {
                                            myIntent=new Intent(Sign_Up.this, Sign_Up.class);
                                            //Toast.makeText(getBaseContext(), "Restraunt name Already Present", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            if (decider == 1) {
                                                myIntent = new Intent(Sign_Up.this, Fazooz.class);
                                            } else {
                                                myIntent = new Intent(Sign_Up.this, Seller_main.class);
                                            }
                                        }
                                            EditText loginName = (EditText) findViewById(R.id.input_name);
                                            EditText loginEmail = (EditText) findViewById(R.id.input_email);
                                            String name = loginName.getText().toString();
                                            String Email = loginEmail.getText().toString();
                                            myIntent.putExtra("name", name);
                                            myIntent.putExtra("email", Email);
                                            myIntent.putExtra("username",username);

                                            myIntent.putExtra("mobile",mobile);
                                            myIntent.putExtra("category",category1);
                                            myIntent.putExtra("address",address);
                                            myIntent.putExtra("restraunt",restraunt);
                                            startActivity(myIntent);

                                            finish();


                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 1000);
    /*
        }
        */
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        radioButton=(RadioButton)findViewById(R.id.seller_login_radio_button);
        radioButton1=(RadioButton)findViewById(R.id.user_login_radio_button);
        if(radioButton.isChecked()){
            if (sUsername.matches("")) {

                valid = false;Toast.makeText(this, "You did not enter Restaurant name", Toast.LENGTH_SHORT).show();
            }

        }

        if(_usernameText.length()<3){
            _usernameText.setError("at least 3 characters");
            valid=false;
        }
        else{
            _usernameText.setError(null);
        }


        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        String activity_name=this.getClass().getSimpleName();
        // Toast .makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();

        if(status=="0"){
         valid=false;
            nonet();
        }


        if(!(radioButton.isChecked()||radioButton1.isChecked())){
            valid=false;
            Toast.makeText(this, "Please select whether you are a user or seller", Toast.LENGTH_SHORT).show();
        }
        if (address.length()<3) {
            _addressText.setError("at least 5 characters");
            valid = false;
        } else {
            _addressText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    private void nonet() {
        Toast .makeText(getBaseContext(), "Network error", Toast.LENGTH_LONG).show();
    }

    public String computeMD5Hash(String password)
    {

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
            return MD5Hash.toString();
            //result.setText("MD5 hash generated is: " + " " + MD5Hash);

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return "";
    }


}