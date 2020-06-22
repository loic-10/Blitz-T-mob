package com.example.blitz_t.Views.Transaction.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.MicrofinanceHelper;
import com.example.blitz_t.Api.SavingHelper;
import com.example.blitz_t.Api.TransactionHelper;
import com.example.blitz_t.Controllers.DialogPersonal;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.example.blitz_t.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.blitz_t.Models.Model.checkAccountCustomer;
import static com.example.blitz_t.Models.Model.currentDateString;
import static com.example.blitz_t.Models.Model.getNewId;

public class MakeTransactionActivity extends AppCompatActivity {

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
    private Activity mActivity;
    private MenuItem buttonItem;
    private ImageView image_type_transaction;
    private Button btn_login_customer;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private ViewPager viewPager_account;

    private MakeTransactionActivity mMakeTransactionActivity;

    static TransactionHelper sTransactionHelper = new TransactionHelper(new Transaction());

    static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    static SavingHelper sSavingHelper = new SavingHelper(new Saving());

    private static ArrayList<Saving> sSavings = new ArrayList<>();

    private static ArrayList<Account> sAccounts = new ArrayList<>();

    private static ArrayList<Microfinance> sMicrofinances = new ArrayList<>();

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_transaction);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mActivity = this;

        mMakeTransactionActivity = this;

        mAccount = (Account) Model.contentPreference(
                new Account(),
                getString(R.string.SHARED_PREF_ACCOUNT_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mCustomer = (Customer) Model.contentPreference(
                new Customer(),
                getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mTransactionType = Status.TransactionType.valueOf((String) Model.contentPreference(
                new Object() ,
                getString(R.string.SHARED_PREF_TRANSACTION_TYPE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this));

        initView();

        initList();

        if(buttonItem != null){
            buttonItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick ( MenuItem item ) {
                    finish();
                    return true;
                }
            });
        }

        initInfo(mTransactionType);

        saveLayoutParams();

        initSpinner();

        initEvent();

        assignLayoutParams();

        eliminatedView(mTransactionType);

        fillForm(mTransactionType);

        checkAccountCustomer( viewPager_account, this, this, mCustomer, mMicrofinance, mAccount, sAccounts);

    }

    private void initInfo ( Status.TransactionType transactionType ) {
        image_type_transaction.setImageResource(transactionType.equals(Status.TransactionType.deposit) ?
                R.drawable.deposit :
                (transactionType.equals(Status.TransactionType.withdrawal) ?
                        R.drawable.withdrawal :
                        R.drawable.transfer));

        collapsing.setTitle(transactionType.toString().toUpperCase());
        collapsing.setCollapsedTitleTextColor(getColor(R.color.defaultColorWhite));
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
                View.GONE);

        text_full_name_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.GONE :
                View.VISIBLE);
        spinner_code_phone_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.GONE :
                View.VISIBLE);
        text_phone_number_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.GONE :
                View.VISIBLE);
        text_cni_number_recipient_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.GONE :
                View.VISIBLE);
        text_key_code_group.setVisibility(recipientStatus.equals(Status.RecipientStatus.customer) ?
                View.GONE :
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
        app_bar.setNavigationIcon(transactionType.equals(Status.TransactionType.deposit) ?
                R.drawable.ic_deposit :
                (transactionType.equals(Status.TransactionType.withdrawal) ?
                        R.drawable.ic_withdrawal :
                        R.drawable.ic_initiate_money_transfer));

        assignLayoutParams();
    }

    private void eliminatedView ( Status.TransactionType transactionType ) {
        spinner_status_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        spinner_account_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        text_full_name_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        spinner_code_phone_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        text_phone_number_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        text_cni_number_recipient_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);
        text_key_code_group.setVisibility((transactionType.equals(Status.TransactionType.deposit) ||
                transactionType.equals(Status.TransactionType.withdrawal)) ?
                View.GONE :
                View.VISIBLE);

        if(transactionType.equals(Status.TransactionType.transfer)){
            spinner_account_group.setVisibility(View.GONE);
            text_full_name_recipient_group.setVisibility(View.GONE);
            spinner_code_phone_recipient_group.setVisibility(View.GONE);
            text_phone_number_recipient_group.setVisibility(View.GONE);
            text_cni_number_recipient_group.setVisibility(View.GONE);
            text_key_code_group.setVisibility(View.GONE);
        }

        assignLayoutParams();
    }

    private void initView () {
        btn_save = findViewById(R.id.btn_save);
        spinner_status_recipient_group = findViewById(R.id.spinner_status_recipient_group);
        spinner_status_recipient = findViewById(R.id.spinner_status_recipient);
        spinner_account_group = findViewById(R.id.spinner_account_group);
        spinner_account = findViewById(R.id.spinner_account);
        text_full_name_recipient_group = findViewById(R.id.text_full_name_recipient_group);
        text_full_name_recipient = findViewById(R.id.text_full_name_recipient);
        spinner_code_phone_recipient_group = findViewById(R.id.spinner_code_phone_recipient_group);
        spinner_code_phone_recipient = findViewById(R.id.spinner_code_phone_recipient);
        text_phone_number_recipient_group = findViewById(R.id.text_phone_number_recipient_group);
        text_phone_number_recipient = findViewById(R.id.text_phone_number_recipient);
        text_cni_number_recipient_group = findViewById(R.id.text_cni_number_recipient_group);
        text_cni_number_recipient = findViewById(R.id.text_cni_number_recipient);
        text_key_code_group = findViewById(R.id.text_key_code_group);
        text_key_code = findViewById(R.id.text_key_code);
        text_amount_group = findViewById(R.id.text_amount_group);
        text_amount = findViewById(R.id.text_amount);
        text_password_group = findViewById(R.id.text_password_group);
        text_password = findViewById(R.id.text_password);

        viewPager_account = findViewById(R.id.viewPager_account);
        
        app_bar = findViewById(R.id.app_bar);
        
        collapsing = findViewById(R.id.collapsing);
        
        image_type_transaction = findViewById(R.id.image_type_transaction);
        
        buttonItem = app_bar.getMenu().findItem(R.id.menu_return);

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
                sAccounts.size() > 0 &&
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
            throw new Exception(getString(R.string.text_error_complete_field));
        }
    }

    private void saveTransaction(){
        try {
            isCompleted();
            Transaction transaction = new Transaction();
            transaction.set_id(getNewId());
            transaction.setSending_account(sAccounts.get(0));
            transaction.setTransaction_date(currentDateString());
            transaction.setAmount(Double.parseDouble(text_amount.getText().toString()));
            transaction.setNumber_day_waiting(0);
            transaction.setTransaction_type(mTransactionType);
            transaction.setTransaction_status(Status.TransactionStatus.pending_validity);
            transaction.setSending_employee(null);
            if(mTransactionType.equals(Status.TransactionType.transfer)){
                if(mRecipientStatus.equals(Status.RecipientStatus.customer)){
                    transaction.setRecipient_account((Account) spinner_account.getSelectedItem());

                    sTransactionHelper.validedTransaction(transaction, null, sMicrofinances.get(0), sSavings);
                }
                else {
                    transaction.setKey_code(text_key_code.getText().toString());
                    transaction.setRecipient_cni_number(text_cni_number_recipient.getText().toString());
                    transaction.setRecipient_name(text_full_name_recipient.getText().toString());
                    transaction.setRecipient_phone_number(spinner_code_phone_recipient.getSelectedItem().toString() +
                            " " +
                            text_phone_number_recipient.getText().toString());

                    sTransactionHelper.transferredTransaction(transaction, null, sMicrofinances.get(0), sSavings);
                }

                new DialogPersonal().showDialog(
                        mMakeTransactionActivity,
                        mTransactionType.toString(),
                        getString(R.string.request_success_transaction),
                        Status.AlertStatus.success ,
                        null ,
                        true ,
                        mActivity);

            }
            else {
                transaction.setRecipient_account(sAccounts.get(0));
                transaction.setTransaction_status(Status.TransactionStatus.pending_validity);
                if(sAccounts.get(0).getAccount_type().equals(Status.AccountType.saving)){
                    transaction.setNumber_day_waiting(sMicrofinances.get(0).getNombre_jour_avis_retrait_epargne());
                    if(mTransactionType.equals(Status.TransactionType.withdrawal)) {
                        transaction.setTransaction_status(Status.TransactionStatus.advised);
                    }
                }

                sTransactionHelper.setTransaction(transaction);

                new DialogPersonal().showDialog(
                        mMakeTransactionActivity,
                        mTransactionType.toString(),
                        getString(R.string.request_go_confirmation_transaction),
                        Status.AlertStatus.success ,
                        null ,
                        true ,
                        mActivity);

            }

        }
        catch (Exception ex){
            new DialogPersonal().showDialog(
                    mMakeTransactionActivity,
                    mTransactionType.toString(),
                    ex.getMessage(),
                    Status.AlertStatus.error ,
                    null ,
                    false ,
                    mActivity);

        }

    }
}
