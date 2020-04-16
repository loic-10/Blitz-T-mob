package com.example.blitz_t.Views.Country;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.City.CityActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.UUID;

public class CountryActivity extends AppCompatActivity {

    private EditText code_phone, name;
    private Button mButton;
    private View city;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        name = findViewById(R.id.editText);
        code_phone = findViewById(R.id.editText1);
        mButton = findViewById(R.id.button2);
        city = findViewById(R.id.text_city);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                startActivity(intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Country country = new Country(UUID.randomUUID().toString(), name.getText().toString(), code_phone.getText().toString());
                CountryHelper.setCountry( country );
                Snackbar.make(v, name.getText().toString(), Snackbar.LENGTH_LONG).show();
            }

        });
    }
}
