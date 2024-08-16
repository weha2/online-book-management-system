package com.weha.online_book_management_system.exception;

import com.weha.online_book_management_system.dtos.DataState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<DataState<String>> handleException(Throwable throwable) {
        DataState<String> res = new DataState<>(false, null, throwable.getMessage());
        return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
    }

}
