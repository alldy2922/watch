package com.datn.watch.common.model.entity;

import lombok.Data;

/**
 * @author tobi
 * @desc : ...
 */
@Data
public class ErrorTraceInfo {

  private String error;
  private StackTraceElement[] traces;
}
