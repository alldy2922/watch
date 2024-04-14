package com.datn.watch.front.common.model.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author tobi
 * @desc : ...
 */
@Data
public class ErrorDetails {

  private Map<String, String> errors;
}
