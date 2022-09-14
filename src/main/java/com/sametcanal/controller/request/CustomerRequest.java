package com.sametcanal.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class CustomerRequest{
    private Long id;

    @NotEmpty(message = "Please enter a name")
    private String customerName;
    private double customerPrice;
    private Long bankId;
}
