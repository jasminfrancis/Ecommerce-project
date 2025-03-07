package com.exchange.controller;

import com.exchange.Model.LoginRequest;
import com.exchange.response.APIResponse;
import com.exchange.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;
    /**
     * Authenticates the user and generates a JWT token upon successful login.
     *
     * @param loginRequest The login credentials (username and password).
     * @return A response entity containing the JWT token if authentication is successful.
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody LoginRequest loginRequest) {
        logger.info("*********** Login controller invoked ***************");
        return ResponseEntity.ok(loginService.login(loginRequest));
    }
}
