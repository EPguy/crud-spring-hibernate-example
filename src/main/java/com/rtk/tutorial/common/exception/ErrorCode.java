package com.rtk.tutorial.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_INSERT_FAILED(500, "Post insert failed."),
    POST_DELETE_FAILED(500, "Post delete failed."),
    POST_EDIT_FAILED(500, "Post edit failed."),
    POST_NOT_EXIST(500, "Post does not exit."),
    PASSWORD_INCORRECT(500, "Password incorrect.")

    ;

    private final int status;
    private final String message;

}
