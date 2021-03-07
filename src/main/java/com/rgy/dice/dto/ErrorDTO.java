package com.rgy.dice.dto;

import java.util.Objects;

public final class ErrorDTO {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDTO errorDTO = (ErrorDTO) o;
        return Objects.equals(code, errorDTO.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "code='" + code + '\'' +
                '}';
    }
}
