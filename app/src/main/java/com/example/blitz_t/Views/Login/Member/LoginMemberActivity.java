package com.example.blitz_t.Views.Login.Member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.blitz_t.Api.MemberHelper;
import com.example.blitz_t.Controllers.MicrofinanceAdapter;
import com.example.blitz_t.HomeMemberActivity;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.OthersMicrofinancesFragment;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Register.Member.RegisterMemberPart1Activity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LoginMemberActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_login_member);

        mContextWrapper = this;

        initView();

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
        MemberHelper.getMembers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Member member = data.getValue(Member.class);
                    if(member != null && member.getCni_number().equals(text_cni_login.getText().toString()) &&
                            member.getPassword().equals(text_password_login.getText().toString())){
                        Intent intent = new Intent(getApplicationContext(), HomeMemberActivity.class);
                        Model.saveFormPreference(member, getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                                getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );
                        startActivity(intent);
                        break;
                    }
                    Snackbar.make(text_password_login, R.string.text_no_account_found, Snackbar.LENGTH_LONG);
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(text_password_login, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    private void initView () {
        constraintLayout = findViewById(R.id.constraintLayout);
        text_cni_login_group = findViewById(R.id.text_cni_login_group);
        text_password_login_group = findViewById(R.id.text_password_login_group);
        text_cni_login = findViewById(R.id.text_cni_login);
        text_password_login = findViewById(R.id.text_password_login);
        btn_login_member = findViewById(R.id.btn_login_member);
        text_not_account = findViewById(R.id.text_not_account);
        text_forgot_password = findViewById(R.id.text_forgot_password);
    }
}
