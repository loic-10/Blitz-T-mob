package com.example.blitz_t;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.example.blitz_t.Models.Model;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashScreenActivity extends AppCompatActivity {

    private Button button_start;
    private View constraintLayout2;
    private Animation btnAnim, logoInfo;
    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        getWindow().setStatusBarColor(R.color.colorPart2);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        Model.showNotification(this, "New notification", "Voici le contenu");

        button_start = findViewById(R.id.button_start);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        progressBar = findViewById(R.id.progressBar);

        button_start.setVisibility(View.INVISIBLE);

        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        logoInfo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_info_animation);
        constraintLayout2.setAnimation(logoInfo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showButton();
            }
        }, 6000);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                Intent intent = new Intent(SplashScreenActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showButton () {
        progressBar.setVisibility(View.INVISIBLE);
        button_start.setVisibility(View.VISIBLE);
        button_start.setAnimation(btnAnim);
    }
}
