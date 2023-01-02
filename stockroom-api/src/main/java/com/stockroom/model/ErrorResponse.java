package com.stockroom.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ErrorResponse {
    private Integer status;
    private String code;
    private String message;

    @JsonCreator
    public ErrorResponse(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
