package com.sametcanal.service;

import com.sametcanal.controller.request.MoneyRequest;
import com.sametcanal.exception.AtmBusinessException;
import com.sametcanal.model.Customer;
import com.sametcanal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer addMoney(MoneyRequest moneyRequest){
        if(!customerRepository.existsById(moneyRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }
        Customer updateMoney = customerRepository.findById(moneyRequest.getId()).orElse(null);
        updateMoney.setCustomerPrice(updateMoney.getCustomerPrice() + moneyRequest.getPrice());
        return this.customerRepository.save(updateMoney);
    }

    @Override
    public Customer withdrawMoney(MoneyRequest moneyRequest){
        if(!customerRepository.existsById(moneyRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }

        if(customerRepository.findById(moneyRequest.getId()).orElse(null).getCustomerPrice() < moneyRequest.getPrice()){
            throw new AtmBusinessException("ATM-1001","There is not enough money in the account", HttpStatus.NOT_FOUND);
        }

        Customer updateMoney = customerRepository.findById(moneyRequest.getId()).orElse(null);
        updateMoney.setCustomerPrice(updateMoney.getCustomerPrice() - moneyRequest.getPrice());
        return this.customerRepository.save(updateMoney);
    }

    @Override
    public Customer moneyTransfer(MoneyRequest moneyRequest,Long id){
        if(!customerRepository.existsById(moneyRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }

        if(customerRepository.findById(moneyRequest.getId()).orElse(null).getCustomerPrice() < moneyRequest.getPrice()){
            throw new AtmBusinessException("ATM-1001","There is not enough money in the account", HttpStatus.NOT_FOUND);
        }

        Customer senderMoney = customerRepository.findById(moneyRequest.getId()).orElse(null);
        senderMoney.setCustomerPrice(senderMoney.getCustomerPrice() - moneyRequest.getPrice());
        this.customerRepository.save(senderMoney);
        Customer recipientMoney = customerRepository.findById(id).orElse(null);
        recipientMoney.setCustomerPrice(recipientMoney.getCustomerPrice() + moneyRequest.getPrice());

        return this.customerRepository.save(recipientMoney);
    }


}
