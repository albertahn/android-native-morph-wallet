package com.neman.morph.morphwallet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.neman.morph.morphwallet.Activity.InviteActivity;
import com.neman.morph.morphwallet.Activity.RecieveGasActivity;
import com.neman.morph.morphwallet.Activity.TransactionActivity;
import com.neman.morph.morphwallet.Adapter.StringRecyclerAdapter;
import com.neman.morph.morphwallet.Utils.firebase_utils.BodyFirebaseManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private StringRecyclerAdapter recyclerAdapter;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    public ArrayList<String> publicArray;
    public ArrayList<String> friendsArray;
    public ArrayList<String> privateArray;
    BodyFirebaseManager firebaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        String[] PERMISSIONS_REQUEST = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // Request permission to open the camera and read the ROC.lic file
        Activity activity = (Activity) this;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_REQUEST, 200);

        }
        //send gas button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send Gas to your friends!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                startActivity(new Intent(getApplicationContext(), TransactionActivity.class));


            }
        });

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.parent_tab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateData((String) tab.getText());
                Snackbar.make(tabLayout, tab.getText(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //drawer.setBackgroundResource(R.color.colorPrimary);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_feed).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
        initializeDisplayContent();

        //firebase
        FirebaseApp.initializeApp(this);
        // firebaseManager = new BodyFirebaseManager(getBaseContext());



    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_feed).setChecked(true);
       /* firebaseManager = new BodyFirebaseManager(getBaseContext());
        firebaseManager.updateTransaction();*/
    }

    private void initializeDisplayContent() {

        recycler = (RecyclerView) findViewById(R.id.list_items);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);


        recyclerAdapter = null;

        publicArray = getFeedFrom("Transactions");

        friendsArray = getFeedFrom("Address Book");
        privateArray = getFeedFrom("Secret Transactions");

        recyclerAdapter = new StringRecyclerAdapter(this, getFeedFrom("Public"));
        recycler.setAdapter(recyclerAdapter);


    }

    private void updateData(String type) {

        Toast.makeText(getApplicationContext(), "type: "+ type, Toast.LENGTH_SHORT).show();

        //recycler = (RecyclerView) findViewById(R.id.list_items);
        // lManager = new LinearLayoutManager(this);
        // recycler.setLayoutManager(lManager);
       // pubListdataArray = getFeedFrom(type);

       // Toast.makeText(getApplicationContext(), "type: "+ pubListdataArray.toString(), Toast.LENGTH_SHORT).show();

        ArrayList<String> resData = new ArrayList<String>();


        Log.d("typeman: ", ""+type);

        switch (type){

            case "Transactions":

                resData =  publicArray;

                break;

            case "Address Book":

                resData =  friendsArray;
                break;

            case "Secret Transactions":
                resData =  privateArray;

                break;
        }

        recyclerAdapter = new StringRecyclerAdapter(this, resData);
        recycler.setAdapter(recyclerAdapter);
        //recyclerAdapter.updateData(getFeedFrom(type));
        initializeDisplayContent();


    }




    private ArrayList<String> getFeedFrom(String type) {
        ArrayList<String> list = new ArrayList<>();
        // TODO: further implement this method to pull actual user data
        list.add("transaction **99fg  success ");
        list.add("transaction **dfgg success ");
        list.add("transaction **sffg success ");
/*
        if (type == "Transactions") {
            list.add("Successful transaction to public key ending with 99fg");
            list.add("Successful transaction to public key ending with dfgg");
            list.add("Successful transaction to public key ending with sffg");
            return list;
        }
        else if (type == "Address Book") {
            list.add("Successful transaction to public key ending with 99fg");
            list.add("Successful transaction to public key ending with dfgg");
            list.add("Successful transaction to public key ending with sffg");
            return list;
        }
        else if (type == "Secret Transactions") {
            list.add("Successful transaction to public key ending with 99fg");
            list.add("Successful transaction to public key ending with dfgg");
            list.add("Successful transaction to public key ending with sffg");
            return list;
        }*/

        //TODO
        return list;
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

        if (id == R.id.nav_feed) {
            if (!item.isChecked()) {
                item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

        } else if (id == R.id.nav_transaction) {
            if (!item.isChecked()) {
                item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
            }

        }else if(id == R.id.nav_recieve){
            if (!item.isChecked()) {
                item.setChecked(true);
                startActivity(new Intent(getApplicationContext(),  RecieveGasActivity.class));
            }

        } else if (id == R.id.nav_invite) {
            if (!item.isChecked()) {
                item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), InviteActivity.class));
            }
        }  else if (id == R.id.nav_account) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
