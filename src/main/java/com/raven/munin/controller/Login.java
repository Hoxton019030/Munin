package com.raven.munin.controller;

import com.raven.munin.auth.AuthRequest;
import com.raven.munin.model.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/loggin", produces = MediaType.APPLICATION_JSON_VALUE)
public class Login {

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/")
    public ResponseEntity<Map<  String, String>> issueToken(@Valid @RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request);
        System.out.println("token = " + token);
        Map<String, String> response = Collections.singletonMap("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<  String, Object>> parseToken(@RequestBody Map<String,String> request){
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Map<String, String>> helloLogin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "fukin");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> helloMunin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "munin");
        return ResponseEntity.ok(map);

    }

}
