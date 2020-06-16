package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class AccountHelper extends DB<Account> {

    static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    static SavingHelper sSavingHelper = new SavingHelper(new Saving());

    public AccountHelper ( Account data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setAccount( Account account){
        setObject(account, account.get_id());
    }

    // --- GET ---

    public DatabaseReference getAccounts(){
        return getReference();
    }

    public Account getAccount( String _id)  {
        ArrayList<Account> accounts = new ArrayList<>();
        getReferenceObject(_id, accounts , new Object());
        return accounts.get(0);
    }

    public Account thisAccount(ArrayList<Account> accounts, String _id){
        for (Account account : accounts) {
            if(account.get_id().equals(_id)){
                return account;
            }
        }
        return null;
    }

    public void completedAccounts(ArrayList<Account> accounts){
        getObjects(accounts);
    }

    //

    public void debitedAccount(Account account, double amount, ArrayList<Saving> savings) throws Exception {

        Saving saving = null;

        boolean is_saving = account.getAccount_type().equals(Status.AccountType.saving);
        if (is_saving)
        {
            saving = sSavingHelper.getSavingInProgress(account.get_id(), savings);
        }

        account.setBalance(account.getBalance() - amount);

        if (account.getBalance() >= 0)
        {
            setAccount(account);
            if (is_saving && saving != null)
            {
                sSavingHelper.debitedSaving(saving, amount);
            }
        }
        else
        {
            throw new Exception("Le solde final ne doit pas etre negatif!");
        }
    }

    public void creditedAccount(Account account, double amount, ArrayList<Saving> savings, Microfinance microfinance) throws Exception {

        Saving saving = null;

        boolean is_saving = account.getAccount_type().equals(Status.AccountType.saving);
        if (is_saving)
        {
            saving = sSavingHelper.getSavingInProgress(account.get_id(), savings);
        }

        account.setBalance(account.getBalance() + amount);

        setAccount(account);
        if (is_saving && saving != null)
        {
            sSavingHelper.creditedSaving(saving, amount);
        }
        else
        {
            if(account.getBalance() <= microfinance.getMontant_maximum_solde_compte_courant())
            {
                setAccount(account);
            }
            else
            {
                throw new Exception("Le solde final ne doit pas etre superieur a " + microfinance.getMontant_maximum_solde_compte_courant());
            }
        }
    }

    // --- DELETE ---

    public void deleteAccount( String _id ){
        removeObject(_id);
    }
}
