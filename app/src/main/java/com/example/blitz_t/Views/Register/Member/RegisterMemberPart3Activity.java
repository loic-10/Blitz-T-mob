package com.example.blitz_t.Views.Register.Member;

import androidx.appcompat.app.AppCompatActivity;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.blitz_t.R;
import com.example.blitz_t.Views.Login.Member.LoginMemberActivity;

public class RegisterMemberPart3Activity extends AppCompatActivity {

    private View linearLayout;
    private View text_link_profil_register_group;
    private ImageView image_profil_register;
    private Spinner spinner_code_phone_register;
    private TextFieldBoxes text_phone_number_register_group, text_password_register_group;
    private View text_phone_number_register, text_password_register;
    private Button btn_sign_in_register_member_part3, btn_prev_register_member_part3;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member_part3);

        initView();

        initEvent();
    }

    private void initEvent () {
        btn_sign_in_register_member_part3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), LoginMemberActivity.class);
                startActivity(intent);
            }
        });

        btn_prev_register_member_part3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initView () {
        linearLayout = findViewById(R.id.linearLayout);
        text_link_profil_register_group = findViewById(R.id.text_link_profil_register_group);
        image_profil_register = findViewById(R.id.image_profil_register);
        spinner_code_phone_register = findViewById(R.id.spinner_code_phone_register);
        text_phone_number_register_group = findViewById(R.id.text_phone_number_register_group);
        text_phone_number_register = findViewById(R.id.text_phone_number_register);
        text_password_register_group = findViewById(R.id.text_password_register_group);
        text_password_register = findViewById(R.id.text_password_register);
        btn_sign_in_register_member_part3 = findViewById(R.id.btn_sign_in_register_member_part3);
        btn_prev_register_member_part3 = findViewById(R.id.btn_prev_register_member_part3);
    }
}
