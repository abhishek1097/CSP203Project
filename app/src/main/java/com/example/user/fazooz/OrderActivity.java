package com.example.user.fazooz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
public Button dubey;
    int rows =6;
    private static final int col = 1;
    String username,name,email,address,category,restraunt,mobile;
    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/register.php";
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/show_login_info.php";
    RequestQueue requestQueue;

    //String s[ ]={"Dubey Canteen","faDFD","FDSF","FWEF","yu","ga","nd","ha","ra","re","as","wd","daddas","sac","wsd","dcw","CSAC","wsz","xD","fdc","fsdaf"};
    List<String> restraunts=new ArrayList<String>();

   // public void du_canteen(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        String activity_name=this.getClass().getSimpleName();
      //  Toast .makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();

        if(status=="0"){
            Intent myintent;
            myintent = new Intent(this,Internet_Status.class);
            myintent.putExtra("activity name", activity_name);

            startActivity(myintent);
        }
        getSupportActionBar().setTitle("Kitchen");
        Intent intent = getIntent();


        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");
       // Toast.makeText(getBaseContext(),username, Toast.LENGTH_LONG).show();

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // Getting the data from the database


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
                        String category2=student.getString("category");
                        String restraunt2=student.getString("restraunt");

                        if(category2.equals("seller")) {
                            uname_present=true;
                            //Log.e("TAG0",computeMD5Hash(password.trim()));
                            Log.e("TAG1",restraunt2);
                            /*
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
                            */
                            restraunts.add(restraunt2);
                            Log.e("TAG11",restraunts.size()+"");

                        }


                        //Log.e("TAG0000",username2);
                        //Log.e("TAG00001",username);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    pop();
                    //result.append("=====\n");
                    /*
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
                    */
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
        requestQueue.add(jsonObjectRequest);

        Log.e("TAG111",restraunts.size()+"");


    }

    void pop() {
        rows=restraunts.size();
        Log.e("TAG1-----------",restraunts.size()+"");
        //Log.e("TAG1-----------",restraunts.get(1));
        //Log.e("TAG1-----------",restraunts.get(2));
        final String[] s=new String[restraunts.size()];
        restraunts.toArray(s);
        TableLayout table=(TableLayout)findViewById(R.id.table);
        int count=-1;


////////////////////



        for(int i=0;i<rows;i++) {

            count++;


            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.FILL_PARENT
            );

            TableRow tr = new TableRow(this);
            tr.setLayoutParams(params);
            tr.setBackgroundResource(0);

            params = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT);
            LinearLayout layout = new LinearLayout(this);
            params.weight = 1;
            layout.setLayoutParams(params);
            layout.setBackgroundColor(Color.WHITE);
            layout.setWeightSum(1);

    /* Create a Button to be the row-content. */

            LinearLayout.LayoutParams chiledParams = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams.weight = (float) 0.53;
            Button b = new Button(this);
            b.setGravity(Gravity.START);
            String rupee = getString(R.string.Rs);
            // b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            b.setAllCaps(false);
            b.setTextSize(18);
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setBackgroundResource(0);
            b.setText(s[count]);
            b.setLayoutParams(chiledParams);

            b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(getApplicationContext(), "Button"+ v.getId(), Toast.LENGTH_SHORT).show();
                        //switch_activity(v.getId());
                        Intent myIntent = new Intent(v.getContext(),Items.class);
                        String restraunt=s[v.getId()];
                        myIntent.putExtra("restraunt",restraunt);
                        myIntent.putExtra("username",username);
                        myIntent.putExtra("email",email);
                        myIntent.putExtra("mobile",mobile);
                        myIntent.putExtra("category",category);
                        myIntent.putExtra("address",address);
                        myIntent.putExtra("name",name);
                        startActivity(myIntent);
                    }
                });

    /* Add Button to row. */


            layout.addView(b);
            tr.addView(layout);
            table.addView(tr);


        }




//            table.addView(tablerow);
//            for(int j=0;j<col;j++){
//                count++;
//                Button button=new Button(this);
//                button.setText(s[count]);
//                String st;
//                st=s[count];
//                button.setWidth(50);
//                button.setId(count);
//                //button.setTextSize(10);
//               // button.setPadding(8, 3, 8, 3);
//                tablerow.addView(button);
//
//                button.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(), "Button"+ v.getId(), Toast.LENGTH_SHORT).show();
//                        //switch_activity(v.getId());
//                        Intent myIntent = new Intent(v.getContext(),Order.class);
//                        String name=s[v.getId()];
//                        myIntent.putExtra("mytext",name);
//                        startActivity(myIntent);
//                    }
//                });
//            }

        ///////////////////////
//        for(int i=0;i<rows;i++){
//            TableRow tablerow=new TableRow(this);
//            table.addView(tablerow);
//            for(int j=0;j<col;j++){
//                count++;
//                Button button=new Button(this);
//                button.setText(s[count]);
//                String st;
//                button.setBackgroundColor(Color.argb(1,255,255,255));
//                st=s[count];
//                button.setId(count);
//                button.setTextSize(20);
//                button.setAllCaps(false);
//                //button.setTextSize(10);
//                // button.setPadding(8, 3, 8, 3);
//
//                tablerow.addView(button);
//
//
//                button.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                      //  Toast.makeText(getApplicationContext(), "Button"+ v.getId(), Toast.LENGTH_SHORT).show();
//                        //switch_activity(v.getId());
//                        Intent myIntent = new Intent(v.getContext(),Items.class);
//                        String restraunt=s[v.getId()];
//                        myIntent.putExtra("restraunt",restraunt);
//                        myIntent.putExtra("username",username);
//                        myIntent.putExtra("email",email);
//                        myIntent.putExtra("mobile",mobile);
//                        myIntent.putExtra("category",category);
//                        myIntent.putExtra("address",address);
//                        myIntent.putExtra("name",name);
//                        startActivity(myIntent);
//                    }
//                });
//            }
//        }
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
        myIntent.putExtra("category",category);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("address",address);
        setResult(RESULT_OK,myIntent);
        finish();
    }


//public void startChildCampus(View view){
//    startActivity(new Intent(this,CurrentActivity.class));
//}
//
//    public void startChildDubey(View view){
//        startActivity(new Intent(this,Dubey_Order.class));
//    }

}
