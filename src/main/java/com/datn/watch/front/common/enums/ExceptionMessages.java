package com.datn.watch.front.common.enums;

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


