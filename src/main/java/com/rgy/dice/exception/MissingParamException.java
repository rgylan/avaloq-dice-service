package com.rgy.dice.exception;

import static com.rgy.dice.exception.ErrorStrand.MISSING_PARAMETER_EXCEPTION;

public class MissingParamException extends BaseException {

    public MissingParamException() {
        super(MISSING_PARAMETER_EXCEPTION);
    }
}
