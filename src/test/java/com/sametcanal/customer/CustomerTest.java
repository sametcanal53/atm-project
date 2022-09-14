package com.sametcanal.customer;

import com.sametcanal.model.Bank;
import com.sametcanal.model.Customer;
import com.sametcanal.repository.BankRepository;
import com.sametcanal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RequiredArgsConstructor
public class CustomerTest implements TestCustomer{

     @Autowired
     CustomerRepository customerRepository;

    // Create
    @Test
    @Override
    public void create() {
        Customer customer = Customer
                .builder()
                .customerName("Sametcan")
                .customerPrice(7500)
                .bankId(1L)
                .build();

        customerRepository.save(customer);
        assertNotNull(customerRepository.findById(7L).get());
    }

    // List
    @Test
    @Override
    public void list() {
        List<Customer> list = customerRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    // Find
    @Test
    @Override
    public void findById() {
        Customer customer = customerRepository.findById(7L).get();
        assertEquals("Sametcan", customer.getCustomerName());
    }

    //Update
    @Test
    @Override
    public void update() {
        Customer customer = customerRepository.findById(7L).get();
        customer.setCustomerName("Samet");
        customer.setCustomerPrice(9000);
        customer.setBankId(6L);
        customerRepository.save(customer);

        assertNotEquals("Sametcan",customerRepository.findById(7L).get().getCustomerName());
    }
    // Delete
    @Test
    @Override
    public void delete() {
        customerRepository.deleteById(7L);
        assertThat(customerRepository.existsById(7L)).isFalse();
    }
}
