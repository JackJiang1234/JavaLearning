package org.smart4j.chapter3.controller;

import org.smart4j.chapter3.service.HelloService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

@Controller
public class HelloController {

    @Inject
    private HelloService helloService;

    @Action("get:/hello")
    public View hello(Param param){
        return new View("hello.jsp").addModel("welcome_message", helloService.getHelloMessage());
    }
}
