package com.example.blitz_t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Views.DesignApp;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeCustomerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private ContextWrapper mContextWrapper;
    private ImageView image_cni_customer;
    private ImageView image_profile_customer;
    private TextView text_customer_name;
    private TextView text_customer_phone_number;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        mContextWrapper = this;

        mCustomer = (Customer) Model.contentPreference(
                new Customer(),
                getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        if(mCustomer == null){
            Intent intent = new Intent(getApplicationContext(), HomeMemberActivity.class);
            startActivity(intent);
            finish();
        }

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

//        navigationView.addHeaderView(Objects.requireNonNull(new NavHeader().getView()));

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
                    new HomeCustomerFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        image_cni_customer = findViewById(R.id.image_cni_customer);
        image_profile_customer = findViewById(R.id.image_profile_customer);
        text_customer_name = findViewById(R.id.text_customer_name);
        text_customer_phone_number = findViewById(R.id.text_customer_phone_number);

//        DesignApp.updateImage(getApplicationContext() , image_cni_customer , mCustomer.getMember().getCni_copy() , null);
//        DesignApp.updateImage(getApplicationContext() , image_profile_customer , mCustomer.getMember().getProfile_picture() , null);
//        text_customer_name.setText(mCustomer.getMember().getFull_name());
//        text_customer_phone_number.setText(mCustomer.getMember().getPhone_number());

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
        Intent intent = null;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeCustomerFragment()).commit();
                this.setTitle("Home | " + mMicrofinance.getNom());
                break;
            case R.id.nav_transaction:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TransactionFragment()).commit();
                this.setTitle("Transaction | " + mMicrofinance.getNom());
                break;
            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllAccountFragment()).commit();
                this.setTitle("Accounts | " + mMicrofinance.getNom());
                break;
            case R.id.nav_agency_point:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MicrofinanceAgencyFragment()).commit();
                this.setTitle("Agencies points | " + mMicrofinance.getNom());
                break;
            case R.id.nav_benefit:
                Toast.makeText(this, "Beneficiaries", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_log_out:
                Model.saveFormPreference(null, getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                        getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );
                intent = new Intent(getApplicationContext(), HomeMemberActivity.class);
                break;
            case R.id.nav_suggestion:
                Toast.makeText(this, "Suggestions", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        if(intent != null) {
            startActivity(intent);
            finish();
        }
        return true;
    }
}
