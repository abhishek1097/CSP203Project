package com.example.user.fazooz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class payment extends AppCompatActivity {

    RequestQueue requestQueue;
    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/order.php";
    int total = 0, count = -1;
    String allitems;
    String allqty;
    String username, name, email, address, category, restraunt, mobile;
    int count_array[] = new int[100];
    String items_array[] = new String[100];

    public void complete_payment(View v) {

        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(payment.this);
        View mview = getLayoutInflater().inflate(R.layout.order_complete_dialog, null);
        mbuilder.setView(mview);
        String rupee = getString(R.string.Rs);
        final TextView cost_text = (TextView) mview.findViewById(R.id.cost_text);
        final TextView restrant_text = (TextView) mview.findViewById(R.id.restraunt_text);
        cost_text.setText(rupee + total + " to ");
        restrant_text.setText(restraunt);
        mbuilder.setCancelable(false);
        mbuilder.setTitle("Order is successfully placed!");
        mbuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        // sUsername = editText.getText().toString();
                        Intent intent = new Intent(payment.this, Fazooz.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("username", username);
                        intent.putExtra("restraunt", " ");
                        intent.putExtra("category", " ");
                        intent.putExtra("address", address);
                        intent.putExtra("mobile", mobile);
                        /////////////////////////////////////////////////////////////

                        // Code for sending the order to the database

                        allitems = items_array[0];
                        allqty = Integer.toString(count_array[0]);

                        for (int i = 1; i < 100; i++) {
                            if (items_array[i] !=null) {
                                allitems = allitems + "," + items_array[i];
                                allqty = allqty + "," + count_array[i];
                            }
                        }





                        //output.append("Creating account...\n");
                        Log.e("TAG00000000000","PLACING ORDER");
                        final String pay="COD";
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

                                parameters.put("buyer",username);
                                parameters.put("seller",restraunt);
                                parameters.put("item_name",allitems);
                                //parameters.put("password",password.getText().toString());
                                parameters.put("qty",allqty);
                                parameters.put("price",Integer.toString(total));
                                parameters.put("address",address);
                                parameters.put("pay",pay);
                                parameters.put("status","active");


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
                        Log.d("TAG7777","END OF ADD");
                        Log.d("TAG888888",allitems);
                        requestQueue.add(request);








                        ///////////////////////////////////////////////////////////
//                        intent.putExtra("username",username);
//                        intent.putExtra("name",name);
//                        intent.putExtra("email",email);
//                        intent.putExtra("mobile",mobile);
//                        intent.putExtra("category",category);
//                        intent.putExtra("address",address);
//                        intent.putExtra("restraunt",restraunt);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = mbuilder.create();
        dialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

        MyGlobals myGlobals = new MyGlobals(getApplicationContext());
        boolean boo;
        boo = myGlobals.isNetworkConnected();
        String status = "0";
        if (boo == true)
            status = "1";

        String activity_xname = this.getClass().getSimpleName();
      //  Toast.makeText(getBaseContext(), status + activity_xname, Toast.LENGTH_LONG).show();

        if (status == "0") {
            Intent myintent;
            myintent = new Intent(this, Internet_Status.class);
            myintent.putExtra("activity name", activity_xname);

            startActivity(myintent);
            finish();
        }
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        restraunt = intent.getStringExtra("restraunt");
        category = intent.getStringExtra("category");
        address = intent.getStringExtra("address");
        mobile = intent.getStringExtra("mobile");
        count_array = intent.getIntArrayExtra("sending count array");
        items_array = intent.getStringArrayExtra("sending items array");
        total = intent.getIntExtra("total", 0);
        TextView textView = (TextView) findViewById(R.id.final_total);
        String rupee = getString(R.string.Rs);
        textView.setText(rupee + total + "");

        TableLayout table = (TableLayout) findViewById(R.id.dynamic_texts);

        for (int i = 0; items_array[i] != null; i++) {

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
            b.setText(items_array[count]+"  ("+count_array[count]+")");
            b.setAllCaps(false);
            b.setTextSize(18);
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setBackgroundResource(0);
            b.setLayoutParams(chiledParams);

    /* Add Button to row. */


            layout.addView(b);

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

        // Toast.makeText(getApplicationContext(), "Button"+ items_array[0]+count_array[0]+""+" "+total, Toast.LENGTH_SHORT).show();
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
}
