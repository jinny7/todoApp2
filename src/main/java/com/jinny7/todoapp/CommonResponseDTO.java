package com.jinny7.todoapp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponseDTO<T> {
    private Integer statusCode;
    private String msg;
    private Object data;
}
