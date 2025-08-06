package com.test.technical.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Response<T> {
    private T data;
    private String message;
    private Integer code;
}
