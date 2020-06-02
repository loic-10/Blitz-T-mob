package com.example.blitz_t.Views.Login.Customer;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blitz_t.Controllers.DialogPersonal;
import com.example.blitz_t.HomeCustomerActivity;
import com.example.blitz_t.HomeMemberActivity;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.example.blitz_t.Views.Register.Customer.MembershipRequestActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.example.blitz_t.Api.CustomerHelper.getCustomers;

public class LoginCustomerActivity extends AppCompatActivity {

    private Member mMember;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private Microfinance mMicrofinance;
    private ImageView image_item_micro_finance;
    private TextView text_microfinance_name;
    private TextView text_microfinance_slogan;
    private ExtendedEditText text_password_login;
    private TextFieldBoxes text_password_login_group;
    private Button btn_login_customer;
    private MenuItem buttonItem;
    private Activity mActivity;
    private LoginCustomerActivity mLoginCustomerActivity;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        mActivity = this;

        mLoginCustomerActivity = this;

        initView();
        
        initEvent();

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
                this);

        if(mMember == null){
            Intent intent = new Intent(getApplicationContext(), HomeMemberActivity.class);
            startActivity(intent);
            finish();
        }


        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        initInfoMicrofinance(mMicrofinance);

        isCustomerMicrofinance();

    }

    private void initInfoMicrofinance ( Microfinance microfinance ) {
        DesignApp.updateImage(getApplicationContext(), image_item_micro_finance, microfinance.getImage(), null);
        collapsing.setTitle(microfinance.getNom());
        text_microfinance_name.setText(microfinance.getNom());
        text_microfinance_slogan.setText(microfinance.getSlogan());
    }

    private void initEvent () {
        btn_login_customer.setOnClickListener(mListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            switch (v.getId()){
                case R.id.btn_login_customer:
                    startLoginCustomer();
                    break;
            }
        }
    };

    private void initView () {
        text_microfinance_name = findViewById(R.id.text_microfinance_name);
        text_microfinance_slogan = findViewById(R.id.text_microfinance_slogan);
        btn_login_customer = findViewById(R.id.btn_login_customer);
        text_password_login = findViewById(R.id.text_password_login);
        text_password_login_group = findViewById(R.id.text_password_login_group);
        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        image_item_micro_finance = findViewById(R.id.image_item_micro_finance);
        buttonItem = app_bar.getMenu().findItem(R.id.menu_return);
    }

    private void startLoginCustomer() {
        getCustomers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                final ArrayList<Customer> customers = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Customer customer = data.getValue(Customer.class);
                    if(customer != null &&
                        customer.getMember().get_id().equals(mMember.get_id()) &&
                        customer.getMicrofinance().get_id().equals(mMicrofinance.get_id()) &&
                        customer.getPassword().equals(text_password_login.getText().toString())){
                            Intent intent = new Intent(getApplicationContext(), HomeCustomerActivity.class);
                            Model.saveFormPreference(customer, getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                                    getString(R.string.PREFERENCE_FILE_KEY), (ContextWrapper) getApplicationContext());
                        customers.add(customer);
                        new DialogPersonal().showDialog(
                                mLoginCustomerActivity,
                            getString(R.string.text_login_customer),
                            getString(R.string.text_welcome) + " " + customer.getMicrofinance().getNom(),
                            Status.AlertStatus.success ,
                            intent ,
                            true ,
                            mActivity);
                        break;
                    }
                }

                if(customers.size() <= 0){
                    new DialogPersonal().showDialog(
                            mLoginCustomerActivity,
                            getString(R.string.text_login_customer),
                            getString(R.string.text_no_account_found),
                            Status.AlertStatus.error ,
                            null ,
                            true ,
                            mActivity);
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                new DialogPersonal().showDialog(
                        mLoginCustomerActivity,
                        getString(R.string.text_login_customer),
                        getString(R.string.text_operation_failed),
                        Status.AlertStatus.error ,
                        null,
                        true ,
                        mActivity);
            }
        });
    }

    private void isCustomerMicrofinance () {
        final ArrayList<Microfinance> microfinances = new ArrayList<>();
        getCustomers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Customer customer = data.getValue(Customer.class);
                    if(customer != null &&
                        customer.getMember().get_id().equals(mMember.get_id()) &&
                        customer.getMicrofinance().get_id().equals(mMicrofinance.get_id())){
                        microfinances.add(customer.getMicrofinance());
                        break;
                    }
                }

                if(microfinances.size() <= 0){
                    Intent intent = new Intent(getApplicationContext(), MembershipRequestActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
            }
        });
    }

}
