package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Fibonacci")
public class Fibonacci {

    @GetMapping("/accionFibonacci/{numero}/{nombre}")
    public String fibonacci(@PathVariable int numero,@PathVariable String nombre){
        if (numero <= 0) {
            return nombre;
        }
        int a = 0;
        int b = 1;
        for (int i = 1; i <= numero; i++) {
            if (i == 1) {
                System.out.println(0);
            } else if (i == 2) {
                System.out.println(1);
            } else {
                int c = a + b;
                System.out.println(c);
                a = b;
                b = c;
            }
        }

        return nombre;
    }

}
