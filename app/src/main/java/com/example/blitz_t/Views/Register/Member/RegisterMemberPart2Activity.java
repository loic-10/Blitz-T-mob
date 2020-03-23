package com.example.blitz_t.Views.Register.Member;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.CityHelper;
import com.example.blitz_t.Api.CountryHelper;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Sex;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.libRG.CustomTextView;

import java.util.ArrayList;
import java.util.List;

public class RegisterMemberPart2Activity extends AppCompatActivity {

    private View linearLayout;
    private TextFieldBoxes text_cni_number_register_group;
    private ExtendedEditText text_cni_number_register;
    private CustomTextView text_link_cni_register_group;
    private ImageView image_cni_register;
    private SmartMaterialSpinner spinner_country_register, spinner_city_register;
    private TextFieldBoxes text_address_register_group;
    private ExtendedEditText text_address_register;
    private Button btn_next_register_member_part2, btn_prev_register_member_part2;
    private Member mMember;
    private Uri selectedPhotoUri;
    private Activity mActivity;
    private ContextWrapper mContextWrapper;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member_part2);

        mContextWrapper = this;

        mActivity = this;

        initView();

        initSpinner();

        initEvent();

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
    }

    private void initSpinner () {
        checkCountries();
        if(spinner_country_register.getSelectedItemPosition() > -1){
            Country country = (Country) spinner_country_register.getSelectedItem();
            checkCitiesForCountries(country.getName());
        }
    }


    private void checkCountries(){
//        final List<Country> countries = (List<Country>) CountryHelper.getAllCountries();

        spinner_country_register.setItem((List) CountryHelper.getCountries());
    }

    private void checkCitiesForCountries( final String country_name) {
        final List<City> cities = new ArrayList<>();

        CityHelper.getReferenceCity("").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if ( child != null ) {
                        City city = child.getValue(City.class);
                        if ( city.getCountry().getName().equals(country_name) ) {
                            cities.add(city);
                        }
                    }
                }
                spinner_city_register.setItem(cities);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });
    }

    private void fillForm ( Member member ) {
        text_cni_number_register.setText(member.getCni_number());
        if(member.getCni_copy() != null) {
            DesignApp.updateImage(getApplicationContext(), image_cni_register, null, (Uri) member.getUri_picture());
        }

        List<Country> countries = (List<Country>) spinner_country_register.getItem();
        spinner_country_register.setSelection(countries.indexOf(member.getCountry()));

//        checkCitiesForCountries(member.getCountry().getName());

        List<City> cities = (List<City>) spinner_city_register.getItem();
        spinner_city_register.setSelection(cities.indexOf(member.getCity()));

        text_address_register.setText(member.getAddress());

    }

    private void initEvent () {
        btn_next_register_member_part2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart3Activity.class);
                mMember.setCni_number(text_cni_number_register.getText().toString());
                mMember.setUri_picture(selectedPhotoUri);
                mMember.setCountry((Country) spinner_country_register.getSelectedItem());
                mMember.setCity((City) spinner_city_register.getSelectedItem());
                mMember.setAddress(text_address_register.getText().toString());
                Model.saveFormPreference(mMember, getString(R.string.SHARED_PREF_MEMBER_REGISTER), getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );
                startActivity(intent);
            }
        });

        btn_prev_register_member_part2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart1Activity.class);
                startActivity(intent);
            }
        });

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

        text_link_cni_register_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity);
            }
        });

        image_cni_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity);
            }
        });
    }

    private void initView () {
        linearLayout = findViewById(R.id.linearLayout);
        text_cni_number_register_group = findViewById(R.id.text_cni_number_register_group);
        text_cni_number_register = findViewById(R.id.text_cni_number_register);
        text_link_cni_register_group = findViewById(R.id.text_link_cni_register_group);
        image_cni_register = findViewById(R.id.image_cni_register);
        spinner_country_register = findViewById(R.id.spinner_country_register);
        spinner_city_register = findViewById(R.id.spinner_city_register);
        text_address_register_group = findViewById(R.id.text_address_register_group);
        text_address_register = findViewById(R.id.text_address_register);
        btn_next_register_member_part2 = findViewById(R.id.btn_next_register_member_part2);
        btn_prev_register_member_part2 = findViewById(R.id.btn_prev_register_member_part2);
    }

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode == 0 && resultCode == RESULT_OK && data != null){
            this.selectedPhotoUri = data.getData();
            DesignApp.updateImage(getApplicationContext(), image_cni_register, null, this.selectedPhotoUri);
        }

    }
}
