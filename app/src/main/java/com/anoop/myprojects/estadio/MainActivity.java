package com.anoop.myprojects.estadio;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.anoop.myprojects.estadio.session_manager.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Intent intent = getIntent();

        //String id = intent.getStringExtra("userId");
        String isowner = intent.getStringExtra("isowner");

//        System.out.println(isowner);

        if(!isowner.isEmpty() && isowner.equals("player"))
        {
            hideItem();
        }
        else
        {
            hideItemForOwner();
        }


//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.app_name, R.string.app_name);
//
//        mDrawerToggle.getDrawerArrowDrawable().setColor(Color.BLACK);
////        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
////            Drawable drawable = getDrawable(R.drawable.ic_menu_black_24dp);
////            mDrawerToggle.setDrawerArrowDrawable((DrawerArrowDrawable) drawable);
////        }
//        //mDrawerToggle.setDrawerArrowDrawable(getResources().getDrawable().);
//        drawer.addDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
                if (id==R.id.nav_send){
                    Session session = new Session(MainActivity.this);
                    session.logOut();

                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    finish();

                }
                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem,navController);
                //This is for closing the drawer after acting on it
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        //System.out.println("Hello1");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void hideItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_gallery).setVisible(false);
        nav_Menu.findItem(R.id.nav_slideshow).setVisible(false);
        nav_Menu.findItem(R.id.nav_tools).setVisible(false);
    }

    private void hideItemForOwner()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_share).setVisible(false);
    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        System.out.println("Hello");
//        if(menuItem.getItemId() == R.id.nav_send)
//        {
//            Session session = new Session(this);
//            session.logOut();
//
//            Intent intent = new Intent(this,Login.class);
//            startActivity(intent);
//            finish();
//        }
//
//        super.onOptionsItemSelected(menuItem);
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        System.out.println("Hello");
//
//        System.out.println(item.getItemId());
//
//        return super.onOptionsItemSelected(item);
//    }
}
