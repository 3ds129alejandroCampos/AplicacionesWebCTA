package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/FizzBuzz")
public class FizzBuzz {

    @GetMapping("/accionFizz/{numero}/{nombre}")
    public String fizzBuzz(@PathVariable int numero, @PathVariable String nombre){
        for(int i = 1;i<=numero;i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("fizzBuzz");
            } else if(i%3==0){
                System.out.println("Fizz");
            }else if(i%5==0){
                System.out.println("Buzz");
            }else{
                System.out.println(i);
            }
        }
        return nombre;
    }
}
