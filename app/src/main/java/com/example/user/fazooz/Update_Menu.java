package com.example.user.fazooz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Update_Menu extends AppCompatActivity {


    int rows ;
    int col ;
    int count=-1;
    String menu[]=new String[100];
    int total=0;
    String costarray1[]=new String[100];
    int costarray[]=new int [100];
    int countarray[]=new int [100];
    int send_count_array[]=new int [100];
    String send_items_array[]=new String [100];
    String username,name=" ",email,address=" ",category,restraunt=" ",mobile;
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/show_items.php";
    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/add_items.php";
    int isdelete=0,isadd;
    String added_item_name;
    String added_item_cost;
    //String s[ ]={"fdf","faDFD","FDSF","FWEF","yu","ga","nd","ha","ra","re","as","wd","daddas","sac","wsd","dcw","CSAC","wsz","xD","fdc","fsdaf"};


    RequestQueue requestQueue;

    List<String> items=new ArrayList<String>();
    List<String> prices=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //menu[0]="dosa";
        //menu[1]="uttapam";
        //costarray[0]=25;
        //costarray[1]=40;
        /////////////////////////////////////////////////////////
        setContentView(R.layout.activity_update__menu);
        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        String activity_name=this.getClass().getSimpleName();
      //  Toast.makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();

        if(status=="0"){
            Intent myintent;
            myintent = new Intent(this,Internet_Status.class);
            myintent.putExtra("activity name", activity_name);

            startActivity(myintent);
            finish();
        }
      //  Button total=(Button)findViewById(R.id.total_button);
      //  total.setVisibility(View.GONE);
     //   Button pay=(Button)findViewById(R.id.pay_button);
      //  pay.setVisibility(View.GONE);

        Intent intent = getIntent();

        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        // ITEMS FETCHED FROM DATABASE

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                showURL,null,new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response) {
                Log.d("TAG123","Response: " + response.toString());
                try {
                    Log.d("TAG3","in_try_1");
                    JSONArray entries = response.getJSONArray("items");
                    Log.d("TAG33","in_try_2");
                    boolean uname_present;
                    boolean pswd_correct=false;
                    uname_present=false;
                    for(int i=0;i<entries.length();i++)
                    {
                        Log.e("TAG","uycre");
                        JSONObject student=entries.getJSONObject(i);
                        Log.e("TAG0","this is what u are looking for");
                        String item2=student.getString("item");
                        String price2=student.getString("price");
                        String restraunt2=student.getString("restraunt");


                        if(restraunt2.equals(restraunt)) {
                            uname_present=true;
                            //Log.e("TAG0",computeMD5Hash(password.trim()));
                            Log.e("TAG1",restraunt2);
                            Log.e("TAG11",item2);

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
                            items.add(item2);
                            prices.add(price2);
                            Log.e("TAG11",items.size()+"");

                        }
                        //String[] menu=items.toArray(new String[items.size()])

                        //Log.e("TAG0000",username2);
                        //Log.e("TAG00001",username);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    Log.e("TAG101","out of the fr loop");
                    int k=0;
                    while(k<items.size())
                    {
                        menu[k]=items.get(k);
                        costarray[k]=Integer.parseInt(prices.get(k));
                        k=k+1;
                    }
                    /*
                    menu=(String[]) items.toArray();
                    costarray1=(String[]) prices.toArray();

                    while(k<costarray1.length) {
                        costarray[k]=Integer.parseInt(costarray1[k]);
                        k=k+1;
                    }
                    */
                    Log.e("TAG101","arrays initialized");


//                    for(int i=0;i<100;i++)
//                        costarray[i]=10;
//                    Button b =(Button)findViewById(R.id.total_button);
//                    Button pay_button =(Button)findViewById(R.id.pay_button);
//                    b.setVisibility(View.GONE);
//                    pay_button.setVisibility(View.GONE);
                    pop();
                    //result.append("=====\n");
                    /*
                    if(uname_present && pswd_correct) {
                        progressDialog.show();
                        correct=true;
                        //output.append("Logging in\n");
                        Log.e("TAG000000000001","LOGGING IN");
                       Toast .makeText(getBaseContext(), "LOGGING IN", Toast.LENGTH_LONG).show();

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

        Log.e("TAG111",items.size()+"");

     //   pop();
        Button updatebutton = (Button)findViewById(R.id.edit_menu);
        updatebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final AlertDialog.Builder mbuilder = new  AlertDialog.Builder(Update_Menu.this);
                View mview = getLayoutInflater().inflate(R.layout.questioning_dialog_seller,null);
                mbuilder.setView(mview);
                mbuilder.setTitle("Select the action");
              //  mbuilder.setCancelable(false);
                final AlertDialog dialog = mbuilder.create();
                dialog.show();
          //      final EditText editText=(EditText)mview.findViewById(R.id.Kitchen_edit);
                final Button additem=(Button)mview.findViewById(R.id.add_item);
                Button deleteitem=(Button)mview.findViewById(R.id.delete_item);
                additem.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isadd=1;
                                dialog.dismiss();
                                add_activity_item();

                            }
                        }
                );

                deleteitem.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isdelete=1;
                                dialog.dismiss();
                                delete_activity_item();

                            }

                            private void delete_activity_item() {

                                Intent myIntent;
                                myIntent=new Intent(Update_Menu.this, Delete_item_seller.class);
                                myIntent.putExtra("name", name);
                                myIntent.putExtra("email", email);
                                myIntent.putExtra("username",username);
                                myIntent.putExtra("mobile",mobile);
                                myIntent.putExtra("category",category);
                                myIntent.putExtra("address",address);
                                myIntent.putExtra("restraunt",restraunt);
                                myIntent.putExtra("menu",menu);
                                myIntent.putExtra("cost",costarray);
                                startActivity(myIntent);

                                finish();


                            }
                        }
                );



            }
        });



   add_activity_item();



    }

    public void add_activity_item() {

        if(isadd==1) {


            final AlertDialog.Builder mbuilder = new  AlertDialog.Builder(Update_Menu.this);
            View mview = getLayoutInflater().inflate(R.layout.additem_dialog_seller,null);
            mbuilder.setView(mview);

            final EditText item_name=(EditText)mview.findViewById(R.id.item_named);
            final EditText item_cost=(EditText)mview.findViewById(R.id.item_costd);
            mbuilder.setTitle("Add Details");
            mbuilder.setCancelable(false);
            mbuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            added_item_name = item_name.getText().toString();
                            added_item_cost = item_cost.getText().toString();
                            validate();
                            dialog.dismiss();
                        }


                    });
            mbuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            //   sUsername = editText.getText().toString();
                            dialog.dismiss();
                        }
                    });
            final AlertDialog dialog = mbuilder.create();
            dialog.show();

        }

    }

    private void validate() {

        if(added_item_cost.length()==0||added_item_cost.matches("")){
              Toast.makeText(this, "Cost is not added", Toast.LENGTH_LONG).show();
        }

        if(added_item_name.length()==0||added_item_name.matches("")){
            Toast.makeText(this, "Item name is not added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Item added successfully", Toast.LENGTH_LONG).show();
            goback();

        }
    }

    private void goback() {
        /////////////////////////////////////////////////////////////////////////////////////// sending items to the database




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

                parameters.put("restraunt",restraunt);
                parameters.put("item",added_item_name);
                parameters.put("price",added_item_cost);

                return parameters;
            }
        };
        Log.d("TAG7","sdding the item to the database");
        Log.d("TAG8",added_item_name);
        requestQueue.add(request);


        Intent myIntent;
        myIntent=new Intent(Update_Menu.this, Seller_main.class);
        myIntent.putExtra("name", name);
        myIntent.putExtra("email", email);
        myIntent.putExtra("username",username);

        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("restraunt",restraunt);

     //   myIntent.putExtra("cost",costarray);
        startActivity(myIntent);

        finish();

    }

    private void pop() {

        rows=items.size();
        Log.e("TAG111111-----------","In the pop function");
        final String[] s=new String[items.size()];
        items.toArray(s);
        int countarray[] = new int[s.length];
        // TextView name1 = (TextView) findViewById(R.id.kitchen_name);
        //name1.setText(restraunt + " Kitchen");
//        for (int i = 0; i < rows; i++) {
//            countarray[i] = 0;
//        }
        TableLayout table = (TableLayout) findViewById(R.id.seller_table);
        int count = -1;
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
          //  b.setText(s[count]);
            b.setText(menu[count]);
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setBackgroundResource(0);
            b.setLayoutParams(chiledParams);

    /* Add Button to row. */


            LinearLayout.LayoutParams chiledParams1 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams1.weight = (float) 0.1;
            Button a = new Button(this);
            a.setText("+");
            a.setId(count + 1000);
            a.setLayoutParams(chiledParams1);
            // a.setBackgroundColor(Color.WHITE);
            a.setBackgroundResource(0);
            a.setTextColor(Color.BLACK);
            a.setOnClickListener(new View.OnClickListener() {
                //
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-1000)+" "+count, Toast.LENGTH_SHORT).show();
                    //switch_activity(v.getId());
                  //  Increase_or_decrease_Integer((v.getId() - 1000), 1);

                }
            });


            LinearLayout.LayoutParams chiledParams2 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams2.weight = (float) 0.2;
            TextView c = new TextView(this);
            c.setText(costarray[count]+"");
            //    c.setBackgroundColor(Color.YELLOW);
            c.setGravity(Gravity.CENTER);
            c.setId(count + 10000);
            c.setTextColor(Color.BLACK);
            c.setBackgroundResource(0);
            c.setLayoutParams(chiledParams2);


            LinearLayout.LayoutParams chiledParams3 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams3.weight = (float) 0.1;
            Button d = new Button(this);
            d.setText("-");
            d.setBackgroundColor(Color.WHITE);
            d.setTextColor(Color.BLACK);
            d.setId(count + 100000);
            d.setBackgroundResource(0);
            d.setOnClickListener(new View.OnClickListener() {
                //
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-100000)+" "+count, Toast.LENGTH_SHORT).show();
                    //switch_activity(v.getId());
                   // Increase_or_decrease_Integer((v.getId() - 100000), 0);

                }
            });
            d.setLayoutParams(chiledParams3);

            layout.addView(b);
          //  layout.addView(a);
            layout.addView(c);
          //  layout.addView(d);
            tr.addView(layout);
            table.addView(tr);


        }
    }
}
