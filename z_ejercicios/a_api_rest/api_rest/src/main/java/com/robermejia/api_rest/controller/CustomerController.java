package com.robermejia.api_rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robermejia.api_rest.model.Customer;

@RestController
public class CustomerController {

    List<Customer>customers = new ArrayList<>(Arrays.asList(
        new Customer(123, "Roberto", "rober",  "123"),
        new Customer(234, "Agustín", "agus",  "234"),
        new Customer(345, "Gustavo", "tavo",  "345"),
        new Customer(456, "Erica", "eri",  "456")
    ));

    @GetMapping("/clientes")
    public List<Customer> getCustomers(){
        return customers;
    }
}
