package com.example.user.fazooz;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class Seller_Current extends AppCompatActivity {
    String name_array[]=new String[100];
    String order_items[]=new String[100];
    String items_quantity[]=new String[100];
    String items_total[]=new String[100];
    String address_array[]=new String[100];
    RequestQueue requestQueue;
    String username,name,email,address,category,restraunt,mobile;

    List<String> address_1=new ArrayList<String>();
    List<String> buyer_1=new ArrayList<String>();
    List<String> item_1=new ArrayList<String>();
    List<String> qty_1=new ArrayList<String>();
    List<String> price_1=new ArrayList<String>();
    List<String> timestamp_1=new ArrayList<String>();


//    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/get_orders.php";
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/get_orders.php";
    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/update_order_status.php";
//
//    List<String> items=new ArrayList<String>();
//    List<String> quantities=new ArrayList<String>();
//    List<String> totals=new ArrayList<String>();
//    List<String> restraunts=new ArrayList<String>();
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_2);
//

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

//
//
        requestQueue= Volley.newRequestQueue(getApplicationContext());
//
        /*
        order_items[0]="Lays,Kurkure,biscuits";
        order_items[1]="Sandwitch,banana";
        items_quantity[0]="1,1,1";
        items_quantity[1]="2,1";
        items_total[0]="200";
        items_total[1]="150";
        name_array[0]="Dubey Canteen";
        name_array[1]="Kerala Canteen";
        address_array[0]="ju";
        address_array[1]="mer";
        */

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                showURL,null,new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response) {
                Log.d("TAG123","Response: " + response.toString());
                try {
                    Log.d("TAG3","in_try_1");
                    JSONArray entries = response.getJSONArray("orders");
                    Log.d("TAG33","in_try_2");
                    boolean uname_present;
                    boolean pswd_correct=false;
                    uname_present=false;
                    for(int i=0;i<entries.length();i++)
                    {
                        Log.e("TAG","uycre");
                        JSONObject student=entries.getJSONObject(i);
                        Log.e("TAG0","this is what u are looking for");
                        String restraunt2=student.getString("Seller");
                        String price2=student.getString("Price");
                        String buyer2=student.getString("Buyer");
                        String item_list2=student.getString("Item_list");
                        String qty_list2=student.getString("qty_list");
                        String status=student.getString("Status");
                        String timestamp2=student.getString("TimeStamp");
                        String address2=student.getString("Address");


                        if(restraunt2.equals(restraunt) && status.equals("active")) {
                            uname_present=true;
                            //Log.e("TAG0",computeMD5Hash(password.trim()));
                            Log.e("TAG1",buyer2);
                            Log.e("TAG11",item_list2);

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


                            address_1.add(address2);
                            buyer_1.add(buyer2);
                            item_1.add(item_list2);
                            qty_1.add(qty_list2);
                            price_1.add(price2);
                            timestamp_1.add(timestamp2);

                            Log.e("TAG11",item_1.size()+"");

                        }
                        //String[] menu=items.toArray(new String[items.size()])

                        //Log.e("TAG0000",username2);
                        //Log.e("TAG00001",username);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    Log.e("TAG101","out of the fr loop");
                    int k=0;
                    while(k<item_1.size())
                    {
                        order_items[k]=item_1.get(k);
                        items_quantity[k]=qty_1.get(k);
                        items_total[k]=price_1.get(k);
                        name_array[k]=buyer_1.get(k);
                        address_array[k]=address_1.get(k);

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

                    pop();
//                    for(int i=0;i<100;i++)
//                        costarray[i]=10;
//                    Button b =(Button)findViewById(R.id.total_button);
//                    Button pay_button =(Button)findViewById(R.id.pay_button);
//                    b.setVisibility(View.GONE);
//                    pay_button.setVisibility(View.GONE);

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

        Log.e("TAG111",item_1.size()+"");


//

//        ////////////////////////////////////////////////////////////////////

    }

    private void pop() {
//        for (int i = 0; i < rows; i++) {
//            countarray[i] = 0;
//        }
        Log.e("TAG101","POP function started");
        int rows= buyer_1.size();
        if(rows==0){
            Intent myintent;
            myintent = new Intent(this,CurrentActivity.class);
            myintent.putExtra("name", name);
            myintent.putExtra("username",username);
            myintent.putExtra("email",email);
            myintent.putExtra("mobile",mobile);
            myintent.putExtra("category",category);
            myintent.putExtra("address",address);
            myintent.putExtra("restraunt",restraunt);
            startActivity(myintent);
            finish();
        }
        TableLayout table = (TableLayout) findViewById(R.id.current_orders_table);
        int count = -1;
        for(int i=0;i<rows;i++) {
            Log.e("TAG101", "Making row " + (i + 1));
            count++;


            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
            );
            Log.e("TAG101", "Check 1");
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(params);
            tr.setBackgroundResource(0);
            Log.e("TAG101", "Check 2");
            params = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT);
            LinearLayout layout = new LinearLayout(this);
            params.weight = 1;
            layout.setLayoutParams(params);
            layout.setBackgroundColor(Color.WHITE);
            layout.setWeightSum(1);
            Log.e("TAG101", "Check 3");
    /* Create a Button to be the row-content. */

            LinearLayout.LayoutParams chiledParams = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams.weight = (float) 0.8;
            Button b = new Button(this);
            b.setGravity(Gravity.CENTER);
            String rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setText("Order No " + (count + 1) + "");
            b.setAllCaps(false);
            b.setTextSize(20);
            b.setBackgroundResource(0);
            b.setLayoutParams(chiledParams);
            LinearLayout.LayoutParams chiledParams1 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams1.weight = (float) 0.2;
            Button a = new Button(this);
            a.setGravity(Gravity.LEFT);
            a.setText(rupee + items_total[count]);
            a.setAllCaps(false);
            Log.e("TAG101", "Check 4");
            rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            a.setBackgroundColor(Color.WHITE);
            a.setTextColor(Color.BLACK);
            //  a.setId(count);
            a.setBackgroundResource(0);
            a.setLayoutParams(chiledParams1);

    /* Add Button to row. */


            Log.e("TAG101", "Check 5");
            layout.addView(b);
            layout.addView(a);

            tr.addView(layout);
            table.addView(tr);

            Log.e("TAG101", "Check 6");
//////////////////////////////////////

            // count_internal++;
            int checker=-1;
           while(checker<1) {
               checker++;
            Log.e("TAG101", "IN SECOND FOR LOOP");
            TableRow.LayoutParams params_name = new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.FILL_PARENT
            );

            TableRow tr_name = new TableRow(this);
            tr_name.setPadding(5, 0, 0, 0);
            tr_name.setLayoutParams(params_name);
            tr_name.setBackgroundResource(0);
            Log.e("TAG101", "Check 7");
            params_name = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT);
            LinearLayout layout_name_child = new LinearLayout(this);
            params_name.weight = 1;
            layout_name_child.setLayoutParams(params_name);
            layout_name_child.setBackgroundColor(Color.WHITE);
            layout_name_child.setWeightSum(1);
            Log.e("TAG101", "Check 8");
    /* Create a Button to be the row-content. */

//                if(j==0){
//
//
//                    LinearLayout.LayoutParams chiledchildParams = new LinearLayout.LayoutParams(0,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//                    chiledchildParams.weight = (float) 0.5;
//                    TextView name_text = new TextView(this);
//                    name_text.setGravity(Gravity.LEFT);
//                    Log.e("TAG101","Check 9");
//                    //    String rupee = getString(R.string.Rs);
//                    //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
//                    name_text.setBackgroundColor(Color.WHITE);
//                    name_text.setTextColor(Color.BLACK);
//                    //  b2.setId(count);
//                    name_text.setText(name_array[count]);
//                    name_text.setBackgroundResource(0);
//                    name_text.setLayoutParams(chiledchildParams);
//                    Log.e("TAG101","Check 10");
//                    name_text.setAllCaps(false);
//                    layoutchild.addView(name_text);
//
//
//                }

            LinearLayout.LayoutParams chiledchildParams = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledchildParams.weight = (float) 0.5;
            Button z = new Button(this);
            z.setGravity(Gravity.LEFT);
            Log.e("TAG101", "Check 9");
            //    String rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            z.setBackgroundColor(Color.WHITE);
            z.setTextColor(Color.BLACK);
               if(checker==0)
            z.setText("Name");
               else if(checker==1){
                   z.setText("Address");
               }
            //  b2.setId(count);
            //  b2.setText(list1[count_internal]);
            z.setBackgroundResource(0);
            z.setLayoutParams(chiledchildParams);
            Log.e("TAG101", "Check 10");
            LinearLayout.LayoutParams chiledchildParams1_name = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledchildParams1_name.weight = (float) 0.5;
            Button y = new Button(this);
            y.setGravity(Gravity.LEFT);
            // a2.setText(quantity[count_internal]);
            y.setAllCaps(false);
            Log.e("TAG101", "Check 11");
            //   rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
               if(checker==0){
            y.setText(name_array[count]);}
               else if(checker==1){
                   y.setText(address_array[count]);}
            y.setBackgroundColor(Color.WHITE);
            y.setTextColor(Color.BLACK);
            y.setId(count);
            y.setBackgroundResource(0);
            y.setLayoutParams(chiledchildParams1_name);
            Log.e("TAG101", "Check 12");

    /* Add Button to row. */


            layout_name_child.addView(z);
            layout_name_child.addView(y);

            tr_name.addView(layout_name_child);
            table.addView(tr_name);
        }

            /////////////////////////////
            String []list1=order_items[count].split(",");
            String []quantity=items_quantity[count].split(",");
            int count_internal=-1;
            for(int j=0;j<list1.length;j++) {
                count_internal++;

                Log.e("TAG101","IN SECOND FOR LOOP");
                TableRow.LayoutParams paramschild = new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.FILL_PARENT
                );

                TableRow tr2 = new TableRow(this);
                tr2.setPadding(5,0,0,0);
                tr2.setLayoutParams(paramschild);
                tr2.setBackgroundResource(0);
                Log.e("TAG101","Check 7");
                paramschild = new TableRow.LayoutParams(0,
                        TableRow.LayoutParams.WRAP_CONTENT);
                LinearLayout layoutchild = new LinearLayout(this);
                paramschild.weight = 1;
                layoutchild.setLayoutParams(paramschild);
                layoutchild.setBackgroundColor(Color.WHITE);
                layoutchild.setWeightSum(1);
                Log.e("TAG101","Check 8");
    /* Create a Button to be the row-content. */

//                if(j==0){
//
//
//                    LinearLayout.LayoutParams chiledchildParams = new LinearLayout.LayoutParams(0,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//                    chiledchildParams.weight = (float) 0.5;
//                    TextView name_text = new TextView(this);
//                    name_text.setGravity(Gravity.LEFT);
//                    Log.e("TAG101","Check 9");
//                    //    String rupee = getString(R.string.Rs);
//                    //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
//                    name_text.setBackgroundColor(Color.WHITE);
//                    name_text.setTextColor(Color.BLACK);
//                    //  b2.setId(count);
//                    name_text.setText(name_array[count]);
//                    name_text.setBackgroundResource(0);
//                    name_text.setLayoutParams(chiledchildParams);
//                    Log.e("TAG101","Check 10");
//                    name_text.setAllCaps(false);
//                    layoutchild.addView(name_text);
//
//
//                }

                LinearLayout.LayoutParams chiledchildParams_name = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                chiledchildParams_name.weight = (float) 0.5;
                Button b2 = new Button(this);
                b2.setGravity(Gravity.LEFT);
                Log.e("TAG101","Check 9");
                //    String rupee = getString(R.string.Rs);
                //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
                b2.setBackgroundColor(Color.WHITE);
                b2.setTextColor(Color.BLACK);
                //  b2.setId(count);
                b2.setText(list1[count_internal]);
                b2.setBackgroundResource(0);
                b2.setLayoutParams(chiledchildParams_name);
                Log.e("TAG101","Check 10");
                LinearLayout.LayoutParams chiledchildParams1 = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                chiledchildParams1.weight = (float) 0.5;
                Button a2 = new Button(this);
                a2.setGravity(Gravity.LEFT);
                a2.setText(quantity[count_internal]);
                a2.setAllCaps(false);
                Log.e("TAG101","Check 11");
                //   rupee = getString(R.string.Rs);
                //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
                a2.setBackgroundColor(Color.WHITE);
                a2.setTextColor(Color.BLACK);
                a2.setId(count);
                a2.setBackgroundResource(0);
                a2.setLayoutParams(chiledchildParams1);
                Log.e("TAG101","Check 12");

    /* Add Button to row. */


                layoutchild.addView(b2);
                layoutchild.addView(a2);

                tr2.addView(layoutchild);
                table.addView(tr2);


            }
////////////////////////////////////////

            TableRow.LayoutParams paramschild = new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.FILL_PARENT
            );

            TableRow tr2 = new TableRow(this);
            tr2.setPadding(5,0,0,0);
            tr2.setLayoutParams(paramschild);
            tr2.setBackgroundResource(0);
            Log.e("TAG101","Check 7");
            paramschild = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT);
            LinearLayout layoutchild = new LinearLayout(this);
            paramschild.weight = 1;
            layoutchild.setLayoutParams(paramschild);
            layoutchild.setBackgroundColor(Color.WHITE);
            layoutchild.setWeightSum(1);
            Log.e("TAG101","Check 8");
    /* Create a Button to be the row-content. */

//                if(j==0){
//
//
//                    LinearLayout.LayoutParams chiledchildParams = new LinearLayout.LayoutParams(0,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//                    chiledchildParams.weight = (float) 0.5;
//                    TextView name_text = new TextView(this);
//                    name_text.setGravity(Gravity.LEFT);
//                    Log.e("TAG101","Check 9");
//                    //    String rupee = getString(R.string.Rs);
//                    //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
//                    name_text.setBackgroundColor(Color.WHITE);
//                    name_text.setTextColor(Color.BLACK);
//                    //  b2.setId(count);
//                    name_text.setText(name_array[count]);
//                    name_text.setBackgroundResource(0);
//                    name_text.setLayoutParams(chiledchildParams);
//                    Log.e("TAG101","Check 10");
//                    name_text.setAllCaps(false);
//                    layoutchild.addView(name_text);
//
//
//                }

            LinearLayout.LayoutParams chiledchildParams_name = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledchildParams_name.weight = (float) 1;
            chiledchildParams_name.leftMargin=80;
            chiledchildParams_name.rightMargin=100;
            Button deliverd = new Button(this);
            deliverd.setGravity(Gravity.CENTER);
            Log.e("TAG101","Check 9");
            //    String rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            deliverd.setBackgroundColor(Color.WHITE);
            deliverd.setTextColor(Color.BLACK);
            //  b2.setId(count);
            deliverd.setId(count+500);
            deliverd.setText("Click here if order is delivered");
            deliverd.setAllCaps(false);
            deliverd.setBackground(getResources().getDrawable(R.drawable.round_corners_button));
            deliverd.setTextColor(Color.WHITE);
            deliverd.setOnClickListener(new View.OnClickListener() {
                //
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-100000)+" "+count, Toast.LENGTH_SHORT).show();
                    //switch_activity(v.getId());
                    int id=v.getId()-500;
                    final String delivered_name=name_array[id];
                    final String ts=timestamp_1.get(id);




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

                            parameters.put("seller",restraunt);
                            parameters.put("buyer",delivered_name);
                            parameters.put("timestamp",ts);

                            return parameters;
                        }
                    };
                    Log.d("TAG7","sdding the item to the database");
                    Log.d("TAG8",restraunt);
                    Log.d("TAG8",delivered_name);
                    Log.d("TAG8",ts);
                    requestQueue.add(request);






                    Intent intent = new Intent(Seller_Current.this, Seller_main.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("username", username);
                    intent.putExtra("restraunt", " ");
                    intent.putExtra("category", " ");
                    intent.putExtra("address", address);
                    intent.putExtra("mobile", mobile);
                    Toast.makeText(getApplicationContext(), "Order Status is successfully changed", Toast.LENGTH_SHORT).show();
                    finish();

                }
            });
           // b2.setBackgroundResource(0);
            deliverd.setLayoutParams(chiledchildParams_name);
            Log.e("TAG101","Check 10");

    /* Add Button to row. */


            layoutchild.addView(deliverd);
        //    layoutchild.addView(a2);

            tr2.addView(layoutchild);
            table.addView(tr2);


///////////////////////////////////////



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
        }
    }
}
