package com.sametcanal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name = "banks")
public class Bank{
    @Id
    @Column(name = "bankId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "bank_name")
    private String bankName;

    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private List<Customer> customers;
}
