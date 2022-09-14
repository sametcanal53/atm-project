package com.sametcanal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","bank"})
public class Customer extends BaseEntity{

    /*@Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

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
