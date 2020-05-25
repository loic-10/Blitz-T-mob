package com.example.blitz_t.Views.Register.Member;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.DirectoryUpload;
import com.example.blitz_t.Api.MemberHelper;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.example.blitz_t.Views.Login.Member.LoginMemberActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.UploadTask;
import com.libRG.CustomTextView;
import java.util.List;
import java.util.UUID;

public class RegisterMemberPart2Activity extends AppCompatActivity {


    private CustomTextView text_link_cni_register_group;
    private ImageView image_cni_register;
    private CustomTextView text_link_profil_register_group;
    private ImageView image_profil_register;
    private SmartMaterialSpinner spinner_code_phone_register;
    private TextFieldBoxes text_phone_number_register_group, text_password_register_group, text_confirm_password_register_group;
    private ExtendedEditText text_phone_number_register, text_password_register, text_confirm_password_register;
    private Button btn_sign_in_register_member_part3, btn_prev_register_member_part3;
    private Uri selectedImageCNIUri = null;
    private Uri selectedImageProfileUri = null;
    private Member mMember;
    private Activity mActivity;
    private ContextWrapper mContextWrapper;
    private List<String > listNumberCode;
    private TextFieldBoxes text_cni_number_register_group;
    private ExtendedEditText text_cni_number_register;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member_part2);

        mContextWrapper = this;

        mActivity = this;

        initView();

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

        initSpinner();

        initEvent();

        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_further_information));

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

    private void initSpinner () {
        Model.checkListNumberCode(spinner_code_phone_register, mMember);
    }

    private void startSaveMember(){
        if(isComplete(text_cni_number_register.getText().toString(),
                selectedImageCNIUri,
                selectedImageProfileUri,
                spinner_code_phone_register.getSelectedItemPosition(),
                text_phone_number_register.getText().toString(),
                text_password_register.getText().toString(),
                text_confirm_password_register.getText().toString())) {
            uploadImageCNI();
        }
        else {
            Snackbar.make(btn_sign_in_register_member_part3, getString(R.string.text_error_complete_field), Snackbar.LENGTH_LONG).show();
        }
    }

    private void uploadImageCNI(){
        MemberHelper.uploadImage(selectedImageCNIUri , String.valueOf(DirectoryUpload.CNI))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
                        taskSnapshot.getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess ( Uri uri ) {
                                        uploadImageProfile(uri.toString());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure ( @NonNull Exception e ) {

                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure ( @NonNull Exception e ) {

                    }
                });

    }

    private void uploadImageProfile( final String imageCNIUrl){
        MemberHelper.uploadImage(selectedImageProfileUri, String.valueOf(DirectoryUpload.Profile))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
                        taskSnapshot.getStorage()
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess ( Uri uri ) {
                                        saveMember( imageCNIUrl, uri.toString());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure ( @NonNull Exception e ) {

                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure ( @NonNull Exception e ) {

                    }
                });
    }

    private boolean isComplete(String cniNumber, Uri cniCopyUri, Uri profileUri, int codePhone, String phoneNumber, String password, String confirmPassword){
        return !cniNumber.isEmpty() &&
                cniCopyUri != null &&
                profileUri != null &&
                codePhone > -1 &&
                !phoneNumber.isEmpty() &&
                !password.isEmpty() &&
                !confirmPassword.isEmpty() &&
                password.equals(confirmPassword);
    }

    private void saveMember(String imageCNIUrl, String imageProfileUrl){
        final Intent intent = new Intent(getApplicationContext(), LoginMemberActivity.class);
        mMember.setCni_copy(imageCNIUrl);
        mMember.setProfile_picture(imageProfileUrl);
        mMember.setPhone_number(spinner_code_phone_register.getSelectedItem().toString() + " " + text_phone_number_register.getText().toString());
        mMember.setPassword(text_password_register.getText().toString());
        mMember.set_id(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        mMember.setCni_number(text_cni_number_register.getText().toString());
        mMember.setRegistration_date(Model.currentDate());
        MemberHelper.setMember(mMember);
        Model.clearFormPreference(mMember, getString(R.string.SHARED_PREF_MEMBER_REGISTER), getString(R.string.PREFERENCE_FILE_KEY), mContextWrapper );
        startActivity(intent);
    }

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode == 0 && resultCode == RESULT_OK && data != null){
            this.selectedImageCNIUri = data.getData();
            DesignApp.updateImage(getApplicationContext(), image_cni_register, null, this.selectedImageCNIUri);
        }

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            this.selectedImageProfileUri = data.getData();
            DesignApp.updateImage(getApplicationContext(), image_profil_register, null, this.selectedImageProfileUri);
        }
    }

    private void fillForm ( Member member ) {
        text_cni_number_register.setText(member.getCni_number());
        text_phone_number_register.setText(member.getPhone_number() != null ? member.getPhone_number().split(" ")[1] : "");

    }

    private void initEvent () {
        btn_sign_in_register_member_part3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                startSaveMember();
            }
        });

        btn_prev_register_member_part3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(getApplicationContext(), RegisterMemberPart1Activity.class);
                startActivity(intent);
            }
        });


        text_link_cni_register_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity, 0);
            }
        });

        image_cni_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity, 0);
            }
        });

        text_link_profil_register_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity, 1);
            }
        });

        image_profil_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                DesignApp.chooseImage(mActivity, 1);
            }
        });
    }

    private void initView () {
        text_cni_number_register_group = findViewById(R.id.text_cni_number_register_group);
        text_cni_number_register = findViewById(R.id.text_cni_number_register);
        text_link_cni_register_group = findViewById(R.id.text_link_cni_register_group);
        image_cni_register = findViewById(R.id.image_cni_register);
        text_link_profil_register_group = findViewById(R.id.text_link_profil_register_group);
        image_profil_register = findViewById(R.id.image_profil_register);
        spinner_code_phone_register = findViewById(R.id.spinner_code_phone_register);
        text_phone_number_register_group = findViewById(R.id.text_phone_number_register_group);
        text_phone_number_register = findViewById(R.id.text_phone_number_register);
        text_password_register_group = findViewById(R.id.text_password_register_group);
        text_confirm_password_register_group = findViewById(R.id.text_confirm_password_register_group);
        text_confirm_password_register = findViewById(R.id.text_confirm_password_register);
        text_password_register = findViewById(R.id.text_password_register);
        btn_sign_in_register_member_part3 = findViewById(R.id.btn_sign_in_register_member_part3);
        btn_prev_register_member_part3 = findViewById(R.id.btn_prev_register_member_part3);
    }
}
