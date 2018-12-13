package com.jack;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib库测试
 */
public class CglibTest {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteClassNoInterface.class);
        enhancer.setCallback(new ConcreteClassInterceptor());
        ConcreteClassNoInterface ccni = (ConcreteClassNoInterface) enhancer.create();

        ccni.getConcreteMethodA("a method parameter");
        ccni.getConcreteMethodB(0);
    }
}

class ConcreteClassNoInterface {
    public String getConcreteMethodA(String str) {
        System.out.println("ConcreteMethod A ... " + str);
        return str;
    }

    public int getConcreteMethodB(int n) {
        System.out.println("ConcreteMethod B ... " + n);
        return n + 10;
    }
}

class ConcreteClassInterceptor implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
        System.out.println("Before:" + method);
        Object object = proxy.invokeSuper(obj, arg);
        System.out.println("After:" + method);
        return object;
    }
}
