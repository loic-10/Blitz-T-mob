package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.blitz_t.Controllers.MicrofinanceAdapter;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Microfinance.ModelMicrofinance;

import java.util.ArrayList;

public class ChoiceMicrofinanceActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText text_search_microfinance;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_microfinance);

        mRecyclerView = findViewById(R.id.recycler_view_microfinance);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MicrofinanceAdapter adapter = new MicrofinanceAdapter(ModelMicrofinance.allMicrofinances() , this);
        mRecyclerView.setAdapter(adapter);

        text_search_microfinance = findViewById(R.id.text_search_microfinance);

        text_search_microfinance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {
                MicrofinanceAdapter adapter = new MicrofinanceAdapter(ModelMicrofinance.searchMicrofinance(s.toString()) , getApplicationContext());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });
    }
}
