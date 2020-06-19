package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blitz_t.Views.Account.Activity.AccountCustomerActivity;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.ViewHolder> {

    private List<Account> mAccounts;
    private Context context;
    private Activity activity;
    private Member mMember = null;
    private Microfinance mMicrofinance = null;

    public AccountRecyclerAdapter ( ArrayList<Account> accounts , Context context , Activity activity ) {
        mAccounts = accounts;
        this.context = context;
        this.activity = activity;

        mMember = (Member) Model.contentPreference(
                new Member(),
                context.getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                context.getString(R.string.PREFERENCE_FILE_KEY),
                (ContextWrapper) context.getApplicationContext());

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                context.getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                context.getString(R.string.PREFERENCE_FILE_KEY),
                (ContextWrapper) context.getApplicationContext());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account , parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n" , "ResourceAsColor"})
    @Override
    public void onBindViewHolder ( @NonNull final ViewHolder holder , int position ) {
        final Account account = mAccounts.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(context , AccountCustomerActivity.class);
                Model.saveFormPreference(account , context.getString(R.string.SHARED_PREF_ACCOUNT_SELECT) ,
                        context.getString(R.string.PREFERENCE_FILE_KEY) , activity);
                context.startActivity(intent);
            }
        });

        holder.image_view_status_account.setImageResource(account.getAccount_status().equals(Status.AccountStatus.activated) ?
                R.drawable.ic_lock_open_black_24dp :
                R.drawable.ic_lock_outline_black_24dp);

        holder.text_balance_account.setText(account.getBalance() + " " + mMicrofinance.getCurrency().getCurrency_symbol());
        holder.text_code_account.setText(account.get_id());
        holder.text_account_type.setText(account.getAccount_type().toString());

        DesignApp.updateImage(context , holder.img_micro_finance, mMicrofinance.getImage() , null);
    }

    @Override
    public int getItemCount () {
        return mAccounts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView image_view_status_account, img_micro_finance;
        TextView text_balance_account, text_code_account, text_account_type;
        ViewHolder ( View v ) {
            super(v);
            image_view_status_account  = v.findViewById(R.id.image_view_status_account);
            img_micro_finance  = v.findViewById(R.id.img_micro_finance);
            text_balance_account = v.findViewById(R.id.text_balance_account);
            text_code_account = v.findViewById(R.id.text_code_account);
            text_account_type = v.findViewById(R.id.text_account_type);
            itemView = v;
        }
    }
}
