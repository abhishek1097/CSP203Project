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

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity {
     int rows ;
     int col ;
    int count=-1;
    int total=0;
    int costarray[]=new int [100];
    int countarray[]=new int [100];
    int send_count_array[]=new int [100];
    String send_items_array[]=new String [100];
    String username,name,email,address,category,restraunt,mobile;
    String showURL="http://10.1.1.19/~2015CSB1002/PHPLAB/show_items.php";
    //String s[ ]={"fdf","faDFD","FDSF","FWEF","yu","ga","nd","ha","ra","re","as","wd","daddas","sac","wsd","dcw","CSAC","wsz","xD","fdc","fsdaf"};


    RequestQueue requestQueue;

    List<String> items=new ArrayList<String>();
    List<String> prices=new ArrayList<String>();
    //List<String> restraunts=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo=myGlobals.isNetworkConnected();
        String status ="0";
        if(boo==true)
            status="1";

        String activity_name=this.getClass().getSimpleName();
       // Toast .makeText(getBaseContext(), status+activity_name, Toast.LENGTH_LONG).show();

        if(status=="0"){
            Intent myintent;
            myintent = new Intent(this,Internet_Status.class);
            myintent.putExtra("activity name", activity_name);

            startActivity(myintent);
        }
        Button total=(Button)findViewById(R.id.total_button);
        total.setVisibility(View.GONE);
        Button pay=(Button)findViewById(R.id.pay_button);
        pay.setVisibility(View.GONE);

        Intent intent = getIntent();

        name  = intent.getStringExtra("name");
        email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt  = intent.getStringExtra("restraunt");
        category  = intent.getStringExtra("category");
        address  = intent.getStringExtra("address");
        mobile  = intent.getStringExtra("mobile");

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        TextView rest=(TextView)findViewById(R.id.rest_name);
        rest.setText(restraunt+"'s Menu");


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


                        //Log.e("TAG0000",username2);
                        //Log.e("TAG00001",username);


                        //result.append(name1+" "+eno1+" "+bp1+" "+pin1+" "+state1+"\n");

                    }
                    for(int i=0;i<100;i++)
                        costarray[i]=10;
                    Button b =(Button)findViewById(R.id.total_button);
                    Button pay_button =(Button)findViewById(R.id.pay_button);
                    b.setVisibility(View.GONE);
                    pay_button.setVisibility(View.GONE);
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


    }

        //////////
        void pop() {


            rows=items.size();
            Log.e("TAG111111-----------",rows+"");
            final String[] s=new String[items.size()];
            final String[] pr=new String[prices.size()];
            final int[] p=new int[prices.size()];
            items.toArray(s);
            prices.toArray(pr);
            for(int i=0;i<items.size();i++)
            {
                p[i]=Integer.parseInt(pr[i]);
                costarray[i]=p[i];
            }
            int countarray[] = new int[s.length];
           // TextView name1 = (TextView) findViewById(R.id.kitchen_name);
            //name1.setText(restraunt + " Kitchen");
            for (int i = 0; i < rows; i++) {
                countarray[i] = 0;
            }
            TableLayout table = (TableLayout) findViewById(R.id.Tables);
            int count = -1;
            for(int i=0;i<rows;i++){

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
                b.setTextSize(18);
                String rupee = getString(R.string.Rs);
                b.setText(s[count]+" "+"("+rupee+costarray[count]+")");
                b.setAllCaps(false);
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
                a.setId(count+1000);
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
                        Increase_or_decrease_Integer((v.getId()-1000),1);

                    }
                });





                LinearLayout.LayoutParams chiledParams2 = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                chiledParams2.weight = (float) 0.2;
                TextView c = new TextView(this);
                c.setText("0");
                //    c.setBackgroundColor(Color.YELLOW);
                c.setGravity(Gravity.CENTER);
                c.setId(count+10000);
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
                d.setId(count+100000);
                d.setBackgroundResource(0);
                d.setOnClickListener(new View.OnClickListener() {
                    //
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-100000)+" "+count, Toast.LENGTH_SHORT).show();
                        //switch_activity(v.getId());
                        Increase_or_decrease_Integer((v.getId()-100000),0);

                    }
                });
                d.setLayoutParams(chiledParams3);

                layout.addView(b);
                layout.addView(d);
                layout.addView(c);
                layout.addView(a);
                tr.addView(layout);
                table.addView(tr);







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
//            for (int i = 0; i < rows; i++) {
//                Log.e("TAG11111122-----------","iteration"+i);
//                TableRow tablerow = new TableRow(this);
//                table.addView(tablerow);
//                count++;
//                Log.e("TAG11111133-----------","iteration"+i);
//                for (int j = 0; j < col; j++) {
//
//                    if (j == 0) {
//                        Button button = new Button(this);
//                        button.setText(s[count]);
//                        button.setTextColor(Color.BLACK);
//                        button.setId(count*i+j);
//                        //   button.setBackgroundColor(Color.WHITE);
//                        //button.setTextSize(10);
//                        //button.setPadding(8, 3, 8, 3);
//                        tablerow.addView(button);
//
//                    } else {
//                        Button button = new Button(this);
//
//                        if (j == 1) {
//                            button.setText("-");
//                            button.setBackgroundResource(R.drawable.round_button);
//                        }
//                        if (j == 2) {
//                            TextView textView = new TextView(this);
//                            String text = countarray[i] + "";
//                            textView.setText(text);
//                            textView.setBackgroundColor(Color.WHITE);
//
//                        } else if (j == 3) {
//                            button.setText("+");
//                            button.setBackgroundResource(R.drawable.round_button);
//                        }
//                        button.setWidth(25);
//                        button.setId(count*i+j);
//
//                        // button.setTextSize(10);
//                        //  button.setPadding(8, 3, 8, 3);
//                        tablerow.addView(button);
//                    }
//
//                }
//                Log.e("TAG111111444-----------","iteration"+i);
//            }
    }

    private void Increase_or_decrease_Integer(int i, int j) {
        int minteger=countarray[i];
        int minteger2=costarray[i];
        if(j==1){
            minteger = minteger + 1;
            total = total + minteger2;}
        if(j==0){
            if(minteger<=0){
                Toast.makeText(getApplicationContext(),"Count can't be negative!!",Toast.LENGTH_SHORT).show();
            }
            else{
                minteger = minteger - 1;
                total = total - minteger2;
            }
        }
        countarray[i]=minteger;

        display(minteger,i);
    }
    public void proceed_payment(View v)
    {

        Intent myIntent = new Intent(v.getContext(),payment.class);

        int k=0;
        for(int i=0;i<100;i++){
            if(countarray[i]!=0){
                send_count_array[k]=countarray[i];
                send_items_array[k]=items.get(i);
                k++;
            }
        }
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("username",username);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("name",name);
        myIntent.putExtra("sending count array",send_count_array);
        myIntent.putExtra("sending items array",send_items_array);
        myIntent.putExtra("total",total);
        startActivity(myIntent);

    }
    private void display(int number,int i) {
        TextView displayInteger = (TextView) findViewById((i+10000));
        displayInteger.setText("" + number);
        Button total_button = (Button)findViewById(R.id.total_button);
        Button pay_button = (Button)findViewById(R.id.pay_button);
        if(total==0){
            total_button.setVisibility(View.GONE);
            pay_button.setVisibility(View.GONE);
        }
        else{
            String rupee = getString(R.string.Rs);
            total_button.setVisibility(View.VISIBLE);
            pay_button.setVisibility(View.VISIBLE);
            total_button.setText(rupee+total+"");
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
        Intent myIntent=new Intent(this,OrderActivity.class);
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

}

