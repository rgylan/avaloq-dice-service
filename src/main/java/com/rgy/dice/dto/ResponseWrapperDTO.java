package com.rgy.dice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rgy.dice.util.GenericBuilder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseWrapperDTO<R> {

    private List<ErrorDTO> errors;
    private R data;

    // Need default constructor to initialize an instance
    public ResponseWrapperDTO() {
    }


    public ResponseWrapperDTO(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public static <R> GenericBuilder<ResponseWrapperDTO> build(R data) {
        return GenericBuilder.build(ResponseWrapperDTO.class).with(object -> {
            object.setData(data);
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseWrapperDTO<?> that = (ResponseWrapperDTO<?>) o;
        return Objects.equals(errors, that.errors) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, data);
    }

    @Override
    public String toString() {
        return "ResponseWrapperDTO{" +
                "errors=" + errors +
                ", data=" + data +
                '}';
    }
}
