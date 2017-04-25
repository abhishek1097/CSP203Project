package com.example.user.fazooz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import layout.CameraFragment;

public class Fazooz extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , BlankFragment.OnFragmentInteractionListener {
    public Button button7,button8,button9,button10;
   TextView temail;
    private ActionBarDrawerToggle mDrawerToggle;
    String username,name,email,address,category,restraunt,mobile;
    int decider=0;
    public void cartzz(View v){
        Intent myintent;
        myintent = new Intent(this,Items.class);
        myintent.putExtra("name", name);
        myintent.putExtra("username",username);
        myintent.putExtra("email",email);
        myintent.putExtra("mobile",mobile);
        myintent.putExtra("category",category);
        myintent.putExtra("address",address);
        myintent.putExtra("restraunt","Kerala Cafe");
        startActivity(myintent);
    }

    public void img_click_2(View v){
        Intent myintent;
        myintent = new Intent(this,Items.class);
        myintent.putExtra("name", name);
        myintent.putExtra("username",username);
        myintent.putExtra("email",email);
        myintent.putExtra("mobile",mobile);
        myintent.putExtra("category",category);
        myintent.putExtra("address",address);
        myintent.putExtra("restraunt","Kerala Cafe");
        startActivity(myintent);
    }

    public void img_click_3(View v){
        Intent myintent;
        myintent = new Intent(this,Items.class);
        myintent.putExtra("name", name);
        myintent.putExtra("username",username);
        myintent.putExtra("email",email);
        myintent.putExtra("mobile",mobile);
        myintent.putExtra("category",category);
        myintent.putExtra("address",address);
        myintent.putExtra("restraunt","Dubey Cafe");
        startActivity(myintent);
    }
    public void init2(){

        Intent myintent;
        myintent = new Intent(this,OrderActivity.class);
        myintent.putExtra("name", name);
        myintent.putExtra("username",username);
        myintent.putExtra("email",email);
        myintent.putExtra("mobile",mobile);
        myintent.putExtra("category",category);
        myintent.putExtra("address",address);
        myintent.putExtra("restraunt",restraunt);
            startActivityForResult(myintent,1000);

    }

    public void profile_activity(){

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

    public void init1(){
        Intent myintent;
        myintent = new Intent(this,Current_Activity2.class);
        myintent.putExtra("name", name);
        myintent.putExtra("username",username);
        myintent.putExtra("email",email);
        myintent.putExtra("mobile",mobile);
        myintent.putExtra("category",category);
        myintent.putExtra("address",address);
        myintent.putExtra("restraunt",restraunt);
        startActivity(myintent);
    }

public void log_out(){
    startActivity(new Intent(this,LoginActivity.class));
    finish();
}

//    public void init2(){
//        startActivity(new Intent(this,CurrentActivity.class));
//    }

    public void init3(){
        button9=(Button)findViewById(R.id.button7);
        button9.setVisibility(View.INVISIBLE);
        button9.postDelayed(new Runnable() {
            public void run() {
                button9.setVisibility(View.VISIBLE);
            }
        }, 7000);
    }

    Animation left_in, left_out;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            Fazooz.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }


    Animation fade_in , fade_out;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazooz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
      //  Toast.makeText(getBaseContext(),username, Toast.LENGTH_LONG).show();

        Log.e("TAG__USERNAME",username);
        Log.e("TAG__NAME",name);
        Log.e("TAG__MOBILE",mobile);
        Log.e("TAG__CATEGORY",category);
        Log.e("TAG__ADDRESS",address);
        Log.e("TAG__RESTRAUNT",restraunt);
        Log.e("TAG__EMAIL",email);



//        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
//        setContentView(R.layout.nav_header_fazooz);
//       TextView tname=(TextView)findViewById(R.id.user_name);
//        tname.setText("qwerty");


////////////////////////////////////



        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        ViewPageAdapter viewPagerAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


        ////////////////////////////////////

        set_header();
//
//
//        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
//        fade_in = AnimationUtils.loadAnimation(this,
//                android.R.anim.fade_in);
//        fade_out = AnimationUtils.loadAnimation(this,
//                android.R.anim.fade_out);
//        viewFlipper.setInAnimation(fade_in);
//        viewFlipper.setOutAnimation(fade_out);
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(5000);
//        viewFlipper.startFlipping();
//


    }

    public void set_header() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView name1 = (TextView)header.findViewById(R.id.user_name);
        TextView email1 = (TextView)header.findViewById(R.id.user_email);
        name1.setText(username);
        email1.setText(email);

        Button b1=(Button)findViewById(R.id.order_now);
        Button b2=(Button)findViewById(R.id.current_order_foodzie);
        b1.setOnClickListener(new View.OnClickListener() {
            //
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-1000)+" "+count, Toast.LENGTH_SHORT).show();
                //switch_activity(v.getId());
      //          Increase_or_decrease_Integer((v.getId()-1000),1);
                init2();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            //
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), "Button"+ v.getId()+" "+(v.getId()-1000)+" "+count, Toast.LENGTH_SHORT).show();
                //switch_activity(v.getId());
                //          Increase_or_decrease_Integer((v.getId()-1000),1);
                init1();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fr = getSupportFragmentManager().findFragmentByTag("homefr");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
       else if (fr==null&&decider==1) {
            drawer.openDrawer(GravityCompat.START);
        }
        else {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Fazooz.this);
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
        getMenuInflater().inflate(R.menu.fazooz, menu);
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
        decider = 1;
        if (id == R.id.nav_home) {
            CameraFragment cameraFragment = new CameraFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment , cameraFragment , "homefr").commit();
            getSupportActionBar().setTitle("Foodzie");
        } else if (id == R.id.nav_orders) {
          init2();

        } else if (id == R.id.nav_address) {
//            Profile_Fragment profileFragment = Profile_Fragment.newInstance(name,username,mobile,address,email);
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativelayout_for_fragment ,profileFragment).commit();
//            getSupportActionBar().setTitle("Share");

            profile_activity();

        } else if (id == R.id.nav_coupons) {
            init1();
//
//            DealFragment dealFragment = new DealFragment();
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativelayout_for_fragment , dealFragment , dealFragment.getTag()).commit();
//            getSupportActionBar().setTitle("Deals and Coupons");


        } else if (id == R.id.nav_share) {
           share_the_app();

//            Profile_Fragment profileFragment = Profile_Fragment.newInstance(name,username,mobile,address,email);
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativelayout_for_fragment ,profileFragment).commit();
//            getSupportActionBar().setTitle("Share");

        } else if (id == R.id.nav_About) {
            AboutUs aboutUs = new AboutUs();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment , aboutUs , aboutUs.getTag()).commit();
            getSupportActionBar().setTitle("About Us");
        }

        else if (id == R.id.nav_logout) {
log_out();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void share_the_app() {
        ApplicationInfo applicationInfo=getApplicationContext().getApplicationInfo();
        String path= applicationInfo.sourceDir;
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
        startActivity(Intent.createChooser(intent,"Share App Via"));
    }
    @Override
    public void onFragmentInteraction(String n1,String u1,String m1,String a1,String e1,String c1,String r1,int i) {
       // Toast.makeText(this,a1,Toast.LENGTH_SHORT).show();
       if(i==1){
//        name=n1;
//        username=u1;
//        email=e1;
//        mobile=m1;
//      //  category=c1;
//        address=a1;
       // restraunt=r1;
           }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode==999) {
            name=data.getStringExtra("name");
            email=data.getStringExtra("email");
            address=data.getStringExtra("address");
            mobile=data.getStringExtra("mobile");
        }

        else{
            name=data.getStringExtra("name");
            category=data.getStringExtra("category");
            mobile=data.getStringExtra("mobile");
            username=data.getStringExtra("username");
            email=data.getStringExtra("email");
            address=data.getStringExtra("address");
            mobile=data.getStringExtra("mobile");
        }

    }
}
