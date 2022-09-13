package com.sametcanal.controller.api;

import com.sametcanal.controller.request.BankRequest;
import com.sametcanal.controller.request.CustomerRequest;
import com.sametcanal.exception.AtmBusinessException;
import com.sametcanal.model.Bank;
import com.sametcanal.model.Customer;
import com.sametcanal.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    // http://localhost:8080/api/bank/
    @GetMapping("/bank/")
    public List<Bank> getAllBanks() {
        return this.bankService.getAllBanks();
    }

    // http://localhost:8080/api/bank/1
    @GetMapping("/bank/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return this.bankService.getBankById(id);
    }

    // http://localhost:8080/api/bank/
    @PostMapping("/bank/")
    public Bank createBank(@RequestBody BankRequest bankRequest) {
        return this.bankService.createBank(bankRequest);
    }

    // http://localhost:8080/api/bank/1
    @PatchMapping("/bank/")
    public ResponseEntity<Bank> updateBank(@RequestBody BankRequest bankRequest) {
        return this.bankService.updateBank(bankRequest);
    }

    // http://localhost:8080/api/bank/
    @DeleteMapping("/bank/{id}")
    public Boolean deleteBank(@PathVariable Long id) {
        return this.bankService.deleteBank(id);
    }

    // http://localhost:8080/api/customer/
    @GetMapping("/customer/")
    public List<Customer> getAllCustomers() {
        return this.bankService.getAllCustomers();
    }

    // http://localhost:8080/api/customer/1
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") Long id) {
        return this.bankService.getCustomerById(id);
    }

    // http://localhost:8080/api/customer/
    @PostMapping("/customer/")
    public Customer createCustomer(@RequestBody CustomerRequest customerRequest) {
        return this.bankService.createCustomer(customerRequest);
    }

    // http://localhost:8080/api/customer/1
    @PatchMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return this.bankService.updateCustomer(customerRequest);
    }

    // http://localhost:8080/api/customer/1
    @DeleteMapping("/customer/{id}")
    public Boolean deleteCustomer(@PathVariable Long id) {
        return this.bankService.deleteCustomer(id);
    }
}
