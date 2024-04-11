package com.datn.watch.common.enums;

/**
 * @author tobi
 * @desc : ...
 */
public enum ResultMessages {
  API_SUCCESS("000", "api.success");
  private String code;
  private String messageCode;

  public String getCode() {
    return code;
  }

  public String getMessageCode() {
    return messageCode;
  }

  ResultMessages(String code, String messageCode) {
    this.code = code;
    this.messageCode = messageCode;
  }
}
