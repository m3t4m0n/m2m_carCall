package com.excavator.m2m.campus_ieum;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String user_pw = intent.getStringExtra("user_pw");
        */

        SharedPreferences sharedPreferences = getSharedPreferences("user_auth", 0);
        String user_id = sharedPreferences.getString("id", "");

        String url = "http://temp_m2m.pilot0613.com/user/getInfo";
        ContentValues paramValues = new ContentValues();
        paramValues.put("name", user_id);
        Log.i("MAIN_USER_NAME", user_id);

        NetworkTask networkTask = new NetworkTask(url, paramValues);
        String result = null;
        try {
            result = networkTask.execute().get();
            JSONObject userObject = new JSONObject(result);

            // instance holder static singleton 유저 정보 저장
            UserAuthInfo.getInstance().receiveObject(userObject);

        } catch(Exception e) {
            e.printStackTrace();
        }

        // UserAuthInfo userAuthInfo = UserAuthInfo.getInstance();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_home, new HomeActivity()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        manager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            manager.beginTransaction().replace(R.id.content_home, new HomeActivity()).commit();

        } else if (id == R.id.nav_bell) {
            manager.beginTransaction().replace(R.id.content_home, new BellActivity()).commit();

        } else if (id == R.id.nav_car) {
            manager.beginTransaction().replace(R.id.content_home, new CarActivity()).commit();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
