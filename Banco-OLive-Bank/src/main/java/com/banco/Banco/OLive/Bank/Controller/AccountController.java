package com.banco.Banco.OLive.Bank.Controller;

import com.banco.Banco.OLive.Bank.Model.Account;
import com.banco.Banco.OLive.Bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> listAll(){
        return new ResponseEntity<>(accountService.listAll(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account){
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping ("/deposit")
    public ResponseEntity<Account> deposit(@RequestParam Long id, double amountToDeposit){
        return new ResponseEntity<>(accountService.deposit(id, amountToDeposit), HttpStatus.OK);
    }

    @PutMapping ("/withdrawal")
    public ResponseEntity<Account> withdrawal(@RequestParam Long id, double amountToWithdrawal){
        return new ResponseEntity<>(accountService.withdrawal(id, amountToWithdrawal), HttpStatus.OK);
    }
}
