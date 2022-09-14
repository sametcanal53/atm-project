package com.sametcanal.controller.api;

import com.sametcanal.controller.request.MoneyRequest;
import com.sametcanal.model.Customer;
import com.sametcanal.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/money")
@RequiredArgsConstructor
public class MoneyController {

    private final CustomerService customerService;

    // http://localhost:8080/api/money/add/
    @PostMapping("/add")
    public Customer addMoney(@Valid @RequestBody MoneyRequest moneyRequest){
        return this.customerService.addMoney(moneyRequest);
    }

    // http://localhost:8080/api/money/withdraw/
    @PostMapping("/withdraw")
    public Customer withdrawMoney(@Valid  @RequestBody MoneyRequest moneyRequest){
        return this.customerService.withdrawMoney(moneyRequest);
    }

    // http://localhost:8080/api/money/transfer/5
    @PostMapping("/transfer/{id}")
    public Customer moneyTransfer(@Valid @RequestBody MoneyRequest moneyRequest,@PathVariable Long id){
       return this.customerService.moneyTransfer(moneyRequest,id);
    }
}
