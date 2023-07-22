package io.github.balieiro.mscartoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartoes")
public class CartoesController {

    @GetMapping
    public String status(){
        return "Ok";
    }
}
