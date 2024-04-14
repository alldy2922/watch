package com.datn.watch.front.common.utils;

import java.util.UUID;

/**
 * @author tobi
 * @desc : ...
 */
public class TokenUtils {

  private TokenUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static String generateTokenUUID() {
    return UUID.randomUUID().toString();
  }
}
