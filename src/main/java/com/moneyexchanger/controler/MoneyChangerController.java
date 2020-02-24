package com.moneyexchanger.controler;

import com.moneyexchanger.dto.ExchangeRateResponse;
import com.moneyexchanger.dto.InqureResponse;
import com.moneyexchanger.dto.SaveTransactionRequest;
import com.moneyexchanger.dto.TransactionDto;
import com.moneyexchanger.exception.CustomException;
import com.moneyexchanger.service.MoneyChangerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MoneyChangerController {

    @Autowired
    MoneyChangerService moneyChangerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyChangerController.class);

    @GetMapping(
            value = "/get-exchange-rate",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ExchangeRateResponse> getExchangeRates() {
        Date startTime = new Date();
        LOGGER.info("Starting to call /get-exchange-rate API ");
        ExchangeRateResponse txnCurrMap = moneyChangerService.getExchangeRate();
        LOGGER.info("Starting to call /get-exchange-rate API ");
        LOGGER.info("End of  /get-exchange-rate API.");
        LOGGER.info("Processed time for /get-exchange-rate API : {} ms", new Date().getTime() - startTime.getTime());
        return ResponseEntity.status(HttpStatus.OK).body(txnCurrMap);
    }

    @GetMapping(
            value = "/get-inquiry-details/BUY/branch/{branch}/currency/{currency}/amount/{amount}",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InqureResponse> buyForigenCurrency(@PathVariable String currency, @PathVariable float amount,
                                                      @PathVariable int branch) throws CustomException {
        InqureResponse txnDto = moneyChangerService.buyCurrency(currency, branch, amount);
        return ResponseEntity.status(HttpStatus.OK).body(txnDto);
    }

    @GetMapping(
            value = "/get-inquiry-details/SELL/branch/{branch}/currency/{currency}/amount/{amount}",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InqureResponse> sellForigenCurrency(@PathVariable String currency, @PathVariable float amount,
                                                       @PathVariable int branch) throws CustomException {
        InqureResponse txnDto = moneyChangerService.sellCurrency(currency, branch, amount);
        return ResponseEntity.status(HttpStatus.OK).body(txnDto);
    }


    @PostMapping(
            value = "/saveTransaction",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TransactionDto> submitAgentInfo(@RequestBody SaveTransactionRequest transactionRequest) {
        TransactionDto transaction = null;
        try {
            transaction = moneyChangerService.saveTransaction(transactionRequest);
        } catch (Exception e) {
            transaction.setMessage("Error Inserting Transation");
        }
        return ResponseEntity.ok().body(transaction);
    }
}
