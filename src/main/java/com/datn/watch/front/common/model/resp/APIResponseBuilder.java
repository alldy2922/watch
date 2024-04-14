package com.datn.watch.front.common.model.resp;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public class APIResponseBuilder {


  public static APIResponse buildResponse(@NotNull HttpStatus httpStatus) {
    return new APIResponse(String.valueOf(httpStatus.value()), String.valueOf(httpStatus.value()),
        httpStatus.getReasonPhrase(), null);
  }

  public static APIResponse buildResponse(@NotNull HttpStatus httpStatus, Object data) {
    return new APIResponse(String.valueOf(httpStatus.value()), String.valueOf(httpStatus.value()),
        httpStatus.getReasonPhrase(), data);
  }

  public static APIResponse buildResponse(@NotNull HttpStatus httpStatus, String customMsg,
      Object data) {
    return new APIResponse(String.valueOf(httpStatus.value()), String.valueOf(httpStatus.value()),
        customMsg, data);
  }


}
