package com.cat.mid.demo.cart.exceptions;

import com.cat.mid.demo.cart.cartms.domain.enums.StatusCode;
import com.cat.mid.demo.cart.shareddomain.rest.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> exception(Exception ex) {
        log.error("Get couldn't handler exception", ex);
        return new ResponseEntity<>(new ExceptionDto(StatusCode.C9999.getCode(), StatusCode.C9999.getMsg()), HttpStatus.OK);
    }

    @ExceptionHandler(CartException.class)
    protected ResponseEntity<Object> cartException(CartException ex) {
        log.error("Get cartException", ex);
        return new ResponseEntity<>(new ExceptionDto(ex.getStatusCode().getCode(), ex.getStatusCode().getMsg()), HttpStatus.OK);
    }
}
