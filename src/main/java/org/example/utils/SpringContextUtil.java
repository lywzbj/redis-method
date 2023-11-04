package org.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getApplicationContext()获取applicationContext对象,applicationContext="
                + SpringContextUtil.applicationContext + "========");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过name获取 Bean.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    //获取激活的环境
    public static String getActiveProfile() {
        String[] activeProfiles = getApplicationContext().getEnvironment().getActiveProfiles();
        if (activeProfiles == null || activeProfiles.length == 0) {
            return "";
        }

        String profile = activeProfiles[0];

        return profile;
    }

    //获取bean工厂，用来实现动态注入bean
    //不能使用其他类加载器加载bean
    //否则会出现异常:类未找到，类未定义
    public static DefaultListableBeanFactory getBeanFactory(){
        return (DefaultListableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
    }

}
