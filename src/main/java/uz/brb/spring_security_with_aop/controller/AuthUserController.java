package uz.brb.spring_security_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brb.spring_security_with_aop.dto.request.LoginRequest;
import uz.brb.spring_security_with_aop.dto.request.RegisterRequest;
import uz.brb.spring_security_with_aop.dto.response.Response;
import uz.brb.spring_security_with_aop.service.AuthUserService;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public Response<?> register(@RequestBody RegisterRequest registerRequest) {
        return authUserService.register(registerRequest);
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginRequest loginRequest) {
        return authUserService.login(loginRequest);
    }
}