package com.cse327.pothik;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    TextView nav_header_Name;
    TextView nav_header_email;
    NavigationView navigationView;
    ImageView profilePic;
    private TextView get_name,get_address,get_pno;
    Button exlpore;

    int PLACE_PICKER_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setNaveBarValues();

        exlpore=(Button)findViewById(R.id.explore);

        get_name=(TextView)findViewById(R.id.get_name);
        get_address=(TextView)findViewById(R.id.get_address);
        get_pno=(TextView)findViewById(R.id.pno);

        exlpore.setOnClickListener(this);





    }
    public void setNaveBarValues(){
        FirebaseUser mauth = FirebaseAuth.getInstance().getCurrentUser();

        View view =  navigationView.getHeaderView(0);

        nav_header_Name = (TextView)view.findViewById(R.id.name);
        nav_header_email = (TextView)view.findViewById(R.id.email);
        profilePic = (ImageView)view.findViewById(R.id.propic);


        nav_header_Name.setText(mauth.getDisplayName()+"");
        nav_header_email.setText(mauth.getEmail()+"");
        Picasso.with(this).load(mauth.getPhotoUrl()).into(profilePic);



    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.mapview) {
            // Handle the camera action
        } else if (id == R.id.arview) {

        } else if (id == R.id.help) {

        } else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            FacebookSignInFragment.fbLogout();
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            finish();
            startActivity(intent);



        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.explore:
                openMap();

                break;
        }
    }

    private void openMap() {

        PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();


        try {
            Intent intent=builder.build(this);

            startActivityForResult(intent,PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }
}
