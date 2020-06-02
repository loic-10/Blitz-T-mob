package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.Date;

public class TransactionHelper {

    // --- CREATE AND SET ---

    public static void setTransaction( Transaction transaction) throws Exception {
        try {
            new DB<Transaction>(transaction).setObject(transaction, transaction.get_id());
        }
        catch (Exception ex){
            throw new Exception("Erreur lors de la sauvegarde des donnees !");
//            Resources.getSystem().getString(R.string.);
        }
    }

    // --- GET ---

    public static DatabaseReference getTransactions(){
        return new DB<Transaction>(new Transaction()).getReference();
    }

    public static Transaction getTransaction( String _id)  {
        ArrayList<Transaction> transactions = new ArrayList<>();
        new DB<Transaction>(new Transaction()).getReferenceObject(_id, transactions , new Object());
        return transactions.get(0);
    }

    //

    public static void canceledTransaction(String transaction_id, Employee employee) throws Exception {
        Transaction transaction = getTransaction(transaction_id);
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.canceled);
        setTransaction(transaction);
    }

    public static void transferredTransaction(String transaction_id, Employee employee) throws Exception {
        Transaction transaction = getTransaction(transaction_id);
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.transferred);
        double transfer_sending_amount = Model.getAmountAndInterest(
                transaction.getAmount(),
                MicrofinanceHelper.getMicrofinance(transaction.getSending_account().getCustomer().getMicrofinance().get_id()).getPourcentage_retrait_virement());

        AccountHelper.debitedAccount(transaction.getSending_account(), transfer_sending_amount);
        setTransaction(transaction);

    }

    public static void refundedTransaction(String transaction_id, Employee employee) throws Exception {
        Transaction transaction = getTransaction(transaction_id);
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.refunded);
        AccountHelper.creditedAccount(transaction.getSending_account(), transaction.getAmount());
        setTransaction(transaction);
    }

    public static void validedTransaction(String transaction_id, Employee employee) throws Exception {
        Transaction transaction = getTransaction(transaction_id);
        transaction.setValidator_employee(employee);
        Account sending_account = AccountHelper.getAccount(transaction.getSending_account().get_id());

        Account recipient_account = null;
        boolean recipient_account_exist = transaction.getRecipient_account() != null;
        if (recipient_account_exist)
        {
            recipient_account = AccountHelper.getAccount(transaction.getRecipient_account().get_id());
        }

        boolean recipient_is_saving = recipient_account != null && recipient_account.getAccount_type().equals(Status.AccountType.saving);

        if (transaction.getTransaction_status().equals(Status.TransactionStatus.advised) ||
            transaction.getTransaction_status().equals(Status.TransactionStatus.pending_validity) ||
            transaction.getTransaction_status().equals(Status.TransactionStatus.transferred))
        {
            if (transaction.getTransaction_type().equals(Status.TransactionType.deposit))
            {
                AccountHelper.creditedAccount(sending_account, transaction.getAmount());
            }

            else if (transaction.getTransaction_type().equals(Status.TransactionType.withdrawal))
            {
                double withdrawal_current_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        MicrofinanceHelper.getMicrofinance(transaction.getSending_account().getCustomer().getMicrofinance().get_id()).getPourcentage_retrait());

                double withdrawal_saving_before_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        MicrofinanceHelper.getMicrofinance(transaction.getSending_account().getCustomer().getMicrofinance().get_id()).getPourcentage_retrait_epargne_sans_avis());

                if (sending_account.getAccount_type().equals(Status.AccountType.saving))
                {
                    Date date = Model.ConvertToDate(transaction.getTransaction_date());

                    date.setDate(date.getDate() + transaction.getNumber_day_waiting());

                    if( date.compareTo(Model.currentDate()) >= 0 )
                    {
                        AccountHelper.debitedAccount(sending_account, transaction.getAmount());
                    }
                    else
                    {
                        AccountHelper.debitedAccount(sending_account, withdrawal_saving_before_amount);
                    }
                }

                else if (sending_account.getAccount_type().equals(Status.AccountType.current))
                {
                    AccountHelper.debitedAccount(sending_account, withdrawal_current_amount);
                }
            }

            else if (transaction.getTransaction_type().equals(Status.TransactionType.transfer))
            {
                double transfer_sending_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        MicrofinanceHelper.getMicrofinance(transaction.getSending_account().getCustomer().getMicrofinance().get_id()).getPourcentage_retrait_virement());

                if (recipient_account != null)
                {
                    if (recipient_is_saving)
                    {
                        AccountHelper.debitedAccount(sending_account, transfer_sending_amount);
                        AccountHelper.creditedAccount(recipient_account, transaction.getAmount());
                    }
                    else
                    {
                        if((recipient_account.getBalance() + transaction.getAmount()) <=
                                MicrofinanceHelper.getMicrofinance(recipient_account.getCustomer().getMicrofinance().get_id()).getMontant_maximum_solde_compte_courant())
                        {
                            AccountHelper.debitedAccount(sending_account, transfer_sending_amount);
                            AccountHelper.creditedAccount(recipient_account, transaction.getAmount());
                        }
                        else
                        {
                            throw new Exception("Le solde final ne doit pas etre superieur a " +
                                    MicrofinanceHelper.getMicrofinance(recipient_account.getCustomer().getMicrofinance().get_id()).getMontant_maximum_solde_compte_courant());
                        }
                    }
                }
            }
        }
        transaction.setTransaction_status(Status.TransactionStatus.valided);
        setTransaction(transaction);
    }

    // --- DELETE ---

    public static void deleteTransaction( String _id ){
        new DB<Transaction>(new Transaction()).removeObject(_id);
    }
}
