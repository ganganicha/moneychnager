package com.moneyexchanger.repository;

import com.moneyexchanger.enitiy.Branch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends CrudRepository<Branch,Integer> {

    @Query(value ="SELECT *  FROM BRANCH where BRANCH_ID =:branchId and CURRENCY_ID =:currencyId", nativeQuery = true)
    public Branch getBranchBalanceForCurrency(@Param("branchId") int branchId, @Param("currencyId") String currencyId);

    @Query(value ="SELECT * FROM BRANCH where BRANCH_ID =:branchId", nativeQuery = true)
    public Branch getBranchByBranchId(@Param("branchId") int branchId);

}
