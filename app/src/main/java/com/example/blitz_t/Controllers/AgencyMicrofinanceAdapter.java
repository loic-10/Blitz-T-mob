package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaek.android.RatingBar;
import com.example.blitz_t.MicrofinanceAgenceActivity;
import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.Agency.ModelAgency;
import com.example.blitz_t.Models.MemberMicrofinance.ModelMemberMicrofinance;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgencyMicrofinanceAdapter extends RecyclerView.Adapter<AgencyMicrofinanceAdapter.ViewHolder> {

    private List<Agency> mAgencies;
    private Context context;
    public static final String KEY_AGENCY = "KEY_AGENCY";

    public AgencyMicrofinanceAdapter ( List<Agency> agencies , Context context ) {
        mAgencies = agencies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_agency , parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder ( @NonNull ViewHolder holder , int position ) {
        final Agency agency = mAgencies.get(position);

        holder.text_ville_quartier.setText(new StringBuilder().append(agency.getVille()).append(", ").append(agency.getQuartier()));

        holder.text_heure_debut_fin.setText(new StringBuilder().append(agency.getHeure_ouverture()).append(" - ").append(agency.getHeure_fermeture()));

        holder.text_position_geographique.setText(agency.getDescription());

        holder.text_telephone.setText(agency.getNumero_tel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, MicrofinanceAgenceActivity.class);
//                intent.putExtra(KEY_AGENCY, agency);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount () {
        return mAgencies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        private TextView text_ville_quartier, text_heure_debut_fin, text_position_geographique, text_telephone;
        public ViewHolder(View v) {
            super(v);
            text_ville_quartier = v.findViewById(R.id.text_ville_quartier);
            text_heure_debut_fin = v.findViewById(R.id.text_heure_debut_fin);
            text_position_geographique = v.findViewById(R.id.text_position_geographique);
            text_telephone = v.findViewById(R.id.text_telephone);
            itemView = v;
        }
    }
}
