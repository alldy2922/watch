package com.datn.watch.common.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.Date;

@Getter
@JsonSerialize
public class Result {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    public static final String PARAM_ERROR = "paramError";
    public static final String NO_DATA = "noData";
    public static final String FORBIDDEN = "forbidden";
  public static final String UNAUTHORIZED = "unAuthorized";


  private String status;

    private String code;

    private String msg;

    private String date;

    public Result() {
        this.status = Result.SUCCESS;
        this.code = "00";
        this.msg = "";
        setNowDate();
    }

    public Result(Status status) {
        if (status != null) {
            this.status = status.getStatus();
            this.code = status.getCode();
            this.msg = status.getMessage();
        }

        setNowDate();
    }

    public Result(String status) {
        this.status = status;
        if (Result.SUCCESS.equals(status)) {
            this.msg = "It was processed normally.";
            this.code = "00";
        } else if (Result.FAIL.equals(status)) {
            this.code = "10";
            this.msg = "A system error has occurred.";
        } else if (Result.NO_DATA.equals(status)) {
            this.code = "11";
            this.msg = "Terminal information cannot be confirmed.";
        } else if (Result.ERROR.equals(status)) {
            this.code = "99";
            this.msg = "An error occurred in linking.";
        } else if (Result.PARAM_ERROR.equals(status)) {
            this.code = "98";
            this.msg = "Parameter information is incorrect.";
        }

        setNowDate();
    }

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
        if (Result.SUCCESS.equals(status)) {
            this.msg = "It was processed normally.";
            this.code = "00";
        } else if (Result.FAIL.equals(status)) {
            this.code = "01";
        } else if (Result.NO_DATA.equals(status)) {
            this.code = "11";
        } else if (Result.ERROR.equals(status)) {
            this.code = "99";
        } else if (Result.PARAM_ERROR.equals(status)) {
            this.code = "98";
        }

        setNowDate();
    }

    public Result(String status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        setNowDate();
    }

    public void setNowDate() {
        date = new Date().toString();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Result [status=" + status + ", code=" + code + ", msg=" + msg + ", date=" + date + "]";
    }

}
