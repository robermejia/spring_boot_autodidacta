package com.robermejia.saludo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensajePersonalizadoController {

    @GetMapping("saludo/{saludo}")
    public String saludoPersonalizado(@PathVariable String saludo){
        return "Hola, " + saludo;
    }

}
