package com.example.blitz_t.Views.Register.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import eightbitlab.com.blurview.BlurView;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import android.app.DatePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.CityHelper;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Sex.Sex;
import com.example.blitz_t.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RegisterMemberPart1Activity extends AppCompatActivity {

    private TextFieldBoxes text_full_name_register_group, text_birth_date_register_group;
    private ExtendedEditText text_full_name_register, text_birth_date_register;
    private SmartMaterialSpinner spinner_sex_register_group;
    private Button btn_next_register_member_part1;
    private ContextWrapper mContextWrapper;
    private Member mMember;
    private SmartMaterialSpinner spinner_country_register, spinner_city_register;
    private TextFieldBoxes text_address_register_group;
    private ExtendedEditText text_address_register;
    private List<City> cities;
    private List<Country> countries;
    private BlurView view_background;
    private DatePickerDialog.OnDateSetListener setListener;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member_part1);
        mContextWrapper = this;
        cities = new ArrayList<>();
        countries = new ArrayList<>();

        initView();

        initSpinner();

        initEvent();

//        DesignApp.blurEffect(view_background , getApplicationContext(), getWindow(), 10);

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_REGISTER),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        if(mMember != null){
            fillForm(mMember);
        }
        else {
            mMember = new Member();
        }

        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_personal_information));

        MenuItem buttonItem = app_bar.getMenu().findItem(R.id.menu_return);
        if(buttonItem != null){
            buttonItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick ( MenuItem item ) {
                    finish();
                    return true;
                }
            });
        }
    }

    private void fillForm ( Member member ){
        text_full_name_register.setText(member.getFull_name());
        text_birth_date_register.setText(member.getBirth_date());
        spinner_sex_register_group.setSelection(member.getSex().ordinal());
        text_address_register.setText(member.getAddress());
    }

    private void initEvent () {
        btn_next_register_member_part1.setOnClickListener(listener);

        text_birth_date_register_group.setOnClickListener(listener);

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet ( DatePicker view , int year , int month , int dayOfMonth ) {
                String date = dayOfMonth+"-"+(month+1)+"-"+year;
                text_birth_date_register.setText(date);
            }
        };

        spinner_country_register.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                Country country = (Country) adapterView.getSelectedItem();
                checkCitiesForCountries(country.getName());
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });
    }

    private boolean isComplete(String fullName, String birthDate,int selectSex, int selectCountry, int selectCity, String address) {
        return !fullName.isEmpty() &&
                !birthDate.isEmpty() &&
                selectSex > -1 &&
                selectCountry > -1 &&
                selectCity > -1 &&
                !address.isEmpty();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                switch (v.getId()){
                    case R.id.btn_next_register_member_part1 :
                        if(isComplete(text_full_name_register.getText().toString(),
                                text_birth_date_register.getText().toString(),
                                spinner_sex_register_group.getSelectedItemPosition(),
                                spinner_country_register.getSelectedItemPosition(),
                                spinner_city_register.getSelectedItemPosition(),
                                text_address_register.getText().toString())) {

                            Intent intent = new Intent(getApplicationContext() , RegisterMemberPart2Activity.class);
                            mMember.setFull_name(text_full_name_register.getText().toString());
                            mMember.setBirth_date(text_birth_date_register.getText().toString());
                            mMember.setSex(Sex.valueOf(spinner_sex_register_group.getSelectedItem().toString()));
                            mMember.setCountry((Country) spinner_country_register.getSelectedItem());
                            mMember.setCity((City) spinner_city_register.getSelectedItem());
                            mMember.setAddress(text_address_register.getText().toString());
                            Model.saveFormPreference(mMember , getString(R.string.SHARED_PREF_MEMBER_REGISTER) , getString(R.string.PREFERENCE_FILE_KEY) , mContextWrapper);
                            startActivity(intent);
                        }
                        else{
                            Snackbar.make(v, getString(R.string.text_error_complete_field), Snackbar.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.text_birth_date_register_group :
                        final Calendar calendar = Calendar.getInstance();
                        final int year = calendar.get(Calendar.YEAR);
                        final int mouth = calendar.get(Calendar.MONTH);
                        final int day = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                RegisterMemberPart1Activity.this,
                                android.R.style.Theme_Material_Dialog,
                                setListener,
                                year,
                                mouth,
                                day
                        );
                        datePickerDialog.show();
                        break;
                }
            }
        };

    private void initView() {
        text_full_name_register_group = findViewById(R.id.text_full_name_register_group);
        text_full_name_register = findViewById(R.id.text_full_name_register);
        text_birth_date_register_group = findViewById(R.id.text_birth_date_register_group);
        text_birth_date_register = findViewById(R.id.text_birth_date_register);
        spinner_sex_register_group = findViewById(R.id.spinner_sex_register);
        btn_next_register_member_part1 = findViewById(R.id.btn_next_register_member_part1);
        spinner_country_register = findViewById(R.id.spinner_country_register);
        spinner_city_register = findViewById(R.id.spinner_city_register);
        text_address_register_group = findViewById(R.id.text_address_register_group);
        text_address_register = findViewById(R.id.text_address_register);
        view_background = findViewById(R.id.view_background);
    }

    private void checkCountries(){
        countries = new ArrayList<>();
        final Country[] countrySearch = {new Country()};

        CountryHelper.getCountries().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Country country = data.getValue(Country.class);
                    if(country != null){
                        countries.add(country);
                    }
                    if(mMember.getCountry() != null){
                        if (country.getName().equals(mMember.getCountry().getName())) {
                            countrySearch[0] = country;
                        }
                    }
                }
                spinner_country_register.setItem(countries);
                if(countries.size() > 0) {
                    spinner_country_register.setSelection(countries.indexOf(countrySearch[0]));
                    checkCitiesForCountries(countrySearch[0].getName());
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void checkCitiesForCountries( final String country_name) {
        cities = new ArrayList<>();
        final City[] citySearch = {new City()};

        CityHelper.getCities().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    City city = data.getValue(City.class);
                    if(city != null){
                        if ( city.getCountry().getName().equals(country_name) ) {
                            cities.add(city);
                            if(mMember.getCity() != null) {
                                if ( city.getName().equals(mMember.getCity().getName()) ) {
                                    citySearch[0] = city;
                                }
                            }
                        }
                    }
                }
                spinner_city_register.setItem(cities);
                spinner_city_register.setSelection(cities.indexOf(citySearch[0]));
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void initSpinner() {
        Sex[] array = Sex.values();
        List<Sex> sexes = Arrays.asList(array);
        spinner_sex_register_group.setItem(sexes);

        spinner_sex_register_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });

        checkCountries();
    }
}
