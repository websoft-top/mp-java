package com.gxwebsoft.common.core.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author ds
 * @Date 2022-05-05
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
  /**
   * spring的应用上下文
   */
  private static ApplicationContext applicationContext;

  /**
   * 初始化时将应用上下文设置进applicationContext
   * @param applicationContext
   * @throws BeansException
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextUtil.applicationContext=applicationContext;
  }

  public static ApplicationContext getApplicationContext(){
    return applicationContext;
  }

  /**
   * 根据bean名称获取某个bean对象
   *
   * @param name bean名称
   * @return Object
   * @throws BeansException
   */
  public static Object getBean(String name) throws BeansException {
    return applicationContext.getBean(name);
  }

  /**
   * 根据bean的class获取某个bean对象
   * @param beanClass
   * @param <T>
   * @return
   * @throws BeansException
   */
  public static <T> T getBean(Class<T> beanClass) throws BeansException {
    return applicationContext.getBean(beanClass);
  }

  /**
   * 获取spring.profiles.active
   * @return
   */
  public static String getProfile(){
    return getApplicationContext().getEnvironment().getActiveProfiles()[0];
  }
}
