package com.polizas.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    @PostMapping("/evento")
    public String evento(@RequestBody Map<String,Object> body){

        System.out.println("Evento enviado al CORE:");
        System.out.println(body);

        return "Evento registrado";
    }
}