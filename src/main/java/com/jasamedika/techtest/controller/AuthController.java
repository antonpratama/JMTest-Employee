package com.jasamedika.techtest.controller;

import com.jasamedika.techtest.model.*;
import com.jasamedika.techtest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/init-data",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<InitDataResponse> initData(@RequestBody InitDataRequest request){
        InitDataResponse initDataResponse = authService.initData(request);
        return WebResponse.<InitDataResponse>builder().data(initDataResponse).build();
    }

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginRequest request){
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @PostMapping(
            path = "/api/auth/ubah-password-sendiri",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request){
        ChangePasswordResponse changePasswordResponse = authService.changePassword(request);
        return WebResponse.<ChangePasswordResponse>builder().data(changePasswordResponse).build();
    }
}
