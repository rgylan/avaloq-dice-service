package com.rgy.dice.exception;

import com.rgy.dice.dto.ErrorDTO;
import com.rgy.dice.dto.ResponseWrapperDTO;
import com.rgy.dice.util.GenericBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

import static com.rgy.dice.exception.ErrorStrand.GENERAL_ERROR_MESSAGE;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<Object> catchUnhandledException(final Exception ex) {
        // Do not return stack trace to client, return simple message and write to log the actual error.
        return error(HttpStatus.INTERNAL_SERVER_ERROR, buildError(GENERAL_ERROR_MESSAGE));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({DiceRollException.class, DiceSideException.class})
    protected ResponseEntity<Object> catchValidationException(final BaseException ex) {
        System.out.printf("BaseException: "+ex.getMessage());
        return error(HttpStatus.INTERNAL_SERVER_ERROR, buildError(ex.getMessage()));
    }

    private ResponseEntity<Object> error(HttpStatus status, ErrorDTO... errors) {
        return new ResponseEntity<>(new ResponseWrapperDTO<>(Arrays.asList(errors)), status);
    }

    private ErrorDTO buildError(String code) {
        return GenericBuilder.build(ErrorDTO.class).with(object -> {
            object.setCode(code);
        }).toInstance();
    }

}
