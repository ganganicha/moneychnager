package com.moneyexchanger.service;

import com.moneyexchanger.dto.ExchangeRateResponse;
import com.moneyexchanger.enitiy.Currency;
import com.moneyexchanger.enitiy.CurrencyExchangeRate;
import com.moneyexchanger.repository.CurrencyRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MoneyChangerServiceTest {

    @Mock
    CurrencyRepo currencyRepo;

    @InjectMocks
    private MoneyChangerService moneyChangerService;


    @Test
    public void name() {
//        when(locationRepo.save(Mockito.any(TransactionLocation.class))).thenReturn(new TransactionLocation());
//        transactionLocationService.saveTransactionLocation(new RequestDto(),123);

    }


    @Test
    public void getExchangeRate() {

        Currency currency = new Currency();
        currency.setAvailableUnits("20,30");
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
        currencyExchangeRate.setCurrencyExchangeId(1);
        currencyExchangeRate.setBuyingExcRate(1);
        currencyExchangeRate.setSellingExcRate(1);
        currency.setCurrencyExchangeRate(currencyExchangeRate);
        currency.setCurrencyId("sgq");
        currency.setName("sgd");
        List<Currency> currencies = new ArrayList<>();
        currencies.add(currency);
        currencies.add(currency);
        when(currencyRepo.findAll()).thenReturn(currencies);
        ExchangeRateResponse currencyList = moneyChangerService.getExchangeRate();
    }


}