package com.sametcanal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","bankId","bank"})
public class Customer{

    @Id
    @Column(name = "customerId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_price")
    private double customerPrice;

    @Column(name = "bankId")
    private Long bankId;

    @ManyToOne()
    @JoinColumn(name = "bankId",insertable = false,updatable = false)
    private Bank bank;
}
