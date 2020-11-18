package ua.dnipro.epam.homework.annotation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ua.dnipro.epam.homework.controller.RegistrationController;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    private final Map<String,Class<?>> beansMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Timed.class)){
            beansMap.put(beanName, bean.getClass());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beansMap.get(beanName);
        if(beanClass!=null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                long before = System.nanoTime();
                Object returnValue = method.invoke(bean, args);
                long after = System.nanoTime();
                logger.info("Method time: "+ (after-before));
                return returnValue;
            });
        }
        return bean;
    }
}

