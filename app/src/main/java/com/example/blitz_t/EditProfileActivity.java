package com.example.blitz_t;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.DirectoryUpload;
import com.example.blitz_t.Api.MemberHelper;
import com.example.blitz_t.Controllers.DialogPersonal;
import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Sex.Sex;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Views.DesignApp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private View linearLayout;
    private TextFieldBoxes text_full_name_register_group, text_birth_date_register_group;
    private ExtendedEditText text_full_name_register, text_birth_date_register;
    private SmartMaterialSpinner spinner_sex_register_group;
    private TextFieldBoxes text_cni_number_register_group;
    private ExtendedEditText text_cni_number_register;
    private SmartMaterialSpinner spinner_country_register, spinner_city_register;
    private TextFieldBoxes text_address_register_group;
    private ExtendedEditText text_address_register;
    private ImageButton btn_edit_cni;
    private ImageView image_cni;
    private ImageButton btn_edit_profile;
    private ImageView profile_image;
    private SmartMaterialSpinner spinner_code_phone_register;
    private TextFieldBoxes text_phone_number_register_group, text_password_register_group, text_confirm_password_register_group;
    private ExtendedEditText text_phone_number_register, text_password_register, text_confirm_password_register;
    private Button button_save;
    private Uri selectedImageCNIUri = null;
    private Uri selectedImageProfilUri = null;
    private Member mMember;
    private ContextWrapper mContextWrapper;
    private Activity mActivity;
    private List<String > listNumberCode;
    private List<City> cities;
    private List<Country> countries;
    private DatePickerDialog.OnDateSetListener setListener;
    private EditProfileActivity mEditProfileActivity;

    static MemberHelper sMemberHelper = new MemberHelper(new Member());

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        listNumberCode = new ArrayList<>();

        mEditProfileActivity = this;

        if(this.selectedImageCNIUri != null) {
            DesignApp.updateImage(getApplicationContext() , image_cni , null , this.selectedImageCNIUri);
        }

        if(this.selectedImageProfilUri != null) {
            DesignApp.updateImage(getApplicationContext() , profile_image , null , this.selectedImageProfilUri);
        }

        mContextWrapper = this;

        mActivity = this;

        cities = new ArrayList<>();

        countries = new ArrayList<>();

        initView();

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                mContextWrapper);

        if(mMember != null){
            fillForm(mMember);
        }
        else {
            mMember = new Member();
        }

        initSpinner();

        initEvent();

        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_edit_account));

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
        text_cni_number_register.setText(member.getCni_number());
        text_address_register.setText(member.getAddress());
        text_phone_number_register.setText(member.getPhone_number() != null ? member.getPhone_number().split(" ")[1] : "");


        DesignApp.updateImage(getApplicationContext() , image_cni , member.getCni_copy() , null);
        DesignApp.updateImage(getApplicationContext() , profile_image , member.getProfile_picture() , null);

        text_password_register.setText(member.getPassword());
        text_confirm_password_register.setText(member.getPassword());

    }

    private void initView() {
        text_full_name_register_group = findViewById(R.id.text_full_name_register_group);
        text_full_name_register = findViewById(R.id.text_full_name_register);
        text_birth_date_register_group = findViewById(R.id.text_birth_date_register_group);
        text_birth_date_register = findViewById(R.id.text_birth_date_register);
        spinner_sex_register_group = findViewById(R.id.spinner_sex_register);
        text_cni_number_register_group = findViewById(R.id.text_cni_number_register_group);
        text_cni_number_register = findViewById(R.id.text_cni_number_register);
        spinner_country_register = findViewById(R.id.spinner_country_register);
        spinner_city_register = findViewById(R.id.spinner_city_register);
        text_address_register_group = findViewById(R.id.text_address_register_group);
        text_address_register = findViewById(R.id.text_address_register);
        btn_edit_cni = findViewById(R.id.btn_edit_cni);
        image_cni = findViewById(R.id.image_cni);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        profile_image = findViewById(R.id.profile_image);
        spinner_code_phone_register = findViewById(R.id.spinner_code_phone_register);
        text_phone_number_register_group = findViewById(R.id.text_phone_number_register_group);
        text_phone_number_register = findViewById(R.id.text_phone_number_register);
        text_password_register_group = findViewById(R.id.text_password_register_group);
        text_confirm_password_register_group = findViewById(R.id.text_confirm_password_register_group);
        text_confirm_password_register = findViewById(R.id.text_confirm_password_register);
        text_password_register = findViewById(R.id.text_password_register);
        button_save = findViewById(R.id.button_save);
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

        Model.checkListNumberCode(spinner_code_phone_register, mMember);

        Model.checkCountries(spinner_country_register, spinner_city_register, mMember);

    }

    private View.OnClickListener listener =
        new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                switch (v.getId()){
                    case R.id.text_birth_date_register_group :
                        final Calendar calendar = Calendar.getInstance();
                        final int year = calendar.get(Calendar.YEAR);
                        final int mouth = calendar.get(Calendar.MONTH);
                        final int day = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                EditProfileActivity.this,
                                android.R.style.Theme_Material_Dialog,
                                setListener,
                                year,
                                mouth,
                                day
                        );
                        datePickerDialog.show();
                        break;

                    case R.id.btn_edit_cni:
                        DesignApp.chooseImage(mActivity, 0);
                        break;

                    case R.id.btn_edit_profile:
                        DesignApp.chooseImage(mActivity, 1);
                        break;

                    case R.id.button_save:
                        saveMember();
                        break;
                }
            }
        };

    private void initEvent () {

        text_birth_date_register_group.setOnClickListener(listener);

        button_save.setOnClickListener(listener);

        btn_edit_cni.setOnClickListener(listener);

        btn_edit_profile.setOnClickListener(listener);

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet ( DatePicker view , int year , int month , int dayOfMonth ) {
                text_birth_date_register.setText(Model.getBirthDateSelect(dayOfMonth, month + 1, year));
            }
        };

        spinner_country_register.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                Country country = (Country) adapterView.getSelectedItem();
                Model.checkCitiesForCountries(spinner_city_register, country, mMember);
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });

    }

    private void uploadImageCNI(){
        sMemberHelper.uploadImage(selectedImageCNIUri, String.valueOf(DirectoryUpload.CNI))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
                        taskSnapshot.getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess ( Uri uri ) {
                                        sMemberHelper.deleteImage(mMember.getCni_copy());
                                        mMember.setCni_copy(uri.toString());

                                        updatedMember(mMember, R.string.text_modified_cni);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure ( @NonNull Exception e ) {

                                        new DialogPersonal().showDialog(
                                                mEditProfileActivity,
                                                getString(R.string.text_edit_profile),
                                                getString(R.string.text_operation_failed),
                                                Status.AlertStatus.error ,
                                                null ,
                                                false ,
                                                mActivity);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure ( @NonNull Exception e ) {

                        new DialogPersonal().showDialog(
                                mEditProfileActivity,
                                getString(R.string.text_edit_profile),
                                getString(R.string.text_operation_failed),
                                Status.AlertStatus.error ,
                                null ,
                                false ,
                                mActivity);
                    }
                });
    }

    private void uploadImageProfile(){
        sMemberHelper.uploadImage(selectedImageProfilUri, String.valueOf(DirectoryUpload.Profile))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
                        taskSnapshot.getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess ( Uri uri ) {
                                        sMemberHelper.deleteImage(mMember.getProfile_picture());
                                        mMember.setProfile_picture(uri.toString());

                                        updatedMember(mMember,  R.string.text_modified_profile);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure ( @NonNull Exception e ) {

                                        new DialogPersonal().showDialog(
                                                mEditProfileActivity,
                                                getString(R.string.text_edit_profile),
                                                getString(R.string.text_operation_failed),
                                                Status.AlertStatus.error ,
                                                null ,
                                                false ,
                                                mActivity);

                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure ( @NonNull Exception e ) {

                        new DialogPersonal().showDialog(
                                mEditProfileActivity,
                                getString(R.string.text_edit_profile),
                                getString(R.string.text_operation_failed),
                                Status.AlertStatus.error ,
                                null ,
                                false ,
                                mActivity);

                    }
                });
    }

    private void updatedMember( final Member member, final int messageSuccess){
        sMemberHelper.setMember(member);
        Model.saveFormPreference(member, getString(R.string.SHARED_PREF_MEMBER_LOGIN), getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );

        new DialogPersonal().showDialog(
                mEditProfileActivity,
                getString(R.string.text_edit_profile),
                getString(messageSuccess),
                Status.AlertStatus.success ,
                null ,
                false ,
                mActivity);
    }

    private void saveMember(){
        mMember.setFull_name(text_full_name_register.getText().toString());
        mMember.setBirth_date(text_birth_date_register.getText().toString());
        mMember.setSex(Sex.valueOf(spinner_sex_register_group.getSelectedItem().toString()));
        mMember.setCni_number(text_cni_number_register.getText().toString());
        mMember.setCountry((Country) spinner_country_register.getSelectedItem());
        mMember.setCity((City) spinner_city_register.getSelectedItem());
        mMember.setAddress(text_address_register.getText().toString());
        mMember.setPhone_number(spinner_code_phone_register.getSelectedItem().toString() + " " + text_phone_number_register.getText().toString());
        mMember.setPassword(text_password_register.getText().toString());
        updatedMember(mMember, R.string.text_modified_account);

    }

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode == 0 && resultCode == RESULT_OK && data != null){
            this.selectedImageCNIUri = data.getData();
            DesignApp.updateImage(getApplicationContext(), image_cni, null, this.selectedImageCNIUri);
            uploadImageCNI();
        }

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            this.selectedImageProfilUri = data.getData();
            DesignApp.updateImage(getApplicationContext(), profile_image, null, this.selectedImageProfilUri);
            uploadImageProfile();
        }
    }
}
