package org.teachmeskills.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.teachmeskills.project.application.ConsoleApplication;
import org.teachmeskills.project.entitiy.Transport;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new  AnnotationConfigApplicationContext(RootConfiguration.class).getBean("consoleApplication", ConsoleApplication.class).start();
    }
}
