package com.demo.wallet.Models.Repositories;

import com.demo.wallet.Models.Entities.AccountEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEO,Long> {
    Optional<AccountEO> getByPlayerName(String name);
}
