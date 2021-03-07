package com.rgy.dice.dto;

import static com.rgy.dice.exception.ErrorStrand.GENERAL_ERROR_MESSAGE;

public enum StatusCode {

    GEN500(GENERAL_ERROR_MESSAGE);

    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
