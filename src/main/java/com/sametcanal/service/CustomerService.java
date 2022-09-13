package com.sametcanal.service;

import com.sametcanal.controller.request.MoneyRequest;
import com.sametcanal.model.Customer;

public interface CustomerService {

    Customer addMoney(MoneyRequest moneyRequest);
    Customer withdrawMoney(MoneyRequest moneyRequest);
    Customer moneyTransfer(MoneyRequest moneyRequest,Long id);

}
