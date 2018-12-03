package com.jack;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class LoadResourceTest {
    public static void main(String[] args) throws Exception {
        System.out.println(LoadResourceTest.class.getResource("/com/jack/User.class"));
        System.out.println(LoadResourceTest.class.getResource("/"));

        System.out.println(LoadResourceTest.class.getClassLoader().getResource(""));
        System.out.println(LoadResourceTest.class.getClassLoader().getResource("/"));

        InputStream in = LoadResourceTest.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);
        System.out.println(properties.getProperty("jdbc.driver"));

        System.out.println("print package class....");
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("com/jack");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            File[] files = new File(url.getPath()).listFiles(pathname -> (pathname.isFile() && pathname.getName().endsWith(".class") || pathname.isDirectory()));

            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
}
