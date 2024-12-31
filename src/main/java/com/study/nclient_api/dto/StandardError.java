package com.study.nclient_api.dto;

import java.time.Instant;

public class StandardError {

    private Instant moment;
    private Integer status;
    private String error;
    private String path;

    public StandardError(Instant moment, Integer status, String error, String path) {
        this.moment = moment;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
