package com.zuer.zuerlvdoubanauth.exception;

import lombok.Data;

@Data
public class FeignFaileResult {
    private String message;
    private int status;
    private String exception;
}
