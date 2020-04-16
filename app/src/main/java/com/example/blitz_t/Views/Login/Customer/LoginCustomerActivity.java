package com.example.blitz_t.Views.Login.Customer;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.blitz_t.Api.MemberHelper;
import com.example.blitz_t.HomeMemberActivity;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.OthersMicrofinancesFragment;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Register.Member.RegisterMemberPart1Activity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class LoginCustomerActivity extends AppCompatActivity {

    private View constraintLayout;
    private TextFieldBoxes text_cni_login_group, text_password_login_group;
    private ExtendedEditText text_cni_login, text_password_login;
    private Button btn_login_member;
    private TextView text_not_account, text_forgot_password;
    private Member mMember;
    private ContextWrapper mContextWrapper;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        mContextWrapper = this;

        initEvent();

        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_log_in));

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

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        if(mMember != null){
            Intent intent = new Intent(getApplicationContext(), HomeMemberActivity.class);
            startActivity(intent);
        }
    }

    private void initEvent () {
        btn_login_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                startLoginMember();
            }
        });

        text_not_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart1Activity.class);
                startActivity(intent);
            }
        });

        text_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart1Activity.class);
                startActivity(intent);
            }
        });
    }

    private void startLoginMember () {

    }
}
