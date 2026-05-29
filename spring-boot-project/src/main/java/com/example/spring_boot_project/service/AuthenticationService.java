package com.example.spring_boot_project.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.dto.request.AuthenticationRequest;
import com.example.spring_boot_project.dto.request.IntrospectRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.AuthenticationResponse;
import com.example.spring_boot_project.dto.response.IntrospectResponse;
import com.example.spring_boot_project.entity.Users;
import com.example.spring_boot_project.exception.AppException;
import com.example.spring_boot_project.exception.ErrorCode;
import com.example.spring_boot_project.repository.UsersRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    private UsersRepository userRepository;

    @NonFinal
    protected static final String SECRET_KEY = "8642eb70e1b158bb30b63efa72def9ae372866ff3f41f52de67b2e3953f7a418";

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var user = userRepository.findByUserName(request.getUserName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder ps = new BCryptPasswordEncoder(10);
        boolean authenticated = ps.matches(request.getPassWord(), user.getPassWord());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        var token = GenerateToken(request.getUserName());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String GenerateToken(String userName) {

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("commercialweb.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "customValue")
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot generate token for username: {}", userName, e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request2) throws ParseException, JOSEException {

        var token = request2.getToken();

        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(verifier);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }
}
