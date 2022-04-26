package com.banco.Banco.OLive.Bank.Repository;

import com.banco.Banco.OLive.Bank.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
