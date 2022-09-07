package com.raven.munin.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
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
