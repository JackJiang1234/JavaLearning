package com.jack;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BuildTest {
    public static void main(String[] args){

        Object object = new Object();
        HashCodeBuilder.reflectionHashCode(object);
        EqualsBuilder.reflectionEquals(object, object);
    }
}
