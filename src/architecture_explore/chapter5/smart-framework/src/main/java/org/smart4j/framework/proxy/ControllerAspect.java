package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;

import java.lang.reflect.Method;

/**
 * 拦截Controller所有的方法
 * */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("------------------end--------------------");
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug("-----------------bgein-----------------");
        LOGGER.debug(String.format("class: %s", cls.getName()));
        LOGGER.debug(String.format("method:%s", method.getName()));
        begin = System.currentTimeMillis();
    }
}
