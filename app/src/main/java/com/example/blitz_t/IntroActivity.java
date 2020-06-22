package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.example.blitz_t.Controllers.IntroPagerAdapter;
import com.example.blitz_t.Views.Customer.Activity.HomeCustomerActivity;
import com.example.blitz_t.Views.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Button mButton;
    private Button mButtonStarted;
    private IntroPagerAdapter mIntroViewPagerAdapter;
    private List<ScreenItem> mScreenItems;
    private TabLayout mTabLayout;
    private int position = 0;
    private Animation btnAnim;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if(restorePrefData()){
            Intent mIntent = new Intent(getApplicationContext(), HomeCustomerActivity.class);
            startActivity(mIntent);
            finish();
        }

        mViewPager = findViewById(R.id.viewpager_intro);
        mTabLayout = findViewById(R.id.tab_indicator);
        mButton = findViewById(R.id.btn_next);
        mButtonStarted = findViewById(R.id.btn_get_started);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        mButtonStarted.setVisibility(View.INVISIBLE);

        mScreenItems = new ArrayList<>();

//        mScreenItems.add(new ScreenItem("Deposit", "Term deposit is an expression used in banking and financial parlance referring to a sum of money blocked in an account and producing interest.", R.drawable.control_money));
//        mScreenItems.add(new ScreenItem("Withdrawal", "Withdrawal means the operation by which the account holder withdraws cash from his account, in an automatic teller machine (ATM) using his card or at a bank branch.", R.drawable.member));
//        mScreenItems.add(new ScreenItem("Transfer", "The Transfer is a banking technique by which, by debiting his account, the holder gives the order to the banking establishment, depositary of funds belonging to him or which he may have, for example as agent, to credit the account of a third party.", R.drawable.img3));
        mScreenItems.add(new ScreenItem("Deposit", "Term deposit is an expression used in banking and financial parlance referring.", R.drawable.control_money));
        mScreenItems.add(new ScreenItem("Withdrawal", "Withdrawal means the operation by which the account holder withdraws cash.", R.drawable.member));
        mScreenItems.add(new ScreenItem("Transfer", "The Transfer is a banking technique by which, by debiting his account.", R.drawable.img3));

        mIntroViewPagerAdapter = new IntroPagerAdapter(this, mScreenItems);

        mViewPager.setAdapter(mIntroViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        mButtonStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                savePrefsData();
                Intent mIntent = new Intent(getApplicationContext(), HomeCustomerActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected ( TabLayout.Tab tab ) {
                if(tab.getPosition() == mScreenItems.size() - 1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected ( TabLayout.Tab tab ) {

            }

            @Override
            public void onTabReselected ( TabLayout.Tab tab ) {

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                position = mViewPager.getCurrentItem();
                if(position < mScreenItems.size()){
                    position++;
                    mViewPager.setCurrentItem(position);
                }
                
                if(position == mScreenItems.size() - 1){
                    loadLastScreen();
                }
            }
        });
    }

    private boolean restorePrefData () {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.SHARED_PREF_OPEN_APK), MODE_PRIVATE);
        return preferences.getBoolean(getString(R.string.KEY_SHARED_PREF_OPEN_APK), false);
    }

    private void savePrefsData () {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.SHARED_PREF_OPEN_APK), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(getString(R.string.KEY_SHARED_PREF_OPEN_APK), true);
        editor.commit();
    }

    private void loadLastScreen () {
        mButtonStarted.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.INVISIBLE);
        mTabLayout.setVisibility(View.INVISIBLE);
        mButtonStarted.setAnimation(btnAnim);
    }
}
