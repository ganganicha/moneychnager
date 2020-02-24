package com.moneyexchanger.dto;

public class SuggestValue {

    private float exchangedAmount;
    private float receivedAmout;

    public SuggestValue(float exchangedAmount, float receivedAmout) {
        this.exchangedAmount = exchangedAmount;
        this.receivedAmout = receivedAmout;
    }

    public float getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(float exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public float getReceivedAmout() {
        return receivedAmout;
    }

    public void setReceivedAmout(float receivedAmout) {
        this.receivedAmout = receivedAmout;
    }

    @Override
    public String toString() {
        return "SuggestValue{" +
                ", exchangedAmount=" + exchangedAmount +
                ", receivedAmout=" + receivedAmout +
                '}';
    }
}
