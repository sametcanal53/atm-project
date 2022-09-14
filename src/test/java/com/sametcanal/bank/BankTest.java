package com.sametcanal.bank;

import com.sametcanal.model.Bank;
import com.sametcanal.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankTest implements TestBank{

    @Autowired
    BankRepository bankRepository;

    // Create
    @Test
    @Override
    public void create() {
        Bank bank = Bank
                .builder()
                .bankName("Denizbank")
                .build();
        bankRepository.save(bank);
        assertNotNull(bankRepository.findById(6L).get());
    }

    // List
    @Test
    @Override
    public void list() {
        List<Bank> list = bankRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    // Find
    @Test
    @Override
    public void findById() {
        Bank bank = bankRepository.findById(6L).get();
        assertEquals("Denizbank", bank.getBankName());
    }

    //Update
    @Test
    @Override
    public void update() {
        Bank bank = bankRepository.findById(6L).get();
        bank.setBankName("Akbank");
        bankRepository.save(bank);

        assertNotEquals("Denizbank",bankRepository.findById(6L).get().getBankName());
    }
    // Delete
    @Test
    @Override
    public void delete() {
        bankRepository.deleteById(6L);
        assertThat(bankRepository.existsById(6L)).isFalse();
    }
}
