package com.demo.wallet.Models.Repositories;

import com.demo.wallet.Models.Entities.WalletEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEO,Long> {
    @Query(nativeQuery = true, value = "select * from wallet where transaction_reference = ?")
    Optional<WalletEO> getTransactionByRef(Long txnRef);

    @Query(nativeQuery = true, value = "select ifnull(sum(amount),0.00) from wallet where account_id = ?")
    BigDecimal getBalance(Long accountId);

    @Query(nativeQuery = true, value = "select * from wallet where account_id = ?")
    List<WalletEO> getTransactionsForPlayer(Long accountId);
}
