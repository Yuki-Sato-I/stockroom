package com.stockroom.domain.model;

import com.stockroom.util.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class User {
    private final UUID id;
    private final String email;
    private String token;
    private final String passwordDigest;
    private final Boolean activated;
    private final Integer status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void changeToken() throws NoSuchAlgorithmException {
        this.token = Function.generateToken();
    }
}
