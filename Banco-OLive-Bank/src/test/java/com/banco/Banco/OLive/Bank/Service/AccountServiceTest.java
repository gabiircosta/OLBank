package com.banco.Banco.OLive.Bank.Service;

import com.banco.Banco.OLive.Bank.Model.Account;
import com.banco.Banco.OLive.Bank.Repository.AccountRepository;
import com.banco.Banco.OLive.Bank.Service.Exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Iti";
    public static final double BALANCE = 10.0;
    public static final int INDEX = 0;

    @InjectMocks //instancia real
    private AccountService accountService;

    @Mock //instancia 'fake'
    private AccountRepository accountRepository;

    private Account account;

    private Optional<Account> accountOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAccount();
    }

    @Test
    void WHEN_CallSaveMethod_THEN_SaveANewAccount() {

    }

    @Test
    void WHEN_CallListAllMethod_THEN_ReturnAListOfAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<Account> response = accountService.listAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Account.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(BALANCE, response.get(INDEX).getBalance());
    }

    @Test
    void WHEN_CallFindByIdMethod_THEN_ReturnAnAccountInstance() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));

        Account response = accountService.findById(ID);

        assertNotNull(response);
        assertEquals(Account.class, response.getClass()); //(o que se espera, o atual)
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BALANCE, response.getBalance());
    }

    @Test
    void WHEN_CallFindByIdMethod_THEN_ReturnANotFoundObject() {
        when(accountRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Account Not Found"));

        try{
            accountService.findById(ID);
        } catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Account Not Found", e.getMessage());
        }
    }

    @Test
    void deposit() {
    }

    @Test
    void withdrawal() {
    }

    // Método pra startat a account

    private void startAccount(){
        account = new Account(ID, NAME, BALANCE);
        accountOptional = Optional.of(new Account(ID, NAME, BALANCE));
    }
}
