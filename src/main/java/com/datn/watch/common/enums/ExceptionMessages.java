package com.datn.watch.common.enums;

/**
 * @author tobi
 * @desc : ...
 */
public enum ExceptionMessages {
  API_ERROR_UNKNOWN("01", "api.error.unknown"),
  API_INVALID_REQUEST_ARGUMENT("02", "api.error.invalid-request-argument"),
  API_MISSING_REQUEST_PARAMETER("03", "api.error.missing-request-argument"),
  API_SERVICE_DOWN("04", "api.error.service-down"),
  API_CROSS_SERVICE_CALL_FAILED("05", "api.error.crosses-service-call-failed");

//  SUCCESS ("1","success"),
//  ERROR ("1","FAIL"),
//  PARAM_ERROR ("1","PARAM_ERROR"),
//  NO_DATA ("1","NO_DATA"),
//  FORBIDDEN ("1","FORBIDDEN");
  private String code;
  private String messageCode;

  public String getCode() {
    return code;
  }

  public String getMessageCode() {
    return messageCode;
  }

  ExceptionMessages(String code, String messageCode) {
    this.code = code;
    this.messageCode = messageCode;
  }
}


