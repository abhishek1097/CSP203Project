package com.example.user.fazooz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    RequestQueue requestQueue;
    String username2;
    String address;
    String category;
    String restraunt;
    String mobile;
    String email;
    String name;
    boolean correct=false;

    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/register.php";
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/show_login_info.php";

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Sign_Up.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        //////////////////

        //////////////////
        final String username = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        Log.d("TAG2","here it is now");

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                showURL,null,new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response) {
                Log.d("TAG123","Response: " + response.toString());
                try {
                    Log.d("TAG3","in_try_1");
                    JSONArray entries = response.getJSONArray("entries");
                    Log.d("TAG33","in_try_2");
                    boolean uname_present;
                    boolean pswd_correct=false;
                    uname_present=false;
                    for(int i=0;i<entries.length();i++)
                    {
                        Log.e("TAG","uycre");
                        JSONObject student=entries.getJSONObject(i);
                        Log.e("TAG0","this is what u are looking for");
                        username2=student.getString("username");
                        String password2=student.getString("password");

                        if(username.equals(username2)) {
                            uname_present=true;
                            Log.e("TAG0",computeMD5Hash(password.trim()));
                            Log.e("TAG1",password2);
                            if(password2.equals(computeMD5Hash(password)))
                            {
                                pswd_correct=true;
                                name=student.getString("name");
                                address=student.getString("address");
                                email=student.getString("email");
                                mobile=student.getString("mobile");
                                category=student.getString("category");
                                restraunt=student.getString("restraunt");
                            }
                            break;
                        }


                        Log.e("TAG0000",username2);
                        Log.e("TAG00001",username);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    //result.append("=====\n");
                    if(uname_present && pswd_correct) {
                        progressDialog.show();
                        correct=true;
                        //output.append("Logging in\n");
                        Log.e("TAG000000000001","LOGGING IN");
                        Toast.makeText(getBaseContext(), "LOGGING IN", Toast.LENGTH_LONG).show();

                    }
                    else if(uname_present)
                    {
                        //output.append("Incorrect Password...\n");
                        Log.e("TAG00000000000","Incorrect Password");
                        _loginButton.setEnabled(true);
                        ////////////////////////////////////////////////////////
                        Toast.makeText(getBaseContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                        Log.d("TAG7","end_2");
                        //return;

                    }
                    else {
                        Log.e("TAG00000000000","Username Not found");
                        _loginButton.setEnabled(true);
                        Toast.makeText(getBaseContext(), "Username not Found", Toast.LENGTH_LONG).show();
                        //output.append("Username not found...\n");
                        //return;
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

//        if(correct) {
        // TODO: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();

                        // onLoginFailed();
                        progressDialog.dismiss();


                        new Thread() {

                            public void run() {
                                try {
                                    Intent myIntent;
                                    sleep(2);
                                    if(correct) {
                                        Log.d("TAG4","Different Class");




                                        if(category.matches("buyer"))
                                            myIntent=new Intent(LoginActivity.this, Fazooz.class);
                                        else
                                        {
                                            myIntent=new Intent(LoginActivity.this, Seller_main.class);
                                        }
                                    }
                                    else
                                    {
                                        Log.d("TAG44444444","THIS SAME CLASS");
                                        myIntent=new Intent(LoginActivity.this, LoginActivity.class);
                                    }
                                    myIntent.putExtra("username",username2);
                                    myIntent.putExtra("name",name);
                                    myIntent.putExtra("email",email);
                                    myIntent.putExtra("mobile",mobile);
                                    myIntent.putExtra("category",category);
                                    myIntent.putExtra("address",address);
                                    myIntent.putExtra("restraunt",restraunt);

                                    startActivity(myIntent);
                                    finish();

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                }, 2000);
        //}
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        if(status=="0"){
            Toast .makeText(getBaseContext(), "Network error", Toast.LENGTH_LONG).show();
            valid=false;
        }


        if (email.isEmpty()){ //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
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

