package com.exchange.service;

import com.exchange.Model.LoginRequest;
import com.exchange.constants.ResponseDescription;
import com.exchange.constants.ResponseMessage;
import com.exchange.controller.LoginController;
import com.exchange.customException.LoginException;
import com.exchange.response.APIResponse;
import com.exchange.response.ErrorResponse;
import com.exchange.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public APIResponse<?> login(LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            // Generate JWT token after successful authentication
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            // Return the token in the response
            return new APIResponse<>(ResponseMessage.LOGIN_SUCESS, ResponseDescription.LOGIN_SUCESS,token, HttpStatus.OK.value());
        } catch (BadCredentialsException e) {
            logger.error("Login failed for user: {} - Invalid credentials", loginRequest.getUsername());
            throw new LoginException(ResponseMessage.INVALID_CREDENTAILS);
        } catch (Exception e) {
            logger.error("Unexpected error during login for user: {} - {}", loginRequest.getUsername(), e.getMessage());
            throw new LoginException("Login failed due to an unexpected error.");
        }


    }
}
