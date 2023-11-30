package com.rtk.tutorial.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class BaseResponse<Data> {
    private final String result;
    private final String message;
    private final Data data;

    public static <Data> BaseResponse<Data> Success(Data data) {
        return BaseResponse.of("SUCCESS", null, data);
    }
    public static <Data> BaseResponse<Data> Fail(String message) {
        return BaseResponse.of("FAIL", message, null);
    }
}
