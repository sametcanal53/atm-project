package com.sametcanal.controller.api;

import com.sametcanal.controller.request.CustomerRequest;
import com.sametcanal.model.Customer;
import com.sametcanal.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final BankService bankService;

    // http://localhost:8080/api/customer/
    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return this.bankService.getAllCustomers();
    }

    // http://localhost:8080/api/customer/1
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") Long id) {
        return this.bankService.getCustomerById(id);
    }

    // http://localhost:8080/api/customer/
    @PostMapping("/")
    public Customer createCustomer(@RequestBody CustomerRequest customerRequest) {
        return this.bankService.createCustomer(customerRequest);
    }

    // http://localhost:8080/api/customer/1
    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return this.bankService.updateCustomer(customerRequest);
    }

    // http://localhost:8080/api/customer/1
    @DeleteMapping("/{id}")
    public Boolean deleteCustomer(@PathVariable Long id) {
        return this.bankService.deleteCustomer(id);
    }
}
