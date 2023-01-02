package com.stockroom.domain.model.enums;

public enum UserActivate {
    INITIAL_ACTIVATE(false),
    ACTIVATED(true);

    private final Boolean value;

    UserActivate(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }
}
