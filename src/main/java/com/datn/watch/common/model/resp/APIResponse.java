package com.datn.watch.common.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tobi
 * @desc : ...
 */
@Data
@AllArgsConstructor
public class APIResponse {

  private String status;

  private String messageCode;

  private String message;

  private Object result;

}
