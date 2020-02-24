package com.moneyexchanger.service;
import com.moneyexchanger.dto.ExchangeRateResponse;
import com.moneyexchanger.dto.SaveTransactionRequest;
import com.moneyexchanger.enitiy.Branch;
import com.moneyexchanger.enitiy.Currency;
import com.moneyexchanger.enitiy.CurrencyExchangeRate;
import com.moneyexchanger.repository.BranchRepo;
import com.moneyexchanger.repository.CurrencyRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MoneyChangerTest {

    @Mock
    CurrencyRepo currencyRepo;

    @Mock
    CurrencyRepo TransRepo;

    @Mock
    BranchRepo branchRepo;

    @InjectMocks
    private MoneyChangerService moneyChangerService;



    @Test
    public void getExchangeDetails() {
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
        ExchangeRateResponse exchangeRateResponse = moneyChangerService.getExchangeRate();
        Assert.isTrue(exchangeRateResponse!=null);
    }

//    @Test
////    public void saveTransaction() {
////
////        Currency currency = new Currency();
////        currency.setAvailableUnits("20,30");
////        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
////        currencyExchangeRate.setCurrencyExchangeId(1);
////        currencyExchangeRate.setBuyingExcRate(1);
////        currencyExchangeRate.setSellingExcRate(1);
////        currency.setCurrencyExchangeRate(currencyExchangeRate);
////        currency.setCurrencyId("sgq");
////        currency.setName("sgd");
////
////        SaveTransactionRequest saveTransactionRequest = new SaveTransactionRequest();
////        saveTransactionRequest.setBranchId(1);
////        saveTransactionRequest.setCurrencyId("sgd");
////        saveTransactionRequest.setExchangedAmount(12.40f);
////        saveTransactionRequest.setReceivedAmout(12.40f);
////        saveTransactionRequest.setTransactionType("BUY");
////        when(moneyChangerService.exchangeRateValidation(Mockito.any(String.class))).thenReturn(currency);
////
////        Branch branch = new Branch();
////        when(branchRepo.getBranchBalanceForCurrency(Mockito.any(Integer.class),Mockito.any(String.class))).thenReturn(branch);
////
////        when(currencyRepo.findAll()).thenReturn(currencies);
////        when(currencyRepo.findAll()).thenReturn(currencies);
////        ExchangeRateResponse exchangeRateResponse = moneyChangerService.getExchangeRate();
////        Assert.isTrue(exchangeRateResponse!=null);
////    }
////
////
////        transaction.setCurrency(exchangeRateValidation(transactionRequest.getCurrencyId()));
////        transaction.setReceivedAmount(transactionRequest.getReceivedAmout());
////        transaction.setIsuedAmount(transactionRequest.getExchangedAmount());
////        transaction.setTransactionDate(new Date());
////        transaction.setTxnType(transactionRequest.getTransactionType());
////        transaction.setTransactionId(transactionRepo.getTransactionId());
////        LOGGER.info("Transaction object cerated : {}" ,transaction.toString());
////        transactionRepo.save(transaction);
////    TransactionDto transactionDto = new TransactionDto();
////        LOGGER.info("Transaction object saved successfully  : {}" ,transaction.toString());
////        BeanUtils.copyProperties(transaction,transactionDto);
////        return transactionDto;

}