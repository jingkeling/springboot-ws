package com.kolin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 * @Author jingkeling
 * @Date 2018/5/9 21:06
 */
@Component
@Lazy(value = false)
public class ApplicationContextRegister implements ApplicationContextAware {


    /**
     * 设置spring上下文
     */

    private static ApplicationContext APPLICATIO_CONTEXT;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATIO_CONTEXT = applicationContext;
    }


    public static ApplicationContext getApplicatioContext() {
        return APPLICATIO_CONTEXT;
    }


}
