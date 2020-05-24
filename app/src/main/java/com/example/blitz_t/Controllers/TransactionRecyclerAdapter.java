package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.ViewHolder> {

    private List<Transaction> mTransactions;
    private Context context;
    private Activity activity;
    private Member mMember = null;
    private Microfinance mMicrofinance = null;

    public TransactionRecyclerAdapter ( ArrayList<Transaction> Transactions , Context context , Activity activity ) {
        mTransactions = Transactions;
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
                .inflate(R.layout.item_transaction , parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n" , "ResourceAsColor"})
    @Override
    public void onBindViewHolder ( @NonNull final ViewHolder holder , int position ) {
        final Transaction transaction = mTransactions.get(position);

        holder.text_status_transaction.setTextColor(
                transaction.getTransaction_status().equals(Status.TransactionStatus.valided) ?
                        R.color.bootstrap_brand_success :
                        transaction.getTransaction_status().equals(Status.TransactionStatus.advised) ?
                                R.color.fbutton_color_green_sea :
                                transaction.getTransaction_status().equals(Status.TransactionStatus.refunded) ?
                                        R.color.colorAccent :
                                        transaction.getTransaction_status().equals(Status.TransactionStatus.pending_validity) ?
                                                R.color.bootstrap_brand_warning :
                                                transaction.getTransaction_status().equals(Status.TransactionStatus.transferred) ?
                                                        R.color.bootstrap_brand_primary :
                                                        R.color.bootstrap_brand_danger
        );

        holder.text_status_transaction.setHintTextColor(
                transaction.getTransaction_status().equals(Status.TransactionStatus.valided) ?
                        R.color.bootstrap_brand_success :
                        transaction.getTransaction_status().equals(Status.TransactionStatus.advised) ?
                                R.color.fbutton_color_green_sea :
                                transaction.getTransaction_status().equals(Status.TransactionStatus.refunded) ?
                                        R.color.colorAccent :
                                        transaction.getTransaction_status().equals(Status.TransactionStatus.pending_validity) ?
                                                R.color.bootstrap_brand_warning :
                                                transaction.getTransaction_status().equals(Status.TransactionStatus.transferred) ?
                                                        R.color.bootstrap_brand_primary :
                                                        R.color.bootstrap_brand_danger
        );

        holder.text_date_transaction.setText(transaction.getTransaction_date());

        holder.text_type_transaction.setText(transaction.getTransaction_type().toString());
        holder.text_type_transaction_letter.setText(transaction.getTransaction_type().toString());
        holder.text_code_transaction.setText(transaction.get_id());
        holder.text_status_transaction.setText(transaction.getTransaction_status().toString());
        holder.text_amount_transaction.setText(transaction.getAmount() + " " + mMicrofinance.getCurrency().getCurrency_symbol());
    }

    @Override
    public int getItemCount () {
        return mTransactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        private TextView text_date_transaction, text_type_transaction, text_type_transaction_letter, text_code_transaction, text_status_transaction, text_amount_transaction;
        ViewHolder ( View v ) {
            super(v);
            text_date_transaction = v.findViewById(R.id.text_date_transaction);
            text_type_transaction = v.findViewById(R.id.text_type_transaction);
            text_type_transaction_letter = v.findViewById(R.id.text_type_transaction_letter);
            text_code_transaction = v.findViewById(R.id.text_code_transaction);
            text_status_transaction = v.findViewById(R.id.text_status_transaction);
            text_amount_transaction = v.findViewById(R.id.text_amount_transaction);
            itemView = v;
        }
    }
}
