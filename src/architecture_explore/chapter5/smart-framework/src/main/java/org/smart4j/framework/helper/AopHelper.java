package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 方法拦截帮助类
 */
public final class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            targetMap.forEach((targetClass, proxyList) -> {
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            });
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }

    /**
     * 获取目标类与其对应的代理对角实例的映射列表
     * */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap)  {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        proxyMap.forEach((proxyClass, targetClassSet) ->{
            targetClassSet.forEach(targetClass ->{
                Proxy proxy = null;
                try {
                    proxy = (Proxy) proxyClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                if (targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else{
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            });
        });
        return targetMap;
    }

    /**
     * 获取切面代理类与应用切面目标类的Map,一个切面代理类可对应多个目标类
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        proxyClassSet.forEach(proxyClass ->{
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        });
        return proxyMap;
    }

    /**
     * 根据Aspect value获取所有应用切面目标类class集合
     * */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect)  {
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }

        return targetClassSet;
    }
}
