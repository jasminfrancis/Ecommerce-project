package com.exchange.service;

import com.exchange.Model.LoginRequest;
import com.exchange.constants.ResponseDescription;
import com.exchange.constants.ResponseMessage;
import com.exchange.customException.LoginException;
import com.exchange.response.APIResponse;
import com.exchange.response.ErrorResponse;
import com.exchange.security.JwtUtil;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }
    @Override
    public APIResponse<?> login(LoginRequest loginRequest) {

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

         throw new LoginException(ResponseMessage.INVALID_CREDENTAILS);
        }


    }
}
