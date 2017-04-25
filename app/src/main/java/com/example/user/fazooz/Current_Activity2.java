package com.example.user.fazooz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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

public class Current_Activity2 extends AppCompatActivity {
    String canteen[]=new String[100];
    String order_items[]=new String[100];
    String items_quantity[]=new String[100];
    String items_total[]=new String[100];
    RequestQueue requestQueue;
    String username,name,email,address,category,restraunt,mobile;

    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/get_orders.php";

    List<String> items=new ArrayList<String>();
    List<String> quantities=new ArrayList<String>();
    List<String> totals=new ArrayList<String>();
    List<String> restraunts=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_2);


//        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
//        boolean boo;
//        boo=myGlobals.isNetworkConnected();
//        String status ="0";
//        if(boo==true)
//            status="1";
//
//        String activity_name=this.getClass().getSimpleName();
//      //  Toast.makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();
//
//        if(status=="0"){
//            Intent myintent;
//            myintent = new Intent(this,Internet_Status.class);
//            myintent.putExtra("activity name", activity_name);
//
//            startActivity(myintent);
//            finish();
//        }

        Intent intent = getIntent();

        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");



        requestQueue= Volley.newRequestQueue(getApplicationContext());

        /*
        order_items[0]="Lays,Kurkure,biscuits";
        order_items[1]="Sandwitch,banana";
        items_quantity[0]="1,1,1";
        items_quantity[1]="2,1";
        items_total[0]="200";
        items_total[1]="150";
        canteen[0]="Dubey Canteen";
        canteen[1]="Kerala Canteen";
        */

        ///////////////////////////////////////////////////////////////////////


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


                        if(buyer2.equals(username) && status.equals("active")) {
                            uname_present=true;
                            //Log.e("TAG0",computeMD5Hash(password.trim()));
                            Log.e("TAG1",restraunt2);
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
                            items.add(item_list2);
                            totals.add(price2);
                            restraunts.add(restraunt2);
                            quantities.add(qty_list2);
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
                        order_items[k]=items.get(k);
                        items_quantity[k]=quantities.get(k);
                        canteen[k]=restraunts.get(k);
                        items_total[k]=totals.get(k);

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

        Log.e("TAG111",items.size()+"");



    }

    private void pop() {
//        for (int i = 0; i < rows; i++) {
//            countarray[i] = 0;
//        }
        Log.e("TAG101","POP function started");
        int rows= items.size();
        if(rows==0){
            Intent myintent;
            myintent = new Intent(this,No_Current_Orders.class);
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
        for(int i=0;i<rows;i++){
            Log.e("TAG101","Making row "+(i+1));
            count++;



            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
            );
            Log.e("TAG101","Check 1");
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(params);
            tr.setBackgroundResource(0);
            Log.e("TAG101","Check 2");
            params = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT);
            LinearLayout layout = new LinearLayout(this);
            params.weight = 1;
            layout.setLayoutParams(params);
            layout.setBackgroundColor(Color.WHITE);
            layout.setWeightSum(1);
            Log.e("TAG101","Check 3");
    /* Create a Button to be the row-content. */

            LinearLayout.LayoutParams chiledParams = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams.weight = (float) 0.8;
            Button b = new Button(this);
            b.setGravity(Gravity.LEFT);
            String rupee = getString(R.string.Rs);
          //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setText(canteen[count]);
            b.setTextSize(20);
            b.setBackgroundResource(0);
            b.setLayoutParams(chiledParams);
            LinearLayout.LayoutParams chiledParams1 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams1.weight = (float) 0.2;
            Button a = new Button(this);
            a.setGravity(Gravity.LEFT);
            a.setText(rupee+items_total[count]);
            a.setAllCaps(false);
            Log.e("TAG101","Check 4");
            rupee = getString(R.string.Rs);
            //  b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
            a.setBackgroundColor(Color.WHITE);
            a.setTextColor(Color.BLACK);
          //  a.setId(count);
            a.setBackgroundResource(0);
            a.setLayoutParams(chiledParams1);

    /* Add Button to row. */


            Log.e("TAG101","Check 5");
            layout.addView(b);
            layout.addView(a);

            tr.addView(layout);
            table.addView(tr);

            Log.e("TAG101","Check 6");
//////////////////////////////////////
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

                LinearLayout.LayoutParams chiledchildParams = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                chiledchildParams.weight = (float) 0.5;
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
                b2.setLayoutParams(chiledchildParams);
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
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("address",address);
        setResult(RESULT_OK,myIntent);
        finish();
    }

}
