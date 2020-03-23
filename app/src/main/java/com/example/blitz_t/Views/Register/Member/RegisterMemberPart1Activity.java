package com.example.blitz_t.Views.Register.Member;

import androidx.appcompat.app.AppCompatActivity;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.DatePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.*;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Sex;
import com.example.blitz_t.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;


import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RegisterMemberPart1Activity extends AppCompatActivity {

    private List<String> provinceList;
    private View linearLayout;
    private TextFieldBoxes text_full_name_register_group, text_birth_date_register_group;
    private ExtendedEditText text_full_name_register, text_birth_date_register;
    private SmartMaterialSpinner spinner_sex_register_group;
    private Button btn_next_register_member_part1;
    private ContextWrapper mContextWrapper;
    private Member mMember;
    private DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member_part1);

        mContextWrapper = this;

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

    private void fillForm ( Member member ){
        text_full_name_register.setText(member.getFull_name());
        text_birth_date_register.setText(member.getBirth_date());
        spinner_sex_register_group.setSelection(member.getSex().ordinal());
    }

    private void initEvent () {
        btn_next_register_member_part1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart2Activity.class);
                mMember.setFull_name(text_full_name_register.getText().toString());
                mMember.setBirth_date(text_birth_date_register.getText().toString());
                mMember.setSex(Sex.valueOf(spinner_sex_register_group.getSelectedItem().toString()));
                Model.saveFormPreference(mMember, getString(R.string.SHARED_PREF_MEMBER_REGISTER), getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );

                startActivity(intent);
            }
        });

        text_birth_date_register_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
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
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet ( DatePicker view , int year , int month , int dayOfMonth ) {
                String date = dayOfMonth+"-"+(month+1)+"-"+year;
                text_birth_date_register.setText(date);
            }
        };
    }

    private void initView() {
        linearLayout = findViewById(R.id.linearLayout);
        text_full_name_register_group = findViewById(R.id.text_full_name_register_group);
        text_full_name_register = findViewById(R.id.text_full_name_register);
        text_birth_date_register_group = findViewById(R.id.text_birth_date_register_group);
        text_birth_date_register = findViewById(R.id.text_birth_date_register);
        spinner_sex_register_group = findViewById(R.id.spinner_sex_register);
        btn_next_register_member_part1 = findViewById(R.id.btn_next_register_member_part1);
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
    }
}
