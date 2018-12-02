package com.jack;

import com.alibaba.fastjson.JSON;

public class JsonParseTest {

    public static String bean2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr,Class<T> objClass){
        return JSON.parseObject(jsonStr, objClass);
    }

    public static void main(String[] args){
        User u = new User();
        u.setName("abc");
        u.setAge(2);

        String json = bean2Json(u);
        System.out.println(json);

        User u2 = json2Bean(json, User.class);
        System.out.println(u2);
    }
}

class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    @Override
    public String toString() {
        return String.format("name=%s, age=%d", this.name, this.age);
    }
}
