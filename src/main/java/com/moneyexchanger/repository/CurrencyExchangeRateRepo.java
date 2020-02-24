package com.moneyexchanger.repository;
import com.moneyexchanger.enitiy.CurrencyExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRateRepo extends CrudRepository<CurrencyExchangeRate,Integer> {
}
