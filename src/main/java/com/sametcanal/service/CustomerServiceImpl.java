package com.sametcanal.service;

import com.sametcanal.controller.request.MoneyRequest;
import com.sametcanal.model.Process;
import com.sametcanal.exception.AtmBusinessException;
import com.sametcanal.model.Customer;
import com.sametcanal.repository.CustomerRepository;
import com.sametcanal.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProcessRepository processRepository;

    @Override
    public Customer addMoney(MoneyRequest moneyRequest){
        if(!customerRepository.existsById(moneyRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }
        Customer updateMoneyInAccount = customerRepository.findById(moneyRequest.getId()).orElse(null);
        updateMoneyInAccount.setCustomerPrice(updateMoneyInAccount.getCustomerPrice() + moneyRequest.getPrice());
        Process process = Process
                            .builder()
                            .customerId(updateMoneyInAccount.getId())
                            .process(updateMoneyInAccount.getCustomerName() + " Added Money to Account")
                            .transactionBalance(moneyRequest.getPrice())
                            .remainingBalance(updateMoneyInAccount.getCustomerPrice())
                            .build();
        this.processRepository.save(process);
        return this.customerRepository.save(updateMoneyInAccount);
    }

    @Override
    public Customer withdrawMoney(MoneyRequest moneyRequest){
        if(!customerRepository.existsById(moneyRequest.getId())){
            throw new AtmBusinessException("ATM-1000","Customer Not Found", HttpStatus.NOT_FOUND);
        }

        if(customerRepository.findById(moneyRequest.getId()).orElse(null).getCustomerPrice() < moneyRequest.getPrice()){
            throw new AtmBusinessException("ATM-1001","There is not enough money in the account", HttpStatus.NOT_FOUND);
        }

        Customer updateMoneyInAccount = customerRepository.findById(moneyRequest.getId()).orElse(null);
        updateMoneyInAccount.setCustomerPrice(updateMoneyInAccount.getCustomerPrice() - moneyRequest.getPrice());
        Process process = Process
                                .builder()
                                .customerId(updateMoneyInAccount.getId())
                                .process(updateMoneyInAccount.getCustomerName() + " Withdrawn Money From Account")
                                .transactionBalance(moneyRequest.getPrice())
                                .remainingBalance(updateMoneyInAccount.getCustomerPrice())
                                .build();
        this.processRepository.save(process);
        return this.customerRepository.save(updateMoneyInAccount);
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
        Customer recipientMoney = customerRepository.findById(id).orElse(null);
        System.out.println(recipientMoney.getCustomerName());
        recipientMoney.setCustomerPrice(recipientMoney.getCustomerPrice() + moneyRequest.getPrice());
        Process process = Process
                                .builder()
                                .customerId(senderMoney.getId())
                                .process(senderMoney.getId() +" " +senderMoney.getCustomerName() + " Transferred Money to Another Account : "+ recipientMoney.getId()+" "+recipientMoney.getCustomerName())
                                .transactionBalance(moneyRequest.getPrice())
                                .remainingBalance(senderMoney.getCustomerPrice())
                                .build();
        this.customerRepository.save(senderMoney);
        this.processRepository.save(process);
        return this.customerRepository.save(recipientMoney);
    }


}
