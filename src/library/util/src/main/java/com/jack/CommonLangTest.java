package com.jack;

import org.apache.commons.lang3.StringUtils;

public class CommonLangTest {
    public static void main(String[] args){
        System.out.println(StringUtils.isBlank("   ")); //true
        System.out.println(StringUtils.isEmpty("   ")); //false
    }
}
