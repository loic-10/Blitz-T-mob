package com.example.blitz_t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
//    private BlurView blurView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        blurView = findViewById(R.id.blurView);
//        blurBackground();

        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public void onBackPressed () {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem menuItem ) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                this.setTitle("Home");
                break;
            case R.id.nav_transaction:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TransactionFragment()).commit();
                this.setTitle("Transaction");
                break;
            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountClientFragment()).commit();
                this.setTitle("Accounts");
                break;
            case R.id.nav_agency_point:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MicrofinanceAgenceFragment()).commit();
                this.setTitle("Agencies points");
                break;
            case R.id.nav_benefit:
                Toast.makeText(this, "Beneficiaries", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_log_out:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_suggestion:
                Toast.makeText(this, "Suggestions", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
