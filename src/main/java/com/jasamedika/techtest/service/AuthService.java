package com.jasamedika.techtest.service;

import com.jasamedika.techtest.entity.Auth;
import com.jasamedika.techtest.model.*;
import com.jasamedika.techtest.repository.AuthRepository;
import com.jasamedika.techtest.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public InitDataResponse initData(InitDataRequest request){
        validationService.validate(request);

        Auth auth = new Auth();

        String generatedPassword = generateRandomPassword();
        auth.setNamaAdmin(request.getNamaAdmin());
        auth.setPerusahaan(request.getPerusahaan());
        auth.setEmail(request.getNamaAdmin()+"@mail.com");
        auth.setPassword(BCrypt.hashpw(generatedPassword, BCrypt.gensalt()));

        authRepository.save(auth);

        return InitDataResponse.builder()
                .namaAdmin(auth.getNamaAdmin())
                .perusahaan(auth.getPerusahaan())
                .email(auth.getEmail())
                .password(generatedPassword)
                .build();
    }

    @Transactional
    public TokenResponse login(LoginRequest request){
        validationService.validate(request);

        Auth auth = authRepository.findById(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau password salah"));

        if (BCrypt.checkpw(request.getPassword(), auth.getPassword())) {
            auth.setToken(UUID.randomUUID().toString());
            auth.setTokenExpiredAt(next30Days());

            authRepository.save(auth);

            return TokenResponse.builder()
                    .token(auth.getToken())
                    .expiredAt(auth.getTokenExpiredAt())
                    .build();
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau password salah");
        }

    }

    @Transactional
    public ChangePasswordResponse changePassword(ChangePasswordRequest request){
        validationService.validate(request);

        Auth auth = authRepository.findById(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ditemukan"));

        if (BCrypt.checkpw(request.getPassword(), auth.getPassword())) {
            if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
                auth.setPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));
            }
            else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password baru tidak sesuai");
            }

            authRepository.save(auth);

            return ChangePasswordResponse.builder()
                    .message("Password changed successfully")
                    .build();
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password lama salah");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }

    public String  generateRandomPassword() {
        String password = "";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        password = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return password;
    }
}
