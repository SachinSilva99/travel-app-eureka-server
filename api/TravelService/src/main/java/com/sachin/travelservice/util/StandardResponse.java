package com.sachin.travelservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse <T>{
    private int code;
    private String msg;
    private T data;
}
