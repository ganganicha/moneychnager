package com.moneyexchanger.dto;

import java.util.List;

public class ExchangeRateResponse extends ResponseDTO{

    List<CurrencyDto> currencies;

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "currencies=" + currencies +
                '}';
    }
}
