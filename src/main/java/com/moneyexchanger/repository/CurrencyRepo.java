package com.moneyexchanger.repository;

import com.moneyexchanger.enitiy.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency,String> {
}
