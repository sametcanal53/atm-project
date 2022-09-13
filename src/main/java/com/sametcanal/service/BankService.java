package com.sametcanal.service;

import com.sametcanal.controller.request.BankRequest;
import com.sametcanal.controller.request.CustomerRequest;
import com.sametcanal.model.Bank;
import com.sametcanal.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService {

    List<Bank> getAllBanks();
    ResponseEntity<Bank> getBankById(Long id);
    Bank createBank(BankRequest bankRequest);
    ResponseEntity<Bank> updateBank(BankRequest bankRequest);
    Boolean deleteBank(Long id);


    List<Customer> getAllCustomers();
    Customer createCustomer(CustomerRequest customerRequest);
    ResponseEntity<Customer> getCustomerById(Long id);
    ResponseEntity<Customer> updateCustomer(CustomerRequest customerRequest);
    Boolean deleteCustomer(Long id);
}
