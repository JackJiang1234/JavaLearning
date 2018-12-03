package com.jack;


import java.io.InputStream;
import java.util.Properties;

public class LoadResourceTest {
    public static void main(String[] args) throws Exception{
        System.out.println(LoadResourceTest.class.getResource(""));
        System.out.println(LoadResourceTest.class.getResource("/"));

        System.out.println(LoadResourceTest.class.getClassLoader().getResource(""));
        System.out.println(LoadResourceTest.class.getClassLoader().getResource("/"));

        InputStream in = LoadResourceTest.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);
        System.out.println(properties.getProperty("jdbc.driver"));
    }
}
