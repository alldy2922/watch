package com.datn.watch.common.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize
public class ResultData<T> extends Result {

    @JsonProperty("data")
    private T resultData;

    public ResultData() {
        super();
    }

    public ResultData(T resultData) {
        super();
        this.resultData = resultData;
    }

    public ResultData(String status) {
        super(status);
    }

    public ResultData(Status status, T resultData) {
        super(status);
        this.resultData = resultData;
    }

    public ResultData(String status, T resultData) {
        super(status);
        this.resultData = resultData;
    }

    public ResultData(String status, String msg, T resultData) {
        super(status, msg);
        this.resultData = resultData;
    }

    public ResultData(String status, String code, String msg, T resultData) {
        super(status, code, msg);
        this.resultData = resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "ResultData [resultData=" + resultData + "]";
    }
}
