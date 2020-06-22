package com.example.blitz_t.Views.Member.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.blitz_t.Views.Member.Fragment.HomeMemberFragment;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Member.Fragment.MyAccountMemberFragment;
import com.example.blitz_t.Views.Microfinance.Fragment.MyMicrofinancesFragment;
import com.example.blitz_t.Views.Microfinance.Fragment.OthersMicrofinancesFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeMemberActivity extends AppCompatActivity{

    private ChipNavigationBar bottom_nav;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_member);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnItemSelectedListener(navListener);
        bottom_nav.setItemSelected(R.id.my_micro_finance, true);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container_member,
                new HomeMemberFragment()).commit();

    }

    private ChipNavigationBar.OnItemSelectedListener navListener =
        new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected ( int i ) {
                Fragment selectedFragment = null;

                switch (i){
                    case R.id.my_micro_finance:
                        selectedFragment = new MyMicrofinancesFragment();
                        break;
                    case R.id.other_micro_finance:
                        selectedFragment = new OthersMicrofinancesFragment();
                        break;
                    case R.id.my_account:
                        selectedFragment = new MyAccountMemberFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container_member,
                        selectedFragment).commit();

            }
        };

}
