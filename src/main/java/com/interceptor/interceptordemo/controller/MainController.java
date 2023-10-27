package com.interceptor.interceptordemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test")
    public String test(){
        System.out.println("Get Test Contorller");
        return "Get Test Controller";
    }

    @PostMapping("/test")
    public String testPost(){
        System.out.println("Post Test Contorller");
        return "Post Test Controller";
    }


}
