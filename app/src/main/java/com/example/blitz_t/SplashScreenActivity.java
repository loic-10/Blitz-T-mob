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

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

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
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/blitz-t"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage() , Toast.LENGTH_LONG).show();
        }

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button_start = findViewById(R.id.button_start);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        progressBar = findViewById(R.id.progressBar);

        button_start.setVisibility(View.INVISIBLE);


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
