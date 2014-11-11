package org.gp19.analysis.service.controller.advice;

import org.gp19.analysis.service.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created on 11/11/14.
 */
@ControllerAdvice
public class BaseControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlingGenericException(Exception exception) {

        // Adding an Error Id
        String msg = exception.getMessage() + " Error Id: " + UUID.randomUUID().toString();

        // Logging
        System.out.println(msg);

        // 500 Http Status Code
        HttpStatus httpStatusInternalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        // Sending Error to the Client
        ErrorDto errorDto = new ErrorDto();
        errorDto.error = msg;
        errorDto.status = httpStatusInternalServerError.getReasonPhrase();
        errorDto.code = httpStatusInternalServerError.value();

        return new ResponseEntity<>(errorDto, httpStatusInternalServerError);
    }
}
