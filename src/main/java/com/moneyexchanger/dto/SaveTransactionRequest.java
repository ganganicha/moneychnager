package com.moneyexchanger.dto;

public class SaveTransactionRequest {

    String currencyId;
    float receivedAmout;
    float exchangedAmount;
    int branchId;
    String transactionType;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public float getReceivedAmout() {
        return receivedAmout;
    }

    public void setReceivedAmout(float receivedAmout) {
        this.receivedAmout = receivedAmout;
    }

    public float getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(float exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "SaveTransactionRequest{" +
                "currencyId='" + currencyId + '\'' +
                ", receivedAmout=" + receivedAmout +
                ", exchangedAmount=" + exchangedAmount +
                ", branchId=" + branchId +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
