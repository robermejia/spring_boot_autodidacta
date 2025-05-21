package com.robermejia.responsive_entity.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.robermejia.responsive_entity.model.Customer;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Roberto", "rober", "123"),
            new Customer(234, "Agustín", "agus", "234"),
            new Customer(345, "Gustavo", "tavo", "345"),
            new Customer(456, "Erica", "eri", "456")));

    @RequestMapping(method = RequestMethod.GET)
    // @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        //return customers;
        return ResponseEntity.ok(customers);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    // @GetMapping("/{username}")
    public ResponseEntity<?> getCliente(@PathVariable String username) {
        for (Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                //return c;
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + username);
    }

    @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer) {
        customers.add(customer);
        //return customer;
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente no encontrado con el ID:  " + customer.getID());
    }

    @RequestMapping(method = RequestMethod.PUT)
    // @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                //return c;
                return ResponseEntity.ok("Cliente modificado exitosamente: " + customer.getID());
            }
        }
        //return null;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customer.getID());
    }

    @RequestMapping(method = RequestMethod.PATCH)
    // @PatchMapping
    public ResponseEntity<?> pathCliente(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                if (customer.getName() != null)
                    c.setName(customer.getName());
                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                //return c;
                return ResponseEntity.ok("Cliente modificado exitosamente con el ID: " + customer.getID());
            }
        }
        //return null;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customer.getID());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getID() == id) {
                customers.remove(c);
                //return c;
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Mensaje no leído : " + id);
            }
        }
        //return null;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + id);
    }

}

