package com.example.blitz_t.Models.Status;

public class Status {

    public enum EmployeeRole {
        agency_manager,
        credit_manager,
        accounting_secretary,
        cash_out,
        manager,
        accounting
    }

    public enum ConnectionStatus {
        online,
        offline
    }

    public enum AccountStatus {
        pending_validity,
        activated,
        disabled
    }

    public enum CustomerStatus {
        activated,
        disabled
    }

    public enum CreditStatus {
        pending_validity,
        refunded,
        not_refunded,
        programed,
        canceled,
        file_opening
    }

    public enum RefundStatus {
        pending_validity,
        canceled,
        valided,
        planned
    }

    public enum RefundPaymentStatus {
        on_time,
        late
    }

    public enum TransactionStatus {
        pending_validity,
        canceled,
        valided,
        transferred,
        advised,
        refunded
    }

    public enum TransactionType {
        deposit,
        withdrawal,
        transfer
    }

    public enum AccountType {
        saving,
        current
    }

    public enum CreditType {
        normal_credit,
        overdraft
    }

    public enum TypeSavingAccountOwner {
        company,
        particular
    }

    public enum AccountNumberStatus {
        debit,
        credit
    }

    public enum GuaranteeStatus {
        recorded,
        returned
    }

    public enum SocialPartStatus {
        completed,
        incompleted
    }

    public enum SalariedStatus {
        in_service,
        dismiss
    }

    public enum EmployeePaymentStatus {
        paid,
        payday,
        unpaid
    }

    public enum SavingStatus {
        completed,
        in_progress
    }

    public enum StorageStatus {
        guarantee,
        internship_request,
        microfinance_document,
        client_image,
        employee_image
    }

    public enum CaseActionType {
        entry,
        exit
    }

    public enum OperationType {
        deposit,
        withdrawal,
        transfer,
        credit,
        refund,
        recording,
        addition,
        editing,
        modification,
        deletion,
        entry,
        exit,
        withdrawal_notice,
        cancellation,
        hiring,
        guarantee_return,
        reduction_social_part_amount,
        validation,
        activation,
        deactivation,
        payment
    }

    public enum PossibilityStatus {
        possible,
        impossible,
        risked
    }
}
