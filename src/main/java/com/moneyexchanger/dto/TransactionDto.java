package com.moneyexchanger.dto;

import com.moneyexchanger.enitiy.Branch;
import com.moneyexchanger.enitiy.Currency;

import javax.persistence.*;
import java.util.Date;

public class TransactionDto extends ResponseDTO{


    private long transactionId;
    private Currency currency;
    private float receivedAmount;
    private float IsuedAmount;
    private Date transactionDate;
    private Branch branch;
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
