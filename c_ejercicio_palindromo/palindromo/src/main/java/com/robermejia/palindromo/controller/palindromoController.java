package com.robermejia.palindromo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class palindromoController {

    @GetMapping("validarPalindromo/{word}")
    public String palindromo(@PathVariable String word) {
        if (isPalindromo(word)) {
            return "La palabra " + word + " es palindromo";
        }else {
            return "La palabra " + word + " no es palindromo";
        }
    }

    private boolean isPalindromo(String palabra) {
        int length = palabra.length();
        for (int i = 0; i < length / 2; i++) {
            if (palabra.charAt(i) != palabra.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }
}

