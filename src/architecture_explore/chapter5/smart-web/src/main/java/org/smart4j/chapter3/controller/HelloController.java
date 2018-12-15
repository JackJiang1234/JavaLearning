package org.smart4j.chapter3.controller;

import org.smart4j.chapter3.service.HelloService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

@Controller
public class HelloController {

    @Inject
    private HelloService helloService;

    @Action("get:/hello")
    public View hello(){
        return new View("hello.jsp").addModel("welcome_message", helloService.getHelloMessage());
    }

    @Action("get:/profile")
    public Data getProfile(Param param){
        User u = new User();
        u.setName("jack");
        u.setAge(20);
        return new Data(u);
    }

    private class  User{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}