package com.datn.watch.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ApplicationContextUtils implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    applicationContext = ctx;
  }

  /**
   * Return ApplicationContext.
   *
   * @return ApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * Find and return a bean of a specific class type within the ApplicationContext.
   *
   * @param classType class type to find
   * @param <T> Generic
   * @return T found empty object
   */
  public static <T> T getBean(Class<T> classType) {
    return applicationContext.getBean(classType);
  }
}
