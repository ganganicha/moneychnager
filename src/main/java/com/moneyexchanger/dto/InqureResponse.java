package com.moneyexchanger.dto;

import com.moneyexchanger.enitiy.Currency;

import javax.xml.ws.Response;

import java.util.List;

public class InqureResponse extends ResponseDTO {


    private String txnType;
    private Currency currency;
    private float receivedAmmount;
    private float issuedAmount;
    private float exchangeRate;
    private List<SuggestValue> suggestionValues;

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getReceivedAmmount() {
        return receivedAmmount;
    }

    public void setReceivedAmmount(float receivedAmmount) {
        this.receivedAmmount = receivedAmmount;
    }

    public float getIssuedAmount() {
        return issuedAmount;
    }

    public void setIssuedAmount(float issuedAmount) {
        this.issuedAmount = issuedAmount;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<SuggestValue> getSuggestionValues() {
        return suggestionValues;
    }

    public void setSuggestionValues(List<SuggestValue> suggestionValues) {
        this.suggestionValues = suggestionValues;
    }
}
