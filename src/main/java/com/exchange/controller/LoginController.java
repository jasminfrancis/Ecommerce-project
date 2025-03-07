package com.exchange.controller;

import com.exchange.Model.LoginRequest;
import com.exchange.response.APIResponse;
import com.exchange.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody LoginRequest loginRequest) {
        // Call the service method to calculate the payable amount
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
}
