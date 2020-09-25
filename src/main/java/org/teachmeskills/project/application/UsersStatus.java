package org.teachmeskills.project.application;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.teachmeskills.project.RootConfiguration;
import org.teachmeskills.project.application.ConsoleApplication;
import org.teachmeskills.project.application.actions.CommonAction;

import java.util.Map;

public enum UsersStatus {

    ADMIN {

        @Override
        public String getUsersType() { return "Администратор"; }

    },

    USER {

        @Override
        public String getUsersType() { return "Ползователь"; }

    };



    public abstract String getUsersType();
}
