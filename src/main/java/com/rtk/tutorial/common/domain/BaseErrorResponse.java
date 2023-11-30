package com.rtk.tutorial.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseErrorResponse {
    private int status;
    private String message;
}
