package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.blitz_t.Controllers.AgencyMicrofinanceAdapter;
import com.example.blitz_t.Controllers.MicrofinanceAdapter;
import com.example.blitz_t.Models.Agency.ModelAgency;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Microfinance.ModelMicrofinance;

import java.util.ArrayList;

public class MicrofinanceAgenceActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Microfinance mMicrofinance;
    public EditText text_search_agency_microfinance;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microfinance_agency);

        mMicrofinance = getIntent().getParcelableExtra(MicrofinanceAdapter.KEY_MICROFINANCE);

        mRecyclerView = findViewById(R.id.recycler_view_agencies);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AgencyMicrofinanceAdapter adapter = new AgencyMicrofinanceAdapter(ModelAgency.searchAgenciesMicrofinance(mMicrofinance.get_id()) , this);
        mRecyclerView.setAdapter(adapter);

        text_search_agency_microfinance = findViewById(R.id.text_search_agency_microfinance);

        text_search_agency_microfinance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {

                AgencyMicrofinanceAdapter adapter = new AgencyMicrofinanceAdapter(ModelAgency.searchAgency(s.toString()), getApplicationContext());
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });
    }
}
