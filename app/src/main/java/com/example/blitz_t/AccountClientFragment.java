package com.example.blitz_t;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Controllers.AccountAdapter;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class AccountClientFragment extends Fragment {

    ViewPager mViewPager;
    AccountAdapter mAdapter;
    Integer[] color = null;
    ArrayList mAccounts;
    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {

        View view = inflater.inflate(R.layout.account_view , container , false);

        mAccounts = new ArrayList<>();

        mAdapter = new AccountAdapter(mAccounts, getContext());
        mViewPager = view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setPadding(130, 0, 130, 0);

        Integer[] color_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        color = color_temp;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled ( int position , float positionOffset , int positionOffsetPixels ) {
                if(position < (mAdapter.getCount() - 1) && position < (color.length - 1)){
                    mViewPager.setBackgroundColor(
                            (Integer) mArgbEvaluator.evaluate(
                                    positionOffset,
                                    color[position],
                                    color[position + 1]
                            )
                    );
                }
                else {
                    mViewPager.setBackgroundColor(color[color.length - 1]);
                }
            }

            @Override
            public void onPageSelected ( int position ) {

            }

            @Override
            public void onPageScrollStateChanged ( int state ) {

            }
        });

        return view;
    }

}
