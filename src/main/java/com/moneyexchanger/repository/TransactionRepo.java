package com.moneyexchanger.repository;
import com.moneyexchanger.enitiy.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction,Long> {

    @Query(value ="SELECT  nvl(max(TRANSACTION_ID),0) +1  FROM TRANSACTION ", nativeQuery = true)
    public Long getTransactionId();
}
