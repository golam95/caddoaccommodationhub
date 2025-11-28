package com.caddospace.caddoaccommodationhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    private boolean status;

    private int statusCode;

    private String message;

    private LocalDateTime timestamp;

    private T data;

    public APIResponse(boolean status, int statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}