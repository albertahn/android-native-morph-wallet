package com.neman.morph.morphwallet.Activity;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.neman.morph.morphwallet.MainActivity;
import com.neman.morph.morphwallet.R;
import com.neman.morph.morphwallet.Utils.barcode.BarcodeCaptureActivity;
import com.neman.morph.morphwallet.Utils.firebase_utils.BodyFirebaseManager;

public class TransactionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView addressToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        addressToSend = (TextView) findViewById(R.id.address);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ImageButton sendPayment = findViewById(R.id.send_button);

        sendPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BodyFirebaseManager firebaseManager = new BodyFirebaseManager(getApplicationContext());
                firebaseManager.updateTransaction();


                Intent intent = new Intent(getApplicationContext(), SuccessPayment.class);
                startActivityForResult(intent, 1);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, 1);



                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                        */
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_transaction).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
        //initializeDisplayContent();
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseApp.initializeApp(getBaseContext());
    }//oncreate

    @Override
   public void onActivityResult(int requestCode,int resultCode, Intent data) {
        if (requestCode == 1) {
                addressToSend.setText("AZgXhB49EKyS31nHQmhdqRfGSFQAN5ZXBV");
                Toast.makeText(getApplicationContext(), "got address", Toast.LENGTH_SHORT).show();

        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_transaction).setChecked(true);
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
        getMenuInflater().inflate(R.menu.transaction, menu);
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

        }  else if (id == R.id.nav_invite) {
            if (!item.isChecked()) {
                item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), InviteActivity.class));
            }
        } else if (id == R.id.nav_account) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
