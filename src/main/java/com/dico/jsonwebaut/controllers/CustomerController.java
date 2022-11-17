package com.dico.jsonwebaut.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


    @GetMapping("/customers")
    public String customers( Model model){

        return "All Customers will be fetches in Json here";
    }
}
