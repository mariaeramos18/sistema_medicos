package com.github.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Informar para o Spring que a classe OlaController é um controlador
@RequestMapping("/ola")  //Informar o endpoint do OlaController (mapeamento de endereço)
public class OlaController {

    //GET
    @GetMapping
    public String olaMundo(){
        return "Olá mundo!";
    }

}
