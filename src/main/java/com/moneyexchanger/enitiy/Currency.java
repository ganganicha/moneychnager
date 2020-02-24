package com.moneyexchanger.enitiy;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "currency_Id")
    String currencyId;

    @Column(name = "name")
    String name;

    @Column(name = "available_units")
    String availableUnits;

    @OneToOne
    @JoinColumn(name = "currency_exchange_Id")
    CurrencyExchangeRate currencyExchangeRate;

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

    public CurrencyExchangeRate getCurrencyExchangeRate() {
        return currencyExchangeRate;
    }

    public void setCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId='" + currencyId + '\'' +
                ", name='" + name + '\'' +
                ", availableUnits='" + availableUnits + '\'' +
                ", currencyExchangeRate=" + currencyExchangeRate +
                '}';
    }
}
