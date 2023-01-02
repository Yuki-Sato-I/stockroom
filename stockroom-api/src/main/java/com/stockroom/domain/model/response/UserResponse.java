package com.stockroom.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class UserResponse {
    private final UUID id;
    private final String email;
    private final String token;
    private final Boolean activated;
    private final Integer status;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
}
