package com.example.blitz_t.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blitz_t.Models.Microfinance;
import com.example.blitz_t.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MicrofinanceAdapter extends RecyclerView.Adapter<MicrofinanceAdapter.ViewHolder> {

    private List<Microfinance> mMicrofinances;
    private Context context;

    public MicrofinanceAdapter ( List<Microfinance> microfinances , Context context ) {
        mMicrofinances = microfinances;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_agency , parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder ( @NonNull ViewHolder holder , int position ) {
//        final Microfinance microfinance = mMicrofinances.get(position);
//
//        holder.text_country.setText(country.getName());
//        holder.tel_code_textview.setText(country.getCode());
//
//        holder.linearLayoutCountry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You clicked " + country.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public int getItemCount () {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View linearLayoutCountry;
        private ImageView flag_image;
        private TextView tel_code_textview;
        private TextView text_country;
        public ViewHolder(View v) {
            super(v);
//            tel_code_textview = v.findViewById(R.id.tel_code_textview);
//            text_country = v.findViewById(R.id.text_country);
//            linearLayoutCountry = v;
        }
    }
}
