package com.exchange.service;

import com.exchange.response.APIResponse;
import com.exchange.Model.LoginRequest;

public interface LoginService {
    APIResponse<?> login(LoginRequest loginRequest);
}
