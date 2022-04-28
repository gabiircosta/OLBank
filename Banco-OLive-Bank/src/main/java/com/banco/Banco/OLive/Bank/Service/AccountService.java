package com.banco.Banco.OLive.Bank.Service;

import com.banco.Banco.OLive.Bank.Model.Account;
import com.banco.Banco.OLive.Bank.Repository.AccountRepository;
import com.banco.Banco.OLive.Bank.Service.Exception.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        log.info("Saving an account");

        if (account.getBalance() < 0){
            System.out.println("We can't create an account with no balance");
            return null;
        } else return accountRepository.save(account);
    }

    public List<Account> listAll(){
        log.info("Listing all accounts on database");

        return accountRepository.findAll();
    }

    public Account findById(Long id){
        log.info("Finding an account by it's id");

        return accountRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Account Not Found"));
    }

    public Account deposit (Long id, double amountToDeposit){
        log.info("Depositing money");

        Optional<Account> existingAccount = accountRepository.findById(id);

        if(id.equals(existingAccount.get().getId())){
            Double balance = existingAccount.get().getBalance();

            if(amountToDeposit <= 0){
                System.out.println("We can't deposit values under or equals zero");
            }

            if (amountToDeposit > 0){
                balance += amountToDeposit;
                existingAccount.get().setBalance(balance);
            }

            return accountRepository.save(existingAccount.get());
        }
        else return null;
    }

    public Account withdrawal (Long id, double amountToWithdrawal){
        log.info("Withdrawing money");

        Optional<Account> existingAccount = accountRepository.findById(id);

        if(id.equals(existingAccount.get().getId())){
            double balance = existingAccount.get().getBalance();

            if(balance < amountToWithdrawal){
                System.out.println("We can't withdrawal amount higher than the balance");
            }

            else {
                balance -= amountToWithdrawal;
                existingAccount.get().setBalance(balance);
            }

            return accountRepository.save(existingAccount.get());
        }
        else return null;
    }

}
