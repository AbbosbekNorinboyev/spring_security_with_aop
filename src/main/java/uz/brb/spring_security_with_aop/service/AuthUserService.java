package uz.brb.spring_security_with_aop.service;

import uz.brb.spring_security_with_aop.dto.request.LoginRequest;
import uz.brb.spring_security_with_aop.dto.request.RegisterRequest;
import uz.brb.spring_security_with_aop.dto.response.Response;

public interface AuthUserService {
    Response<?> register(RegisterRequest registerRequest);

    Response<?> login(LoginRequest loginRequest);
}