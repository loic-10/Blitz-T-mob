package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.Models.Transaction.Transaction;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.Date;

public class TransactionHelper extends DB<Transaction> {

    static CustomerHelper sCustomerHelper = new CustomerHelper(new Customer());

    static AccountHelper sAccountHelper = new AccountHelper(new Account());

    static MicrofinanceHelper sMicrofinanceHelper = new MicrofinanceHelper(new Microfinance());

    public TransactionHelper ( Transaction data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setTransaction( Transaction transaction) throws Exception {
        try {
            setObject(transaction, transaction.get_id());
        }
        catch (Exception ex){
            throw new Exception("Erreur lors de la sauvegarde des donnees !");
//            Resources.getSystem().getString(R.string.);
        }
    }

    // --- GET ---

    public DatabaseReference getTransactions(){
        return getReference();
    }

    public Transaction getTransaction( String _id)  {
        ArrayList<Transaction> transactions = new ArrayList<>();
        getReferenceObject(_id, transactions , new Object());
        return transactions.get(0);
    }

    public Transaction thisTransaction(ArrayList<Transaction> transactions, String _id){
        for (Transaction transaction : transactions) {
            if(transaction.get_id().equals(_id)){
                return transaction;
            }
        }
        return null;
    }

    public void completedTransactions(ArrayList<Transaction> transactions){
        getObjects(transactions);
    }

    //

    public void canceledTransaction(Transaction transaction, Employee employee) throws Exception {
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.canceled);
        setTransaction(transaction);
    }

    public void transferredTransaction(Transaction transaction, Employee employee, ArrayList<Saving> savings, Microfinance microfinance) throws Exception {
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.transferred);
        double transfer_sending_amount = Model.getAmountAndInterest(
                transaction.getAmount(),
                microfinance.getPourcentage_retrait_virement());

        sAccountHelper.debitedAccount(transaction.getSending_account(), transfer_sending_amount, savings);

        setTransaction(transaction);

    }

    public void refundedTransaction(Transaction transaction, Employee employee, ArrayList<Saving> savings, Microfinance microfinance) throws Exception {
        transaction.setValidator_employee(employee);
        transaction.setTransaction_status(Status.TransactionStatus.refunded);
        sAccountHelper.creditedAccount(transaction.getSending_account(), transaction.getAmount(), savings, microfinance);
        setTransaction(transaction);
    }

    public void validedTransaction( Transaction transaction, Employee employee, Microfinance microfinance, ArrayList<Saving> savings) throws Exception {
        transaction.setValidator_employee(employee);
        Account sending_account = transaction.getSending_account();

        Account recipient_account = null;

        boolean recipient_account_exist = transaction.getRecipient_account() != null;
        if (recipient_account_exist)
        {
            recipient_account = transaction.getRecipient_account();
        }

        boolean recipient_is_saving = recipient_account != null &&
                                    recipient_account.getAccount_type().equals(Status.AccountType.saving);

        if (transaction.getTransaction_status().equals(Status.TransactionStatus.advised) ||
            transaction.getTransaction_status().equals(Status.TransactionStatus.pending_validity) ||
            transaction.getTransaction_status().equals(Status.TransactionStatus.transferred))
        {
            if (transaction.getTransaction_type().equals(Status.TransactionType.deposit))
            {
                sAccountHelper.creditedAccount(sending_account, transaction.getAmount(), savings, microfinance);
            }

            else if (transaction.getTransaction_type().equals(Status.TransactionType.withdrawal))
            {
                double withdrawal_current_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        microfinance.getPourcentage_retrait());

                double withdrawal_saving_before_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        microfinance.getPourcentage_retrait_epargne_sans_avis());

                if (sending_account.getAccount_type().equals(Status.AccountType.saving))
                {
                    Date date = Model.ConvertToDate(transaction.getTransaction_date());

                    date.setDate(date.getDate() + transaction.getNumber_day_waiting());

                    if( date.compareTo(Model.currentDate()) >= 0 )
                    {
                        sAccountHelper.debitedAccount(sending_account, transaction.getAmount(), savings);
                    }
                    else
                    {
                        sAccountHelper.debitedAccount(sending_account, withdrawal_saving_before_amount, savings);
                    }
                }

                else if (sending_account.getAccount_type().equals(Status.AccountType.current))
                {
                    sAccountHelper.debitedAccount(sending_account, withdrawal_current_amount, savings);
                }
            }

            else if (transaction.getTransaction_type().equals(Status.TransactionType.transfer))
            {
                double transfer_sending_amount = Model.getAmountAndInterest(
                        transaction.getAmount(),
                        microfinance.getPourcentage_retrait_virement());

                if (recipient_account != null)
                {
                    if (recipient_is_saving)
                    {
                        sAccountHelper.debitedAccount(sending_account, transfer_sending_amount, savings);
                        sAccountHelper.creditedAccount(recipient_account, transaction.getAmount(), savings, microfinance);
                    }
                    else
                    {
                        if((recipient_account.getBalance() + transaction.getAmount()) <=
                                microfinance.getMontant_maximum_solde_compte_courant())
                        {
                            sAccountHelper.debitedAccount(sending_account, transfer_sending_amount, savings);
                            sAccountHelper.creditedAccount(recipient_account, transaction.getAmount(), savings, microfinance);
                        }
                        else
                        {
                            throw new Exception("Le solde final ne doit pas etre superieur a " +
                                    microfinance.getMontant_maximum_solde_compte_courant());
                        }
                    }
                }
            }
        }
        transaction.setTransaction_status(Status.TransactionStatus.valided);
        setTransaction(transaction);
    }

    // --- DELETE ---

    public void deleteTransaction( String _id ){
        removeObject(_id);
    }
}
