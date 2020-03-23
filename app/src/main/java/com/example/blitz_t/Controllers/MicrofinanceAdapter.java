package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import com.chaek.android.RatingBar;
import com.example.blitz_t.MicrofinanceAgenceActivity;
import com.example.blitz_t.Models.Agency.ModelAgency;
import com.example.blitz_t.Models.MemberMicrofinance.ModelMemberMicrofinance;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MicrofinanceAdapter extends RecyclerView.Adapter<MicrofinanceAdapter.ViewHolder> {

    private List<Microfinance> mMicrofinances;
    private Context context;
    public static final String KEY_MICROFINANCE = "KEY_MICROFINANCE";

    public MicrofinanceAdapter ( ArrayList<Microfinance> microfinances , Context context ) {
        mMicrofinances = microfinances;
        this.context = context;
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
    public void onBindViewHolder ( @NonNull ViewHolder holder , int position ) {
        final Microfinance microfinance = mMicrofinances.get(position);

        holder.text_name_item_micro_finance.setText(microfinance.getName());

        holder.text_slogan_item_micro_finance.setText(microfinance.getSlogan());

        holder.text_item_costumer_number.setText(R.string.text_many_costumers);

        holder.text_item_costumer_number.setText(ModelMemberMicrofinance.memberNumberMicrofinance(microfinance.get_id()) + " "
                + holder.text_item_costumer_number.getText() );

        holder.text_item_agency_number.setText(R.string.text_many_agency);
        holder.text_item_agency_number.setText(ModelAgency.searchAgenciesMicrofinance(microfinance.get_id()).size() + " "
                + holder.text_item_agency_number.getText() );

        Picasso.with(context)
                .load(microfinance.getLogo())
                .into(holder.image_item_micro_finance);

        holder.rating_item_micro_finance.setScore(ModelMemberMicrofinance.ratingMicrofinance(microfinance.get_id()));

        holder.button_item_more_agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MicrofinanceAgenceActivity.class);
                intent.putExtra(KEY_MICROFINANCE, microfinance);
                context.startActivity(intent);
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
