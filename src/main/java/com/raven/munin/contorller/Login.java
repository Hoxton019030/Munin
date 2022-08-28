package com.raven.munin.contorller;

import com.raven.munin.auth.AuthRequest;
import com.raven.munin.model.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class Login {

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<Map<String,String>> issueToken(@Valid @RequestBody AuthRequest request){
        String token = jwtService.generateToken(request);
        Map<String, String> response = Collections.singletonMap("token", token);
        return ResponseEntity.ok(response);
    }

}
