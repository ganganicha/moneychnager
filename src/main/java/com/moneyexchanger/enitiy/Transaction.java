package com.moneyexchanger.enitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @Column(name = "Transaction_id")
    private long transactionId;

    @OneToOne
    @JoinColumn(name="currency_Id")
    private Currency currency;

    @Column(name = "received_amount")
    private float receivedAmount;

    @Column(name = "Issued_amount")
    private float IsuedAmount;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @OneToOne
    @JoinColumn(name = "branch_Id")
    private Branch branch;

    @Column(name = "txn_Type")
    private String txnType;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(float receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public float getIsuedAmount() {
        return IsuedAmount;
    }

    public void setIsuedAmount(float isuedAmount) {
        IsuedAmount = isuedAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", currency=" + currency +
                ", receivedAmount=" + receivedAmount +
                ", IsuedAmount=" + IsuedAmount +
                ", transactionDate=" + transactionDate +
                ", branch=" + branch +
                ", txnType='" + txnType + '\'' +
                '}';
    }
}
