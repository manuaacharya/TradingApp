package com.app.trade.model;

public class ApiResponse {

    private String message;
    private String token;
    private Object object;

    public ApiResponse(String message, String token,Object object) {
        this.message = message;
        this.token = token;
        this.object = object;
    }

    public ApiResponse(String message,Object object) {
        this.message = message;
        this.object = object;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

