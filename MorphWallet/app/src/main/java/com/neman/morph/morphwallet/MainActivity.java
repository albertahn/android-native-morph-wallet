package com.neman.morph.morphwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.neman.morph.morphwallet.Activity.InviteActivity;
import com.neman.morph.morphwallet.Activity.TransactionActivity;
import com.neman.morph.morphwallet.Adapter.StringRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private StringRecyclerAdapter recyclerAdapter;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    public ArrayList<String> pubListdataArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        this.setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_feed).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
        initializeDisplayContent();

    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_feed).setChecked(true);
    }

    private void initializeDisplayContent() {

        recycler = (RecyclerView) findViewById(R.id.list_items);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);


        recyclerAdapter = null;
        recyclerAdapter = new StringRecyclerAdapter(this, getFeedFrom("public"));
        recycler.setAdapter(recyclerAdapter);


    }

    private void updateData(String type) {



        //recycler = (RecyclerView) findViewById(R.id.list_items);
        // lManager = new LinearLayoutManager(this);
        // recycler.setLayoutManager(lManager);
        pubListdataArray = getFeedFrom(type);

        Toast.makeText(getApplicationContext(), "type: "+ pubListdataArray.toString(), Toast.LENGTH_SHORT).show();

        recyclerAdapter = new StringRecyclerAdapter(this, pubListdataArray);
        recycler.setAdapter(recyclerAdapter);
        //recyclerAdapter.updateData(getFeedFrom(type));
    }




    private ArrayList<String> getFeedFrom(String type) {
        ArrayList<String> list = new ArrayList<>();
        // TODO: further implement this method to pull actual user data


        if (type == "public") {
            list.add("Public: bill"); list.add("Public: harry");  list.add("Public: Jimmy");
            return list;
        }
        else if (type == "friends") {
            list.add("Friends: joe"); list.add("Friends: Kim");
            return list;
        }
        else if (type == "private") {
            list.add("Private: mom"); list.add("Private: pops");
            return list;
        }

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
