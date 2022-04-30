package com.banco.Banco.OLive.Bank.Service;

import com.banco.Banco.OLive.Bank.Model.Customer;
import com.banco.Banco.OLive.Bank.Repository.CustomerRepository;
import com.banco.Banco.OLive.Bank.Service.Exception.ObjectNotFoundException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CustomerService {

    @Autowired
    public CustomerRepository repository;

    public Customer save(Customer customer){
        log.info("Saving a costumer");

        return repository.save(customer);
    }

    public Customer findById(Long id){
        log.info("Finding a customer by it's id");

        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Account Not Found"));
    }

    public List<Customer> findAll(){
        log.info("Listing all customer on database");

        return repository.findAll();
    }

}
