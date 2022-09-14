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
@RequestMapping("/api/bank")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    // http://localhost:8080/api/bank/
    @GetMapping("/")
    public List<Bank> getAllBanks() {
        return this.bankService.getAllBanks();
    }

    // http://localhost:8080/api/bank/1
    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return this.bankService.getBankById(id);
    }

    // http://localhost:8080/api/bank/
    @PostMapping("/")
    public Bank createBank(@RequestBody BankRequest bankRequest) {
        return this.bankService.createBank(bankRequest);
    }

    // http://localhost:8080/api/bank/1
    @PatchMapping("/")
    public ResponseEntity<Bank> updateBank(@RequestBody BankRequest bankRequest) {
        return this.bankService.updateBank(bankRequest);
    }

    // http://localhost:8080/api/bank/
    @DeleteMapping("/{id}")
    public Boolean deleteBank(@PathVariable Long id) {
        return this.bankService.deleteBank(id);
    }

}
