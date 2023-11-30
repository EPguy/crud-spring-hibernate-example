package com.rtk.tutorial.common.exception;

import com.rtk.tutorial.common.domain.BaseErrorResponse;
import com.rtk.tutorial.common.domain.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(final CustomException e) {
        return new ResponseEntity(new BaseErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
