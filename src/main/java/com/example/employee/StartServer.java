package com.example.employee;

import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class StartServer {
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("Starting server at http://localhost:8080/employee.html");
        tomcat.start();
        tomcat.getServer().await();
    }
}
