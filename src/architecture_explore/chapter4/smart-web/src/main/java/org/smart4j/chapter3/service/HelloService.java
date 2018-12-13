package org.smart4j.chapter3.service;

import org.smart4j.framework.annotation.Service;

@Service
public class HelloService {
    public String getHelloMessage(){
        return "Hi, this is jack. good luck!";
    }
}
