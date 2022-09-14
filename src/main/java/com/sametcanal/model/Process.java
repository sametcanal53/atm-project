package com.sametcanal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long processId;
    @Column(name = "customerId")
    private Long customerId;
    @Column(name = "process")
    private String process;
    @Column(name = "transaction_balance")
    private double transactionBalance;
    @Column(name = "remaining_balance")
    private double remainingBalance;
}
