package org.smart4j.test;

import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;

import java.util.Set;

import static org.junit.Assert.assertFalse;

public class ClassUtilTest {

    @Test
    public void getClassSetTest(){
        Set<Class<?>> set = ClassUtil.getClassSet("org.smart4j.framework");
        assertFalse(set.isEmpty());

        for(Class c : set){
            System.out.println(c.getCanonicalName());
        }
    }

    @Test
    public void getClassNameTest(){
        System.out.println(String.class.getName());
        System.out.println(String.class.getCanonicalName());
        System.out.println(String.class.getSimpleName());
    }
}
