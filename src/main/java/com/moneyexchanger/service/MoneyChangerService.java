package com.moneyexchanger.service;

import com.moneyexchanger.dto.*;
import com.moneyexchanger.enitiy.Branch;
import com.moneyexchanger.enitiy.Currency;
import com.moneyexchanger.enitiy.Transaction;
import com.moneyexchanger.exception.CustomException;
import com.moneyexchanger.repository.BranchRepo;
import com.moneyexchanger.repository.CurrencyRepo;
import com.moneyexchanger.repository.TransactionRepo;
import com.moneyexchanger.util.Constans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MoneyChangerService {

    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    MessageSource messageSource;

    @Autowired
    BranchRepo branchRepo;

    @Autowired
    TransactionRepo transactionRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyChangerService.class);

    public ExchangeRateResponse getExchangeRate(){
        LOGGER.debug("Starting to excute getExchangeRate() ");
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        List<CurrencyDto> currencyList= new ArrayList<>();
        LOGGER.debug("Waiting for currencies");
        Iterable<Currency> currencies = currencyRepo.findAll();
        LOGGER.info("CurrencyDto list found :  {} ",currencies.toString());
        for (Currency currency: currencies) {
            CurrencyDto currencyDto = new CurrencyDto();
            BeanUtils.copyProperties(currency,currencyDto);
            if(currency.getCurrencyExchangeRate()!=null) {
                currencyDto.setSellExchangeRate(currency.getCurrencyExchangeRate().getSellingExcRate());
                currencyDto.setBuyExchangeRate(currency.getCurrencyExchangeRate().getBuyingExcRate());
            }
            currencyList.add(currencyDto);
        }
        exchangeRateResponse.setCurrencies(currencyList);
        LOGGER.debug("Ending getExchangeRate() ");
        return exchangeRateResponse;
    }

    public Currency exchangeRateValidation(String currencyId) throws CustomException {
        LOGGER.info("Finding relevant currency for currencyId : {}  ", currencyId);
        if (currencyRepo.findById(currencyId).isPresent()) {
            Currency currency = currencyRepo.findById(currencyId).get();
            if (currency.getCurrencyExchangeRate() != null) {
                LOGGER.info("Currency Details found : {} ",currency.toString());
                return currency;
            } else {
                throw new CustomException(Constans.CURRANCY_NOT_FOUND,
                        messageSource.getMessage(
                                String.valueOf(Constans.CURRANCY_NOT_FOUND),
                                new Object[]{},
                                LocaleContextHolder.getLocale()));
            }
        } else {
            throw new CustomException(Constans.CURRANCY_NOT_FOUND,
                    messageSource.getMessage(
                            String.valueOf(Constans.CURRANCY_NOT_FOUND),
                            new Object[]{},
                            LocaleContextHolder.getLocale()));
        }
    }

    public InqureResponse buyCurrency(String currencyId, int branch, float receivedAmount) throws CustomException {
        InqureResponse inqureResponse = new InqureResponse();
        Currency currency = exchangeRateValidation(currencyId);
        float issuedAmount = receivedAmount * currency.getCurrencyExchangeRate().getBuyingExcRate();
        LOGGER.info("Exact Issued amount : {} " ,issuedAmount);
        Branch branchDetails = branchRepo.getBranchBalanceForCurrency(branch, currencyId);
        float branchCurrentBalance = branchDetails.getAvailableBalance();
        if (branchCurrentBalance < issuedAmount) {
            LOGGER.info("Branch balance is not enough for the transaction. Branch balance : {} " ,branchCurrentBalance);
            float receivableAmount = suggestBuyCurrency(branchCurrentBalance,
                    currency.getCurrencyExchangeRate().getBuyingExcRate());
            List<SuggestValue> suggestValues = new ArrayList<>();
            inqureResponse.setMessage(messageSource.getMessage(String.valueOf(Constans.CURRANCY_NOT_FOUND),
                    new Object[]{}, LocaleContextHolder.getLocale()));
            LOGGER.info("Stated to create a suggestion vale");
            inqureResponse.setStatusCode(Constans.NOT_ENOUGH_BALANCE);
            SuggestValue suggestValue = new SuggestValue(branchCurrentBalance,receivableAmount);
            LOGGER.info("suggestion vale created successfully : {} " ,suggestValue.toString());
            suggestValues.add(suggestValue);
            inqureResponse.setSuggestionValues(suggestValues);
        }
        inqureResponse.setCurrency(currency);
        inqureResponse.setExchangeRate(currency.getCurrencyExchangeRate().getBuyingExcRate());
        inqureResponse.setReceivedAmmount(receivedAmount);
        inqureResponse.setIssuedAmount(issuedAmount);
        inqureResponse.setTxnType("BUY");
        LOGGER.info("inqureResponse created Successfully : {} " , inqureResponse.toString());
        return inqureResponse;
    }

    public InqureResponse sellCurrency(String currencyId, int branch, float receivedAmount) throws CustomException {
        InqureResponse inqureResponse = new InqureResponse();
        Currency currency = exchangeRateValidation(currencyId);
        float issuedAmount = receivedAmount / currency.getCurrencyExchangeRate().getSellingExcRate();
        Branch branchDetails = branchRepo.getBranchBalanceForCurrency(branch, currencyId);
        float branchCurrentBalance = branchDetails.getAvailableBalance();
        float exchangeRate = currency.getCurrencyExchangeRate().getSellingExcRate();
        if (branchCurrentBalance < issuedAmount) {
            float receivableAmount = suggestSellCurrency(branchCurrentBalance,exchangeRate );
            List<SuggestValue> suggestValues = new ArrayList<>();
            inqureResponse.setMessage(messageSource.getMessage(String.valueOf(Constans.CURRANCY_NOT_FOUND),
                    new Object[]{}, LocaleContextHolder.getLocale()));
            inqureResponse.setStatusCode(Constans.NOT_ENOUGH_BALANCE);
            SuggestValue suggestValue = new SuggestValue(branchCurrentBalance, receivableAmount);
            suggestValues.add(suggestValue);
            inqureResponse.setSuggestionValues(suggestValues);
        } else {
            List<String> currencyNotes = Arrays.asList(currency.getAvailableUnits().split("\\,"));
            float value = issuedAmount;
            for (String note : currencyNotes) {
                value = value % Integer.parseInt(note);
            }
            float misingvalue = Integer.parseInt(currencyNotes.get(currencyNotes.size() - 1)) - value;
            float suggestedissuedLowerAmount = issuedAmount-value;
            SuggestValue suggestLowerValue = new SuggestValue(suggestedissuedLowerAmount,
                    suggestSellCurrency(suggestedissuedLowerAmount,exchangeRate));
            float suggestedissuedUpperAmount = issuedAmount + misingvalue;
            SuggestValue suggestUpperValue = new SuggestValue(suggestedissuedUpperAmount,
                    suggestSellCurrency(suggestedissuedUpperAmount,exchangeRate));
            if(inqureResponse.getSuggestionValues()!= null){
                inqureResponse.getSuggestionValues().add(suggestUpperValue);
                inqureResponse.getSuggestionValues().add(suggestLowerValue);
            }else{
                List<SuggestValue> suggestValues = new ArrayList<>();
                suggestValues.add(suggestLowerValue);
                suggestValues.add(suggestUpperValue);
                inqureResponse.setSuggestionValues(suggestValues);
            }
        }
        inqureResponse.setCurrency(currency);
        inqureResponse.setExchangeRate(currency.getCurrencyExchangeRate().getSellingExcRate());
        inqureResponse.setReceivedAmmount(receivedAmount);
        inqureResponse.setIssuedAmount(issuedAmount);
        inqureResponse.setTxnType("BUY");
        return inqureResponse;

    }

    public float suggestBuyCurrency(float amount, float exchangeRate) {
        return amount / exchangeRate;
    }

    public float suggestSellCurrency(float amount, float exchangeRate) {
        DecimalFormat df = new DecimalFormat("###.#");
        return Float.parseFloat(df.format(amount * exchangeRate));
    }

    public TransactionDto saveTransaction(SaveTransactionRequest transactionRequest){
        Transaction transaction = new Transaction();
        LOGGER.info("Calling to saveTransaction : {} " , transactionRequest.toString());
        Branch branch = branchRepo.getBranchBalanceForCurrency(transactionRequest.getBranchId(),
                transactionRequest.getCurrencyId());
        if(branch!=null){
            transaction.setBranch(branch);
        }
        transaction.setCurrency(exchangeRateValidation(transactionRequest.getCurrencyId()));
        transaction.setReceivedAmount(transactionRequest.getReceivedAmout());
        transaction.setIsuedAmount(transactionRequest.getExchangedAmount());
        transaction.setTransactionDate(new Date());
        transaction.setTxnType(transactionRequest.getTransactionType());
        transaction.setTransactionId(transactionRepo.getTransactionId());
        LOGGER.info("Transaction object cerated : {}" ,transaction.toString());
        transactionRepo.save(transaction);
        TransactionDto transactionDto = new TransactionDto();
        LOGGER.info("Transaction object saved successfully  : {}" ,transaction.toString());
        BeanUtils.copyProperties(transaction,transactionDto);
        return transactionDto;
    }
}
