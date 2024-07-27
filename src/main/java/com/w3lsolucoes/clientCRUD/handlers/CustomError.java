package com.w3lsolucoes.clientCRUD.handlers;


import java.time.Instant;

public class CustomError {

    private final Instant timestamp;
    private final Integer status;
    private  String error;
    private final String path;


    public CustomError(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setError(String error) {
        this.error = error;
    }

}
