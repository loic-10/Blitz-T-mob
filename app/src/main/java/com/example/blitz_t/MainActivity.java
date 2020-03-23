package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button main_activity_button_login;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_activity_button_login = findViewById(R.id.main_activity_button_login);

        main_activity_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                startSignInActivity();
            }
        });

    }

    //FOR DATA
    // 1 - Identifier for Sign-In Activity
    private static final int RC_SIGN_IN = 123;

    // --------------------
    // ACTIONS
    // --------------------


    // --------------------
    // NAVIGATION
    // --------------------

    // 2 - Launch Sign-In Activity
    private void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.logo)
                        .build(),
                RC_SIGN_IN);
    }
}
