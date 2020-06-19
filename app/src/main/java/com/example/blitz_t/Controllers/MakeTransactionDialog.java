package com.example.blitz_t.Controllers;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.*;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Views.Account.Activity.AccountCustomerActivity;
import com.example.blitz_t.Api.MicrofinanceHelper;
import com.example.blitz_t.Api.SavingHelper;
import com.example.blitz_t.Api.TransactionHelper;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.example.blitz_t.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import static com.example.blitz_t.Models.Model.currentDateString;
import static com.example.blitz_t.Models.Model.getNewId;

public class MakeTransactionDialog {

    private TextFieldBoxes text_full_name_recipient_group,
            text_cni_number_recipient_group,
            text_key_code_group,
            text_amount_group,
            text_password_group,
            text_phone_number_recipient_group;
    private ExtendedEditText text_full_name_recipient,
            text_cni_number_recipient,
            text_key_code,
            text_amount,
            text_password,
            text_phone_number_recipient;
    private SmartMaterialSpinner spinner_status_recipient,
            spinner_account,
            spinner_code_phone_recipient;
    private ImageView image_transaction;
    private TextView text_title;
    private Button btn_save;
    private View spinner_status_recipient_group,
            spinner_account_group,
            spinner_code_phone_recipient_group;
    private Member mMember;
    private Microfinance mMicrofinance;
    private Customer mCustomer;
    private Account mAccount;
    private ConstraintLayout.LayoutParams mLayoutParams =
            new ConstraintLayout.LayoutParams(ConstraintLayout.LAYOUT_DIRECTION_INHERIT,
            ConstraintLayout.LAYOUT_DIRECTION_INHERIT);
    private ViewGroup.LayoutParams text_full_name_recipient_group_layout_params;
    private ViewGroup.LayoutParams text_cni_number_recipient_group_layout_params;
    private ViewGroup.LayoutParams text_key_code_group_layout_params;
    private ViewGroup.LayoutParams spinner_status_recipient_group_layout_params;
    private ViewGroup.LayoutParams spinner_account_group_layout_params;
    private ViewGroup.LayoutParams spinner_code_phone_recipient_group_layout_params;
    private Status.RecipientStatus mRecipientStatus;
    private Status.TransactionType mTransactionType;
    private Dialog dialog;
    private AccountCustomerActivity mAccountCustomerActivity;
    private Activity mActivity;

    static TransactionHelper sTransactionHelper = new TransactionHelper(new Transaction());

    static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    static SavingHelper sSavingHelper = new SavingHelper(new Saving());

    private static ArrayList<Saving> sSavings = new ArrayList<>();

    private static ArrayList<Microfinance> sMicrofinances = new ArrayList<>();

    public void showDialog ( AccountCustomerActivity accountCustomerActivity , Status.TransactionType transactionType , Member member , Microfinance microfinance , Account account , Customer customer , Activity activity ){
        dialog = new Dialog(accountCustomerActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pop_make_transaction);

        mAccountCustomerActivity = accountCustomerActivity;
        mActivity = activity;
        mMember = member;
        mMicrofinance = microfinance;
        mAccount = account;
        mCustomer = customer;
        mTransactionType = transactionType;

        initView(dialog);

        initList();

        saveLayoutParams();

        initSpinner();

        initEvent();

        assignLayoutParams();

        eliminatedView(mTransactionType);

        fillForm(mTransactionType);

        dialog.show();

    }

    private void initList () {
        sMicrofinanceHelper.getMicrofinance(mMicrofinance.get_id(), sMicrofinances);
        sSavingHelper.completedSavings(sSavings);
    }

    private void saveLayoutParams () {
        text_full_name_recipient_group_layout_params = text_full_name_recipient_group.getLayoutParams();
        text_cni_number_recipient_group_layout_params = text_cni_number_recipient_group.getLayoutParams();
        text_key_code_group_layout_params = text_key_code_group.getLayoutParams();
        spinner_status_recipient_group_layout_params = spinner_status_recipient_group.getLayoutParams();
        spinner_account_group_layout_params = spinner_account_group.getLayoutParams();
        spinner_code_phone_recipient_group_layout_params = spinner_code_phone_recipient_group.getLayoutParams();
    }

    private void assignLayoutParams(){
        text_full_name_recipient_group.setLayoutParams(text_full_name_recipient_group.getVisibility() == View.VISIBLE ?
                text_full_name_recipient_group_layout_params :
                mLayoutParams);
        text_cni_number_recipient_group.setLayoutParams(text_cni_number_recipient_group.getVisibility() == View.VISIBLE ?
                text_cni_number_recipient_group_layout_params :
                mLayoutParams);
        text_key_code_group.setLayoutParams(text_key_code_group.getVisibility() == View.VISIBLE ?
                text_key_code_group_layout_params :
                mLayoutParams);
        spinner_status_recipient_group.setLayoutParams(spinner_status_recipient_group.getVisibility() == View.VISIBLE ?
                spinner_status_recipient_group_layout_params :
                mLayoutParams);
        spinner_account_group.setLayoutParams(spinner_account_group.getVisibility() == View.VISIBLE ?
                spinner_account_group_layout_params :
                mLayoutParams);
        spinner_code_phone_recipient_group.setLayoutParams(spinner_code_phone_recipient_group.getVisibility() == View.VISIBLE ?
                spinner_code_phone_recipient_group_layout_params :
                mLayoutParams);
    }

    private void initEvent () {

        spinner_status_recipient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                mRecipientStatus = Status.RecipientStatus.valueOf(adapterView.getSelectedItem().toString());
                eliminatedView(mRecipientStatus);
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });

        btn_save.setOnClickListener(mListener);
    }

    private void eliminatedView ( Status.RecipientStatus recipientStatus ) {

        spinner_account_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.VISIBLE :
                View.INVISIBLE);

        text_full_name_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.INVISIBLE :
                View.VISIBLE);
        spinner_code_phone_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_phone_number_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_cni_number_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_key_code_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.INVISIBLE :
                View.VISIBLE);

        assignLayoutParams();
    }

    private void initSpinner () {

        Status.RecipientStatus[] array = Status.RecipientStatus.values();
        List<Status.RecipientStatus> recipientStatuses = Arrays.asList(array);
        spinner_status_recipient.setItem(recipientStatuses);

        Model.checkListNumberCode(spinner_code_phone_recipient, mMember);

        Model.checkAccountMicrofinance(spinner_account, mMicrofinance, mAccount);

        assignLayoutParams();
    }

    private void fillForm ( Status.TransactionType transactionType ) {
        image_transaction.setImageResource(transactionType.equals(Status.TransactionType.deposit) ?
                R.drawable.ic_deposit :
                (transactionType.equals(Status.TransactionType.withdrawal) ?
                        R.drawable.ic_withdrawal :
                        R.drawable.ic_initiate_money_transfer));

        text_title.setText(transactionType.toString());

        assignLayoutParams();
    }

    private void eliminatedView ( Status.TransactionType transactionType ) {
        spinner_status_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        spinner_account_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_full_name_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        spinner_code_phone_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_phone_number_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_cni_number_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);
        text_key_code_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.INVISIBLE :
                View.VISIBLE);

        if(transactionType.equals(Status.TransactionType.transfer)){
            spinner_account_group.setVisibility(View.INVISIBLE);
            text_full_name_recipient_group.setVisibility(View.INVISIBLE);
            spinner_code_phone_recipient_group.setVisibility(View.INVISIBLE);
            text_phone_number_recipient_group.setVisibility(View.INVISIBLE);
            text_cni_number_recipient_group.setVisibility(View.INVISIBLE);
            text_key_code_group.setVisibility(View.INVISIBLE);
        }

        assignLayoutParams();
    }

    private void initView ( Dialog dialog ) {
        image_transaction = dialog.findViewById(R.id.image_transaction);
        text_title = dialog.findViewById(R.id.text_title);
        btn_save = dialog.findViewById(R.id.btn_save);
        spinner_status_recipient_group = dialog.findViewById(R.id.spinner_status_recipient_group);
        spinner_status_recipient = dialog.findViewById(R.id.spinner_status_recipient);
        spinner_account_group = dialog.findViewById(R.id.spinner_account_group);
        spinner_account = dialog.findViewById(R.id.spinner_account);
        text_full_name_recipient_group = dialog.findViewById(R.id.text_full_name_recipient_group);
        text_full_name_recipient = dialog.findViewById(R.id.text_full_name_recipient);
        spinner_code_phone_recipient_group = dialog.findViewById(R.id.spinner_code_phone_recipient_group);
        spinner_code_phone_recipient = dialog.findViewById(R.id.spinner_code_phone_recipient);
        text_phone_number_recipient_group = dialog.findViewById(R.id.text_phone_number_recipient_group);
        text_phone_number_recipient = dialog.findViewById(R.id.text_phone_number_recipient);
        text_cni_number_recipient_group = dialog.findViewById(R.id.text_cni_number_recipient_group);
        text_cni_number_recipient = dialog.findViewById(R.id.text_cni_number_recipient);
        text_key_code_group = dialog.findViewById(R.id.text_key_code_group);
        text_key_code = dialog.findViewById(R.id.text_key_code);
        text_amount_group = dialog.findViewById(R.id.text_amount_group);
        text_amount = dialog.findViewById(R.id.text_amount);
        text_password_group = dialog.findViewById(R.id.text_password_group);
        text_password = dialog.findViewById(R.id.text_password);

        text_amount_group.setLabelText(text_amount_group.getLabelText() +
                " ( min: " +
                mMicrofinance.getMontant_minimum_transaction() +
                " " +
                mMicrofinance.getCurrency().getCurrency_symbol() +
                " )"
        );
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            switch (v.getId()){
                case R.id.btn_save :
                    saveTransaction();
                    break;
            }
        }
    };

    private boolean isCompleted() throws Exception {
        if(!text_amount.getText().toString().isEmpty() &&
            Double.parseDouble(text_amount.getText().toString()) >= mMicrofinance.getMontant_minimum_transaction() &&
            !text_password.getText().toString().isEmpty() &&
            text_password.getText().toString().equals(mCustomer.getPassword()) &&
            sMicrofinances.size() > 0 &&
            (!mTransactionType.equals(Status.TransactionType.transfer) ||
                (mRecipientStatus.equals(Status.RecipientStatus.customer) ?
                    spinner_account.getSelectedItemPosition() > -1 :
                    (!text_key_code.getText().toString().isEmpty() &&
                        !text_cni_number_recipient.getText().toString().isEmpty() &&
                        !text_full_name_recipient.getText().toString().isEmpty() &&
                        spinner_code_phone_recipient.getSelectedItemPosition() > -1 &&
                        !text_phone_number_recipient.getText().toString().isEmpty()
                    )
                )
            )
        ){
            return true;
        }
        else {
            throw new Exception(mAccountCustomerActivity.getString(R.string.text_error_complete_field));
        }
    }

    private void saveTransaction(){
        try {
            boolean completed = isCompleted();
            Transaction transaction = new Transaction();
            transaction.set_id(getNewId());
            transaction.setSending_account(mAccount);
            transaction.setTransaction_date(currentDateString());
            transaction.setAmount(Double.parseDouble(text_amount.getText().toString()));
            transaction.setNumber_day_waiting(0);
            transaction.setTransaction_type(mTransactionType);
            transaction.setSending_employee(null);
            if(mTransactionType.equals(Status.TransactionType.transfer)){
                if(mRecipientStatus.equals(Status.RecipientStatus.customer)){
                    transaction.setRecipient_account((Account) spinner_account.getSelectedItem());
                }
                else {
                    transaction.setKey_code(text_key_code.getText().toString());
                    transaction.setRecipient_cni_number(text_cni_number_recipient.getText().toString());
                    transaction.setRecipient_name(text_full_name_recipient.getText().toString());
                    transaction.setRecipient_phone_number(spinner_code_phone_recipient.getSelectedItem().toString() +
                            " " +
                            text_phone_number_recipient.getText().toString());
                }
                transaction.setTransaction_status(Status.TransactionStatus.pending_validity);

//                sTransactionHelper.setTransaction(transaction);
                sTransactionHelper.validedTransaction(transaction, null, sMicrofinances.get(0), sSavings);

                new DialogPersonal().showDialog(
                        mAccountCustomerActivity,
                        mTransactionType.toString(),
                        mAccountCustomerActivity.getString(R.string.request_success_transaction),
                        Status.AlertStatus.success ,
                        null ,
                        true ,
                        mActivity);

                dialog.dismiss();
            }
            else {
                transaction.setRecipient_account(mAccount);
                if(mAccount.getAccount_type().equals(Status.AccountType.saving)){
                    transaction.setNumber_day_waiting(sMicrofinances.get(0).getNombre_jour_avis_retrait_epargne());
                    transaction.setTransaction_status(Status.TransactionStatus.advised);
                }
                else {
                    transaction.setTransaction_status(Status.TransactionStatus.pending_validity);
                }

                sTransactionHelper.setTransaction(transaction);

                new DialogPersonal().showDialog(
                        mAccountCustomerActivity,
                        mTransactionType.toString(),
                        mAccountCustomerActivity.getString(R.string.request_go_confirmation_transaction),
                        Status.AlertStatus.success ,
                        null ,
                        true ,
                        mActivity);

                dialog.dismiss();
            }

        }
        catch (Exception ex){
            new DialogPersonal().showDialog(
                    mAccountCustomerActivity,
                    mTransactionType.toString(),
                    ex.getMessage(),
                    Status.AlertStatus.error ,
                    null ,
                    true ,
                    mActivity);

        }

    }
}
