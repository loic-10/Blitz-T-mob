package com.example.blitz_t.Views.Customer.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.CustomerHelper;
import com.example.blitz_t.Controllers.DialogPersonal;
import com.example.blitz_t.Views.Member.Activity.HomeMemberActivity;
import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.util.List;
import java.util.UUID;

public class MembershipRequestCustomerActivity extends AppCompatActivity {

    private Member mMember;
    private Customer mCustomer;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private Microfinance mMicrofinance;
    private ImageView image_item_micro_finance;
    private TextView text_microfinance_name;
    private TextView text_microfinance_slogan;

    private AlertDialog.Builder mBuilder;
    private ProgressDialog mProgressDialog;

    private TextFieldBoxes text_profession_group;
    private ExtendedEditText text_profession;

    private View spinner_city_group;
    private SmartMaterialSpinner spinner_city;

    private View spinner_agency_group;
    private SmartMaterialSpinner spinner_agency;

    private TextFieldBoxes text_password_register_group;
    private ExtendedEditText text_password_register;

    private TextFieldBoxes text_confirm_password_register_group;
    private ExtendedEditText text_confirm_password_register;

    private Button button_save;

    private MenuItem buttonItem;
    private Activity mActivity;
    private MembershipRequestCustomerActivity mMembershipRequestCustomerActivity;

    private List<City> cities;
    private List<Agency> agencies;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_request);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mBuilder = new AlertDialog.Builder(this);
        mProgressDialog = new ProgressDialog(this);

        mActivity = this;

        mMembershipRequestCustomerActivity = this;

        initView();

        if(buttonItem != null){
            buttonItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick ( MenuItem item ) {
                    finish();
                    return true;
                }
            });
        }

        mCustomer = new Customer();

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

        initEvent();

        initSpinner();

        initInfoMicrofinance(mMicrofinance);

    }

    private void initSpinner () {
        Model.checkCitiesForAgencyMicrofinance(spinner_city, mMicrofinance);
    }


    private boolean isComplete(String profession, int selectCity, int selectAgency, String password, String confirm_password) {
        return !profession.isEmpty() &&
                selectCity > -1 &&
                selectAgency > -1 &&
                !password.isEmpty() &&
                !confirm_password.isEmpty() &&
                password.equals(confirm_password);
    }

    private void initInfoMicrofinance ( Microfinance microfinance ) {
        DesignApp.updateImage(getApplicationContext(), image_item_micro_finance, microfinance.getImage(), null);
        collapsing.setTitle(microfinance.getNom());
        text_microfinance_name.setText(microfinance.getNom());
        text_microfinance_slogan.setText(microfinance.getSlogan());
    }

    private void initEvent () {
        button_save.setOnClickListener(mListener);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                City city = (City) adapterView.getSelectedItem();
                Model.checkAgencyCity(spinner_agency, mMicrofinance, city);
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick ( View v ) {
            switch (v.getId()){
                case R.id.button_save:
                    mProgressDialog.create();
                    mProgressDialog.setMessage(getString(R.string.request_in_progress));
                    mProgressDialog.show();
                    startRegisterCustomer();
                    break;
            }
        }
    };

    private void initView () {
        text_microfinance_name = findViewById(R.id.text_microfinance_name);
        text_microfinance_slogan = findViewById(R.id.text_microfinance_slogan);

        text_profession_group = findViewById(R.id.text_profession_group);
        text_profession = findViewById(R.id.text_profession);

        spinner_city_group = findViewById(R.id.spinner_city_group);
        spinner_city = findViewById(R.id.spinner_city);

        spinner_agency_group = findViewById(R.id.spinner_agency_group);
        spinner_agency = findViewById(R.id.spinner_agency);

        text_password_register_group = findViewById(R.id.text_password_register_group);
        text_password_register = findViewById(R.id.text_password_register);

        text_confirm_password_register_group = findViewById(R.id.text_confirm_password_register_group);
        text_confirm_password_register = findViewById(R.id.text_confirm_password_register);

        button_save = findViewById(R.id.button_save);
        collapsing = findViewById(R.id.collapsing);
        image_item_micro_finance = findViewById(R.id.image_item_micro_finance);
        app_bar = findViewById(R.id.app_bar);
        buttonItem = app_bar.getMenu().findItem(R.id.menu_return);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startRegisterCustomer() {
        if(isComplete(text_profession.getText().toString(),
            spinner_city.getSelectedItemPosition(),
            spinner_agency.getSelectedItemPosition(),
            text_password_register.getText().toString(),
            text_confirm_password_register.getText().toString())){

            Intent intent = new Intent(getApplicationContext(), HomeMemberActivity.class);

            mCustomer.set_id(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            mCustomer.setAgency((Agency) spinner_agency.getSelectedItem());
            mCustomer.setCustomer_status(mMicrofinance.isAccepter_manuellement_demande_client() ?
                    Status.CustomerStatus.disabled :
                    Status.CustomerStatus.activated);
            mCustomer.setMember(mMember);
            mCustomer.setMicrofinance(mMicrofinance);
            mCustomer.setProfession(text_profession.getText().toString());
            mCustomer.setPassword(text_password_register.getText().toString());
            mCustomer.register_date = Model.currentDateString();
            CustomerHelper.setCustomer(mCustomer);
            mProgressDialog.dismiss();

            new DialogPersonal().showDialog(
                    mMembershipRequestCustomerActivity ,
                    getString(R.string.text_membership_request),
                    getString(mMicrofinance.isAccepter_manuellement_demande_client() ?
                            R.string.request_wait_confirmation :
                            R.string.request_success_customer),
                    Status.AlertStatus.success ,
                    intent ,
                    true ,
                    mActivity);
        }
        else{
            mProgressDialog.dismiss();
            new DialogPersonal().showDialog(
                    mMembershipRequestCustomerActivity ,
                    getString(R.string.text_membership_request),
                    getString(R.string.text_operation_failed),
                    Status.AlertStatus.error ,
                    null ,
                    false ,
                    mActivity);
        }
    }

}
