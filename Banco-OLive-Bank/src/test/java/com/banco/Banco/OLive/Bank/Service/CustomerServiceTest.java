package com.banco.Banco.OLive.Bank.Service;

import com.banco.Banco.OLive.Bank.Model.Customer;
import com.banco.Banco.OLive.Bank.Repository.CustomerRepository;
import com.banco.Banco.OLive.Bank.Service.Exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "NAME_MOCK";
    public static final String CPF = "CPF_MOCK";

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    private Optional<Customer> customerOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    void WHEN_CallSaveMethod_THEN_SaveAndReturnACustomerInstance() {
        when(customerRepository.save(any())).thenReturn(customer);

        Customer response = customerService.save(customer);

        assertNotNull(response);
        assertEquals(Customer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
    }

    @Test
    void WHEN_CallFindByIdMethod_THEN_ReturnACustomerInstance() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        Customer response = customerService.findById(ID);

        assertNotNull(response);
        assertEquals(Customer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
    }

    @Test
    void WHEN_CallFindByIdMethod_THEN_ReturnAObjectNotFoundException() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        try{
            customerService.findById(ID);
        } catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Account Not Found", e.getMessage());
        }
    }

    @Test
    void WHEN_CallFindAllMethod_THEN_ReturnAListOfAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> response = customerService.findAll();

        assertNotNull(response);
        assertEquals(Customer.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(CPF, response.get(0).getCpf());
    }

    public void startCustomer(){
        customer = new Customer(ID, NAME, CPF);
        customerOptional = Optional.of(new Customer(ID, NAME, CPF));
    }
}