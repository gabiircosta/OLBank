package com.banco.Banco.OLive.Bank.Repository;

import com.banco.Banco.OLive.Bank.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
