package com.example.blitz_t.Controllers;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.example.blitz_t.AccountCustomerActivity;
import com.example.blitz_t.R;

public class DialogPerso {
    public static void showDialog( AccountCustomerActivity mainActivity, String message){
        Dialog dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pop_make_transaction);

//        TextView text_message = dialog.findViewById(R.id.text_message);
//        text_message.setText(message);
//
//        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
//        Button btn_save = dialog.findViewById(R.id.btn_save);
//
//        btn_cancel.setOnClickListener(mListener);
//        btn_save.setOnClickListener(mListener);

        dialog.show();

    }

    private static View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            switch (v.getId()){
                case R.id.btn_cancel :
                    break;
                case R.id.btn_save :
                    break;
            }
        }
    };
}
