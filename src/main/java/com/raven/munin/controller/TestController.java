package com.raven.munin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String helloMunin() {
        return "Hello Munin";
    }

    @GetMapping("/users")
    public String helloUser() {
        return "Hello User";
    }

//    @GetMapping("/users/{id}")
//    public String helloUser(@PathVariable("id")String id) {
//        return "Hello"+id;
//    }



}
