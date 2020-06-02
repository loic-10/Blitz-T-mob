package com.example.blitz_t.Controllers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.R;

public class DialogPersonal {

    private Dialog mDialog;
    private ImageView icon_status;
    private TextView text_title;
    private TextView text_message;
    private ImageView image_status;
    private Button btn_ok;
    private Intent mIntent;
    private Context mContext;
    private boolean mustFinnish;
    private Activity mActivity;


    public void showDialog ( Context context , String title , String message , Status.AlertStatus alertStatus , Intent intent , boolean mustFinnish , Activity activity ){
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.alert_dialog);
        mIntent = intent;
        mContext = context;
        this.mustFinnish = mustFinnish;
        mActivity= activity;

        initView();

        initEvent();

        fillForm(title, message, alertStatus);

        mDialog.show();
    }

    private void initEvent () {

        mDialog.setOnDismissListener(mOnDismissListener);

        mDialog.setOnCancelListener(mOnCancelListener);

        btn_ok.setOnClickListener(mListener);
    }

    private void fillForm ( String title , String message , Status.AlertStatus alertStatus ) {
        icon_status.setImageResource(alertStatus.equals(Status.AlertStatus.error) ?
                R.drawable.ic_error :
                R.drawable.ic_ok);
        text_title.setText(title);
        text_message.setText(message);
        image_status.setImageResource(alertStatus.equals(Status.AlertStatus.error) ?
                R.drawable.error :
                R.drawable.succes);
    }

    private void initView () {
        icon_status = mDialog.findViewById(R.id.icon_status);
        text_title = mDialog.findViewById(R.id.text_title);
        text_message = mDialog.findViewById(R.id.text_message);
        image_status = mDialog.findViewById(R.id.image_status);
        btn_ok = mDialog.findViewById(R.id.btn_ok);

    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            if ( v.getId() == R.id.btn_ok ) {
                dismiss();
            }
        }
    };

    private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss ( DialogInterface dialog ) {
            dismiss();
        }
    };

    private DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel ( DialogInterface dialog ) {
            dismiss();
        }
    };

    private void dismiss(){
        mDialog.dismiss();
        if ( mIntent != null )
        {
            mContext.startActivity(mIntent);
            if(mustFinnish){
                mActivity.finish();
            }
        }
    }
}
