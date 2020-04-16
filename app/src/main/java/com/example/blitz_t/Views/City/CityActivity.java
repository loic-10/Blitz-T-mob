package com.example.blitz_t.Views.City;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.CityHelper;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Country.CountryActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CityActivity extends AppCompatActivity {

    private SmartMaterialSpinner country;
    private EditText name;
    private Button mButton;
    private View countries;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        countries = findViewById(R.id.text_country);

        country = findViewById(R.id.spinner);
        name = findViewById(R.id.editText);
        mButton = findViewById(R.id.button2);

        checkCountries();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                City city = new City(UUID.randomUUID().toString(), (Country) country.getSelectedItem(), name.getText().toString());
                CityHelper.setCity(city);
                Snackbar.make(v, name.getText().toString(), Snackbar.LENGTH_LONG).show();
            }
        });

        countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), CountryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkCountries(){
        final List<Country> countries = new ArrayList<>();
        CountryHelper.getCountries().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Country country = data.getValue(Country.class);
                    if(country != null){
                        countries.add(country);
                    }
                }
                country.setItem(countries);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
