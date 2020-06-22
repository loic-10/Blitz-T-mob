package com.example.blitz_t.Views.Account.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.blitz_t.Api.AccountHelper;
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
import com.example.blitz_t.Views.DesignApp;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.blitz_t.Models.Model.currentDateString;
import static com.example.blitz_t.Models.Model.getNewId;

public class CreateAccountCustomerActivity extends AppCompatActivity {

    private TextFieldBoxes text_balance_account_group,
            text_password_group,
            text_company_name_group,
            text_number_month_group;
    private ExtendedEditText text_balance_account,
            text_password,
            text_company_name,
            text_number_month;
    private SmartMaterialSpinner spinner_account_type,
            spinner_type_saving_account_owner;
    private Button btn_save;
    private View spinner_account_type_group,
            spinner_type_saving_account_owner_group;

    private ImageView image_microfinance;

    private Toolbar app_bar;

    private CollapsingToolbarLayout collapsing;

    private CreateAccountCustomerActivity mCreateAccountCustomerActivity;

    private MenuItem buttonItem;

    private Activity mActivity;

    private Member mMember;

    private Customer mCustomer;

    private Microfinance mMicrofinance;

    private Status.AccountType mAccountType;

    private Status.TypeSavingAccountOwner mTypeSavingAccountOwner;

    private static SavingHelper sSavingHelper = new SavingHelper(new Saving());

    private static AccountHelper sAccountHelper = new AccountHelper(new Account());

    private static TransactionHelper sTransactionHelper = new TransactionHelper(new Transaction());

    private static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    private static ArrayList<Microfinance> sMicrofinances = new ArrayList<>();

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_customer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mActivity = this;

        mCreateAccountCustomerActivity = this;

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

        initView();

        if(buttonItem != null){
            buttonItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick ( MenuItem item ) {
                    finish();
                    return true;
                }
            });
        }

        initSpinner();

        initList();

        initEvent();

        initInfo();
    }

    private void initInfo ( ) {

        DesignApp.updateImage(getApplicationContext() , image_microfinance , mMicrofinance.getImage() , null);

        collapsing.setTitle(getString(R.string.text_create_account).toUpperCase());
        collapsing.setCollapsedTitleTextColor(getColor(R.color.defaultColorWhite));
    }

    private void initList () {
        sMicrofinanceHelper.getMicrofinance(mMicrofinance.get_id(), sMicrofinances);
    }

    private void initEvent () {

        spinner_account_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                mAccountType = Status.AccountType.valueOf(adapterView.getSelectedItem().toString());
                eliminatedView(mAccountType);
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });

        spinner_type_saving_account_owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int position, long id) {
                mTypeSavingAccountOwner = Status.TypeSavingAccountOwner.valueOf(adapterView.getSelectedItem().toString());
                eliminatedView(mTypeSavingAccountOwner);
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView) {
            }
        });

        btn_save.setOnClickListener(mListener);

    }

    private void eliminatedView ( Status.TypeSavingAccountOwner typeSavingAccountOwner ) {

        text_company_name_group.setVisibility(typeSavingAccountOwner.equals(Status.TypeSavingAccountOwner.company) ?
                View.VISIBLE :
                View.GONE);
    }

    private void eliminatedView ( Status.AccountType accountType ) {
        spinner_type_saving_account_owner_group.setVisibility(accountType.equals(Status.AccountType.saving) ?
                View.VISIBLE :
                View.GONE);

        text_number_month_group.setVisibility(accountType.equals(Status.AccountType.saving) ?
                View.VISIBLE :
                View.GONE);
    }

    private void initSpinner () {

        Status.AccountType[] array1 = Status.AccountType.values();
        List<Status.AccountType> accountTypes = Arrays.asList(array1);
        spinner_account_type.setItem(accountTypes);

        Status.TypeSavingAccountOwner[] array2 = Status.TypeSavingAccountOwner.values();
        List<Status.TypeSavingAccountOwner> typeSavingAccountOwners = Arrays.asList(array2);
        spinner_type_saving_account_owner.setItem(typeSavingAccountOwners);

        assignLayoutParams();
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            switch (v.getId()){
                case R.id.btn_save:
                    saveAccount();
                    break;
            }
        }
    };

    private boolean isCompleted() throws Exception {
        if(!text_balance_account.getText().toString().isEmpty() &&
            Double.parseDouble(text_balance_account.getText().toString()) >= mMicrofinance.getMontant_minimum_transaction() &&
            !text_password.getText().toString().isEmpty() &&
            text_password.getText().toString().equals(mCustomer.getPassword()) &&
            spinner_account_type.getSelectedItem() != null &&
            (mAccountType.equals(Status.AccountType.current) ||
                (mAccountType.equals(Status.AccountType.saving) &&
                    !text_number_month.getText().toString().isEmpty() &&
                    Double.parseDouble(text_number_month.getText().toString()) > 0 &&
                    (spinner_type_saving_account_owner.getSelectedItem() != null &&
                        (mTypeSavingAccountOwner.equals(Status.TypeSavingAccountOwner.particular) ||
                            (mTypeSavingAccountOwner.equals(Status.TypeSavingAccountOwner.company) &&
                                !text_company_name.getText().toString().isEmpty()
                            )
                        )
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

    private void saveAccount(){
        try {
            isCompleted();
            Account account = new Account();
            Saving saving = new Saving();
            Transaction transaction = new Transaction();
            account.set_id(getNewId());
            account.setBalance(0);
            account.setAccount_status(Status.AccountStatus.pending_validity);
            account.setRegister_date(currentDateString());
            account.setCustomer(mCustomer);
            account.setAccount_type(mAccountType);

            if(mAccountType.equals(Status.AccountType.saving)){
                account.setType_saving_account_owner(mTypeSavingAccountOwner);
                if(mTypeSavingAccountOwner.equals(Status.TypeSavingAccountOwner.company)){
                    account.setCompany_name(text_company_name.getText().toString());
                }
                saving.setAccount(account);
                saving.setBalance(0);
                saving.set_id(getNewId());
                saving.setNumber_months(Integer.parseInt(text_number_month.getText().toString()));
                saving.setSaving_date(currentDateString());
                saving.setSaving_status(Status.SavingStatus.in_progress);
                sSavingHelper.setSaving(saving);
            }

            transaction.set_id(getNewId());
            transaction.setSending_account(account);
            transaction.setTransaction_date(currentDateString());
            transaction.setAmount(Double.parseDouble(text_balance_account.getText().toString()));
            transaction.setNumber_day_waiting(0);
            transaction.setTransaction_type(Status.TransactionType.deposit);
            transaction.setSending_employee(null);
            transaction.setRecipient_account(account);
            transaction.setTransaction_status(Status.TransactionStatus.pending_validity);
            if(account.getAccount_type().equals(Status.AccountType.saving)){
                transaction.setNumber_day_waiting(sMicrofinances.get(0).getNombre_jour_avis_retrait_epargne());
            }

            sTransactionHelper.setTransaction(transaction);

            sAccountHelper.setAccount(account);

            new DialogPersonal().showDialog(
                    mCreateAccountCustomerActivity,
                    getString(R.string.text_create_account),
                    getString(R.string.text_create_account_successfully),
                    Status.AlertStatus.success ,
                    null ,
                    true ,
                    mActivity);



        }
        catch (Exception ex){
            new DialogPersonal().showDialog(
                    mCreateAccountCustomerActivity,
                    getString(R.string.text_create_account),
                    getString(R.string.text_operation_failed),
                    Status.AlertStatus.error ,
                    null ,
                    false ,
                    mActivity);

        }

    }

    private void assignLayoutParams () {
        spinner_type_saving_account_owner_group.setVisibility(View.GONE);
        text_company_name_group.setVisibility(View.GONE);
        text_number_month_group.setVisibility(View.GONE);
    }

    private void initView () {
        btn_save = findViewById(R.id.btn_save);
        spinner_account_type_group = findViewById(R.id.spinner_account_type_group);
        spinner_account_type= findViewById(R.id.spinner_account_type);
        text_balance_account_group = findViewById(R.id.text_balance_account_group);
        text_balance_account = findViewById(R.id.text_balance_account);
        text_password_group = findViewById(R.id.text_password_group);
        text_password = findViewById(R.id.text_password);
        spinner_type_saving_account_owner_group = findViewById(R.id.spinner_type_saving_account_owner_group);
        spinner_type_saving_account_owner = findViewById(R.id.spinner_type_saving_account_owner);
        text_company_name_group = findViewById(R.id.text_company_name_group);
        text_company_name = findViewById(R.id.text_company_name);
        text_number_month_group = findViewById(R.id.text_number_month_group);
        text_number_month = findViewById(R.id.text_number_month);

        app_bar = findViewById(R.id.app_bar);

        collapsing = findViewById(R.id.collapsing);

        image_microfinance = findViewById(R.id.image_item_micro_finance);

        buttonItem = app_bar.getMenu().findItem(R.id.menu_return);

        text_balance_account_group.setLabelText(text_balance_account_group.getLabelText() +
                " ( min: " +
                mMicrofinance.getMontant_minimum_transaction() +
                " " +
                mMicrofinance.getCurrency().getCurrency_symbol() +
                " )"
        );

    }
}
