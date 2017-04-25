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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Delete_item_seller extends AppCompatActivity {
    String username=" ",name=" ",email=" ",address=" ",category=" ",restraunt=" ",mobile=" ";
    RequestQueue requestQueue;
    String insertURL="http://10.1.1.19/~2015CSB1002/PHPLAB/delete_item.php";
    String []items=new String[100];
    int []cost=new int[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item_seller);
        MyGlobals myGlobals = new MyGlobals(getApplicationContext());

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        boolean boo;
//        items[0]="ok";
//        items[1]="feidk";

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
        items = intent.getStringArrayExtra("menu");
        cost=  intent.getIntArrayExtra("cost");
        pop();
    }

    private void pop() {
        int rows=0;
      //  Log.e("TAG111111-----------",rows+"");
        //final String[] s=new String[items.size()];
       // items.toArray(s);
       // int countarray[] = new int[s.length];
        // TextView name1 = (TextView) findViewById(R.id.kitchen_name);
        //name1.setText(restraunt + " Kitchen");
//        for (int i = 0; i < rows; i++) {
//            countarray[i] = 0;
//        }
        Log.e("TAG01010101-----------",items.length+"");
        for(int i=0;i<100;i++)
        {
            if(cost[i]==0)
            {
                rows=i;
                break;
            }
        }
        TableLayout table = (TableLayout) findViewById(R.id.seller_table_delete);
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
            b.setText(items[count]);
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(count);
            b.setBackgroundResource(0);
            b.setOnClickListener(new View.OnClickListener() {
                //
                @Override
                public void onClick(View v) {
                     Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                    int name1=v.getId();
                    final String deleted_name=items[name1];
                    Log.e("TAG01010101-----------",deleted_name);
                    ////////////////////////////////////////Deleting code////////////////////


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
                            parameters.put("item",deleted_name);

                            return parameters;
                        }
                    };
                    Log.d("TAG7","sdding the item to the database");
                    Log.d("TAG8",deleted_name);
                    requestQueue.add(request);



                    Intent myIntent;
                    myIntent=new Intent(Delete_item_seller.this, Seller_main.class);
                    myIntent.putExtra("name", name);
                    myIntent.putExtra("email", email);
                    myIntent.putExtra("username",username);

                    myIntent.putExtra("mobile",mobile);
                    myIntent.putExtra("category",category);
                    myIntent.putExtra("address",address);
                    myIntent.putExtra("restraunt",restraunt);
                    startActivity(myIntent);

                    finish();
                }
            });


            b.setLayoutParams(chiledParams);

    /* Add Button to row. */

//
//            LinearLayout.LayoutParams chiledParams1 = new LinearLayout.LayoutParams(0,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            chiledParams1.weight = (float) 0.1;
//            Button a = new Button(this);
//            a.setText("+");
//            a.setId(count + 1000);
//            a.setLayoutParams(chiledParams1);
//            // a.setBackgroundColor(Color.WHITE);
//            a.setBackgroundResource(0);
//            a.setTextColor(Color.BLACK);
//            a.setOnClickListener(new View.OnClickListener() {
//                //
//                @Override
//                public void onClick(View v) {
//                    // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-1000)+" "+count, Toast.LENGTH_SHORT).show();
//                    //switch_activity(v.getId());
//                    //  Increase_or_decrease_Integer((v.getId() - 1000), 1);
//
//                }
//            });
//
//
            LinearLayout.LayoutParams chiledParams2 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            chiledParams2.weight = (float) 0.2;
            TextView c = new TextView(this);
            c.setText(cost[count]+"");
            //    c.setBackgroundColor(Color.YELLOW);
            c.setGravity(Gravity.CENTER);
         //   c.setId(count + 10000);
            c.setTextColor(Color.BLACK);
            c.setBackgroundResource(0);
            c.setLayoutParams(chiledParams2);
////
//
//            LinearLayout.LayoutParams chiledParams3 = new LinearLayout.LayoutParams(0,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            chiledParams3.weight = (float) 0.1;
//            Button d = new Button(this);
//            d.setText("-");
//            d.setBackgroundColor(Color.WHITE);
//            d.setTextColor(Color.BLACK);
//            d.setId(count + 100000);
//            d.setBackgroundResource(0);
//            d.setOnClickListener(new View.OnClickListener() {
//                //
//                @Override
//                public void onClick(View v) {
//                    // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-100000)+" "+count, Toast.LENGTH_SHORT).show();
//                    //switch_activity(v.getId());
//                    // Increase_or_decrease_Integer((v.getId() - 100000), 0);
//
//                }
//            });
//            d.setLayoutParams(chiledParams3);

            layout.addView(b);
         //   layout.addView(a);
          layout.addView(c);
         //   layout.addView(d);
            tr.addView(layout);
            table.addView(tr);


        }
    }
}
