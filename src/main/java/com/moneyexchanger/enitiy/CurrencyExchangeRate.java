package com.moneyexchanger.enitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency_exchange_rates")
public class CurrencyExchangeRate {

    @Id
    @Column(name = "currency_exchange_Id")
    int currencyExchangeId;

    @Column(name = "buying_Exc_Rate")
    float buyingExcRate;

    @Column(name = "selling_Exc_Rate")
    float sellingExcRate;

    public int getCurrencyExchangeId() {
        return currencyExchangeId;
    }

    public void setCurrencyExchangeId(int currencyExchangeId) {
        this.currencyExchangeId = currencyExchangeId;
    }

    public float getBuyingExcRate() {
        return buyingExcRate;
    }

    public void setBuyingExcRate(float buyingExcRate) {
        this.buyingExcRate = buyingExcRate;
    }

    public float getSellingExcRate() {
        return sellingExcRate;
    }

    public void setSellingExcRate(float sellingExcRate) {
        this.sellingExcRate = sellingExcRate;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRate{" +
                "currencyExchangeId=" + currencyExchangeId +
                ", buyingExcRate=" + buyingExcRate +
                ", sellingExcRate=" + sellingExcRate +
                '}';
    }
}
