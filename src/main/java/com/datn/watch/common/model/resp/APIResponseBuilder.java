package com.datn.watch.common.model.resp;

import com.datn.watch.common.enums.ExceptionMessages;
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

  public static APIResponse buildExceptionResponse(@NotNull ExceptionMessages exceptionMsg) {
    return new APIResponse(exceptionMsg.getCode(), exceptionMsg.getCode(),
        exceptionMsg.getMessageCode(), null);
  }

  public static APIResponse buildExceptionResponse(@NotNull ExceptionMessages exceptionMsg,
      Object data) {
    return new APIResponse(exceptionMsg.getCode(), exceptionMsg.getCode(),
        exceptionMsg.getMessageCode(), data);
  }

  public static APIResponse buildExceptionResponse(@NotNull ExceptionMessages exceptionMsg,
      String customMessage, Object data) {
    return new APIResponse(exceptionMsg.getCode(), exceptionMsg.getMessageCode(), customMessage,
        data);
  }

}
