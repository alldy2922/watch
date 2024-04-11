package com.datn.watch.common.utils;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtils {

  /**
   * The message of the forwarded code is applied in multiple languages and returned.
   * If the corresponding message is not defined, the code is returned as it is.
   *
   * @param code message code
   * @return String Messages applied in multiple languages
   */
  public static String getMessage(String code) {

    return getMessage(code, null);
  }

  /**
   * The message of the forwarded code is applied in multiple languages and returned.
   * If the corresponding message is not defined, the code is returned as it is.
   *
   * @param code message code
   * @param args placeholder
   * @return String Messages applied in multiple languages
   */
  public static String getMessage(String code, String[] args) {

    MessageSourceAccessor messageSourceAccessor = ApplicationContextUtils.getBean(
      MessageSourceAccessor.class);
    try {
      return messageSourceAccessor.getMessage(code, args);
    } catch (NoSuchMessageException noSuchMessageException) {
      return code;
    }
  }
}
