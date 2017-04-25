package com.example.user.fazooz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class Seller_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
String username,name,email,restraunt,category,address,mobile;

    public void update_menu(View view){
        Intent myIntent = new Intent(this,Update_Menu.class);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("username",username);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("name",name);
       // myIntent.putExtra("sending count array",send_count_array);
       // myIntent.putExtra("sending items array",send_items_array);
       // myIntent.putExtra("total",total);
        startActivity(myIntent);
    }
    public void update_menu_1(){
        Intent myIntent = new Intent(this,Update_Menu.class);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("username",username);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("name",name);
        // myIntent.putExtra("sending count array",send_count_array);
        // myIntent.putExtra("sending items array",send_items_array);
        // myIntent.putExtra("total",total);
        startActivity(myIntent);
    }
    public void current_orders(View view){
        Intent myIntent = new Intent(this,Seller_Current.class);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("username",username);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("name",name);
        // myIntent.putExtra("sending count array",send_count_array);
        // myIntent.putExtra("sending items array",send_items_array);
        // myIntent.putExtra("total",total);
        startActivity(myIntent);
    }

    public void current_orders_1(){
        Intent myIntent = new Intent(this,Seller_Current.class);
        myIntent.putExtra("restraunt",restraunt);
        myIntent.putExtra("username",username);
        myIntent.putExtra("email",email);
        myIntent.putExtra("mobile",mobile);
        myIntent.putExtra("category",category);
        myIntent.putExtra("address",address);
        myIntent.putExtra("name",name);
        // myIntent.putExtra("sending count array",send_count_array);
        // myIntent.putExtra("sending items array",send_items_array);
        // myIntent.putExtra("total",total);
        startActivity(myIntent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        this.setTitle("Foodzie");


        Intent intent = getIntent();

        name  = intent.getStringExtra("name");
         email  = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
         restraunt  = intent.getStringExtra("restraunt");
         category  = intent.getStringExtra("category");
         address  = intent.getStringExtra("address");
         mobile  = intent.getStringExtra("mobile");

        Log.e("TAG__USERNAME",username);
        Log.e("TAG__NAME",name);
        Log.e("TAG__MOBILE",mobile);
        Log.e("TAG__CATEGORY",category);
        Log.e("TAG__ADDRESS",address);
        Log.e("TAG__RESTRAUNT",restraunt);
        Log.e("TAG__EMAIL",email);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
      set_hyder();


    }

    public void set_hyder() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView name1 = (TextView)header.findViewById(R.id.seller_name);
        TextView email1 = (TextView)header.findViewById(R.id.seller_email);
        name1.setText(name);
        email1.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Seller_main.this);
                builder.setMessage("Are you sure want to exit the app ?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        finish();
                    }
                });

                builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_seller_home) {

            Home_Seller home_seller = new Home_Seller();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment_seller , home_seller , home_seller.getTag()).commit();
            getSupportActionBar().setTitle("Foodzie");

        } else if (id == R.id.nav_seller_profile) {

            profile_act();


        }
        else if (id == R.id.nav_seller_current_orders) {

           current_orders_1();


        }
        else if (id == R.id.nav_seller_update_menu) {

            update_menu_1();


        }else if (id == R.id.nav_seller_share) {
            share_the_app();

        } else if (id == R.id.nav_seller_contact_us) {


           about_seller aboutUs = new about_seller();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment_seller , aboutUs , aboutUs.getTag()).commit();
            getSupportActionBar().setTitle("About Us");


        }
        else if (id==R.id.nav_seller_logout){
            log_out();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void profile_act() {

            Intent myintent;
            myintent = new Intent(this,Profile_Activity.class);
            myintent.putExtra("name", name);
            myintent.putExtra("username",username);
            myintent.putExtra("email",email);
            myintent.putExtra("mobile",mobile);
            myintent.putExtra("category",category);
            myintent.putExtra("address",address);
            myintent.putExtra("restraunt",restraunt);
            startActivityForResult(myintent,999);


    }

    private void log_out() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }



    private void share_the_app() {
        ApplicationInfo applicationInfo=getApplicationContext().getApplicationInfo();
        String path= applicationInfo.sourceDir;
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
        startActivity(Intent.createChooser(intent,"Share App Via"));
    }
//Via    @Override
//    public void onFragmentInteraction(String n1,String u1,String m1,String a1,String e1,String c1,String r1,int i) {
//        Toast.makeText(this,a1,Toast.LENGTH_SHORT).show();
//        if(i==1){
////        name=n1;
////        username=u1;
////        email=e1;
////        mobile=m1;
////      //  category=c1;
////        address=a1;
//            // restraunt=r1;
//        }
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            name=data.getStringExtra("name");
            email=data.getStringExtra("email");
            address=data.getStringExtra("address");
            mobile=data.getStringExtra("mobile");
            set_hyder();
        }
    }


}
