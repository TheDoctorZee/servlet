package com.epam;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        final Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");
        final Context context = tomcat.addWebapp("", new File("src/main/webapp/").getAbsolutePath());
        StandardJarScanner scan = (StandardJarScanner) context.getJarScanner();
        scan.setScanClassPath(true);
        scan.setScanBootstrapClassPath(true); // just guessing here
        scan.setScanAllDirectories(true);
        scan.setScanAllFiles(true);
        tomcat.start();
        tomcat.getServer().await();
    }

}
