package ua.dnipro.epam.homework.annotation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    private Map<String, Class> beansMap = new HashMap();

    Logger logger = Logger.getLogger(BeanPostProcessorImpl.class);

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        for (Method declaredMethod : bean.getClass().getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Timed.class)) {
                beansMap.put(beanName, bean.getClass());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = beansMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    long before = System.nanoTime();
                    Object returnValue = method.invoke(bean, args);
                    long after = System.nanoTime();
                    System.out.println("Method time: " + (after - before));
                    return returnValue;
                }
            });
        }
        return bean;
    }
}

