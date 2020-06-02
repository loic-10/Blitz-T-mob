package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AccountHelper {

    // --- CREATE AND SET ---

    public static void setAccount( Account account){
        new DB<Account>(account).setObject(account, account.get_id());
    }

    // --- GET ---

    public static DatabaseReference getAccounts(){
        return new DB<Account>(new Account()).getReference();
    }

    public static Account getAccount( String _id)  {
        ArrayList<Account> accounts = new ArrayList<>();
        new DB<Account>(new Account()).getReferenceObject(_id, accounts , new Object());
        return accounts.get(0);
    }

    //

    public static void debitedAccount(Account account, double amount) throws Exception {
        ArrayList<Saving> savings = new ArrayList<>();
        boolean is_saving = account.getAccount_type().equals(Status.AccountType.saving);
        if (is_saving)
        {
            SavingHelper.getSavingInProgress(account.get_id(), savings);
        }

        account.setBalance(account.getBalance() - amount);

        if (account.getBalance() >= 0)
        {
            setAccount(account);
            if (is_saving && savings.size() > 0)
            {
                SavingHelper.debitedSaving(savings.get(0), amount);
            }
        }
        else
        {
            throw new Exception("Le solde final ne doit pas etre negatif!");
        }
    }

    public static void creditedAccount(Account account, double amount) throws Exception {
        ArrayList<Saving> savings = new ArrayList<>();
        boolean is_saving = account.getAccount_type().equals(Status.AccountType.saving);
        if (is_saving)
        {
            SavingHelper.getSavingInProgress(account.get_id(), savings);
        }

        account.setBalance(account.getBalance() + amount);

        setAccount(account);
        if (is_saving && savings.size() > 0)
        {
            SavingHelper.creditedSaving(savings.get(0), amount);
        }
        else
        {
            if(account.getBalance() <= MicrofinanceHelper.getMicrofinance(account.getCustomer().getMicrofinance().get_id()).getMontant_maximum_solde_compte_courant())
            {
                setAccount(account);
            }
            else
            {
                throw new Exception("Le solde final ne doit pas etre superieur a " + MicrofinanceHelper.getMicrofinance(account.getCustomer().getMicrofinance().get_id()).getMontant_maximum_solde_compte_courant());
            }
        }
    }

    // --- DELETE ---

    public static void deleteAccount( String _id ){
        new DB<Account>(new Account()).removeObject(_id);
    }
}
