package com.example.blitz_t.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AccountAdapter extends PagerAdapter {

    private List<Account> mAccounts;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public AccountAdapter ( List<Account> accounts , Context context ) {
        mAccounts = accounts;
        mContext = context;
    }

    @Override
    public int getCount () {
        return 5;
    }

    @Override
    public boolean isViewFromObject ( @NonNull View view , @NonNull Object object ) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem ( @NonNull ViewGroup container , int position ) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_account, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Toast.makeText(mContext, "Tu me cliques hein ??", Toast.LENGTH_LONG).show();
            }
        });

//        ImageView imageView = view.findViewById(R.id.image);
//        TextView title = view.findViewById(R.id.title), desc = view.findViewById(R.id.desc);
//
//        imageView.setImageResource(mAccounts.get(position).getImage());
//        title.setText(mAccounts.get(position).getTitle());
//        desc.setText(mAccounts.get(position).getDesc());
//
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem ( @NonNull ViewGroup container , int position , @NonNull Object object ) {
        container.removeView((View) object);
    }
}
