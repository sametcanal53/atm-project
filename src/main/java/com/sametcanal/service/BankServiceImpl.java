package com.sametcanal.service;

import com.sametcanal.controller.request.BankRequest;
import com.sametcanal.controller.request.CustomerRequest;
import com.sametcanal.exception.AtmBusinessException;
import com.sametcanal.model.Bank;
import com.sametcanal.model.Customer;
import com.sametcanal.repository.BankRepository;
import com.sametcanal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl  implements BankService{
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;

    @Override
    public List<Bank> getAllBanks() {
        return this.bankRepository.findAll();
    }

    @Override
    public ResponseEntity<Bank> getBankById(Long id) {
        {
            if(!bankRepository.existsById(id)){
                throw new AtmBusinessException("ATM-1002","Bank Not Found", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(this.bankRepository.findById(id).orElse(null));
        }
    }

    @Override
    public Bank createBank(BankRequest bankRequest) {
        Bank bank = Bank.builder()
                .bankName(bankRequest.getBankName())
                .build();
        return this.bankRepository.save(bank);
    }

    @Override
    public ResponseEntity<Bank> updateBank(BankRequest bankRequest) {
        if(!bankRepository.existsById(bankRequest.getId())){
            throw new AtmBusinessException("ATM-1002","Bank Not Found", HttpStatus.NOT_FOUND);
        }
        Bank updateBank = bankRepository.findById(bankRequest.getId()).orElse(null);
        updateBank.setBankName(updateBank.getBankName());
        this.bankRepository.save(updateBank);
        return ResponseEntity.ok(updateBank);
    }

    @Override
    public Boolean deleteBank(Long id) {
        if(!bankRepository.existsById(id)){
            throw new AtmBusinessException("ATM-1002","Bank Not Found", HttpStatus.NOT_FOUND);
        }
        this.bankRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        if(!customerRepository.existsById(id)){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(this.customerRepository.findById(id).orElse(null));
    }


    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .customerPrice(customerRequest.getCustomerPrice())
                .bankId(customerRequest.getBankId())
                .build();
        return this.customerRepository.save(customer);
    }


    @Override
    public ResponseEntity<Customer> updateCustomer(CustomerRequest customerRequest) {
        if(!customerRepository.existsById(customerRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }
        Customer updateCustomer = customerRepository.findById(customerRequest.getId()).orElse(null);
        updateCustomer.setCustomerName(customerRequest.getCustomerName());
        updateCustomer.setCustomerPrice(customerRequest.getCustomerPrice());
        updateCustomer.setBankId(customerRequest.getBankId());
        this.customerRepository.save(updateCustomer);
        return ResponseEntity.ok(updateCustomer);
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        if(!customerRepository.existsById(id)){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }
        this.customerRepository.deleteById(id);
        return true;
    }
}
