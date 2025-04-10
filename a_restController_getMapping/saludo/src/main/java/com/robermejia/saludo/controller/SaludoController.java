package com.robermejia.saludo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class SaludoController {

@GetMapping({"saludo","hello"})
public String saludo(){
    return "Hello, world";
}

}
