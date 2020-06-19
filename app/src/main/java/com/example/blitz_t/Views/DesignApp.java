package com.example.blitz_t.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.blitz_t.Models.Model;
import com.squareup.picasso.Picasso;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class DesignApp {

    public static void blurEffect ( BlurView blurView, Context context, Window window , float radius) {

        View decorView = window.getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();
        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(context))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }

    public static void updateImage( Context context, ImageView imageView, String linkImage, Uri uri ) {
        imageView.setBackground(null);
        if ( uri != null ) Picasso.with(context)
                .load(uri)
                .into(imageView);
        else{
            if(linkImage == null || linkImage.isEmpty())
                return;
            Picasso.with(context)
                .load(linkImage)
                .into(imageView);
        }
    }

    public static void chooseImage( Activity activity, int requestCode ){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
