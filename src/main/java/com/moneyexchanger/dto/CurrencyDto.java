package com.moneyexchanger.dto;

import javax.persistence.*;

public class CurrencyDto {

    String currencyId;
    String name;
    String availableUnits;
    float buyExchangeRate;
    float sellExchangeRate;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(String availableUnits) {
        this.availableUnits = availableUnits;
    }

    public float getBuyExchangeRate() {
        return buyExchangeRate;
    }

    public void setBuyExchangeRate(float buyExchangeRate) {
        this.buyExchangeRate = buyExchangeRate;
    }

    public float getSellExchangeRate() {
        return sellExchangeRate;
    }

    public void setSellExchangeRate(float sellExchangeRate) {
        this.sellExchangeRate = sellExchangeRate;
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "currencyId='" + currencyId + '\'' +
                ", name='" + name + '\'' +
                ", availableUnits='" + availableUnits + '\'' +
                ", buyExchangeRate=" + buyExchangeRate +
                ", sellExchangeRate=" + sellExchangeRate +
                '}';
    }
}
