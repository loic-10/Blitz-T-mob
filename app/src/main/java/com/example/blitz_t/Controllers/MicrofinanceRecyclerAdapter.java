package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import com.chaek.android.RatingBar;
import com.example.blitz_t.MicrofinanceAgencyActivity;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Login.Customer.LoginCustomerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MicrofinanceRecyclerAdapter extends RecyclerView.Adapter<MicrofinanceRecyclerAdapter.ViewHolder> {

    private List<Microfinance> mMicrofinances;
    private Context context;
    private Activity activity;
    public static final String KEY_MICROFINANCE = "KEY_MICROFINANCE";
    public Member mMember = null;

    public MicrofinanceRecyclerAdapter ( ArrayList<Microfinance> microfinances , Context context  , Activity activity ) {
        mMicrofinances = microfinances;
        this.context = context;
        this.activity = activity;
        mMember = (Member) Model.contentPreference(
                new Member(),
                context.getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                context.getString(R.string.PREFERENCE_FILE_KEY),
                (ContextWrapper) context.getApplicationContext());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_microfinance , parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder ( @NonNull final ViewHolder holder , int position ) {
        final Microfinance microfinance = mMicrofinances.get(position);

        holder.text_name_item_micro_finance.setText(microfinance.getNom());

        holder.text_slogan_item_micro_finance.setText(microfinance.getSlogan());

        holder.text_item_costumer_number.setText(R.string.text_many_costumers);

//        holder.text_item_costumer_number.setText(ModelMemberMicrofinance.memberNumberMicrofinance(microfinance.get_id()) + " "
//                + holder.text_item_costumer_number.getText() );

        holder.text_item_agency_number.setText(R.string.text_many_agency);
        holder.text_item_agency_number.setText(null);

        Picasso.with(context)
                .load(microfinance.getImage())
                .into(holder.image_item_micro_finance);

//        holder.rating_item_micro_finance.setScore(ModelMemberMicrofinance.ratingMicrofinance(microfinance.get_id()));


        holder.button_item_more_agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MicrofinanceAgencyActivity.class);
                Model.saveFormPreference(microfinance , context.getString(R.string.SHARED_PREF_MICROFINANCE_SELECT) ,
                        context.getString(R.string.PREFERENCE_FILE_KEY) , activity);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                if(mMember != null) {
                    Intent intent = new Intent(context , LoginCustomerActivity.class);
                    Model.saveFormPreference(microfinance , context.getString(R.string.SHARED_PREF_MICROFINANCE_SELECT) ,
                            context.getString(R.string.PREFERENCE_FILE_KEY) , activity);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, context.getString(R.string.text_login_as_member), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount () {
        return mMicrofinances.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        private TextView text_name_item_micro_finance, text_slogan_item_micro_finance, text_item_agency_number, text_item_costumer_number;
        private ImageView image_item_micro_finance;
        private RatingBar rating_item_micro_finance;
        private Button button_item_more_agency;
        public ViewHolder(View v) {
            super(v);
            text_name_item_micro_finance = v.findViewById(R.id.text_name_item_micro_finance);
            text_slogan_item_micro_finance = v.findViewById(R.id.text_slogan_item_micro_finance);
            text_item_agency_number = v.findViewById(R.id.text_item_agency_number);
            text_item_costumer_number = v.findViewById(R.id.text_item_costumer_number);
            image_item_micro_finance = v.findViewById(R.id.image_item_micro_finance);
            rating_item_micro_finance = v.findViewById(R.id.rating_item_micro_finance);
            button_item_more_agency = v.findViewById(R.id.button_item_more_agency);
            itemView = v;
        }
    }
}
