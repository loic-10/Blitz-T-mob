package com.example.blitz_t;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;

import com.example.blitz_t.Views.DesignApp;

public class ChoiceLogActivity extends AppCompatActivity {

    private View button_log_in, button_create_account;
    private View text_skip;
    private Intent mIntent;
    private BlurView blurView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_log);

        blurView = findViewById(R.id.blurView);

        DesignApp.blurEffect(blurView, this, getWindow(), 5);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button_log_in = findViewById(R.id.button_log_in);
        button_create_account = findViewById(R.id.button_create_account);
        text_skip = findViewById(R.id.text_skip);

        text_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                mIntent = new Intent(ChoiceLogActivity.this, ChoiceMicrofinanceActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
