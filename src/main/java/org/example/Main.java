package org.example;


import org.example.controller.AuthController;
import org.example.db.DataBase;
import org.example.db.InitDataBase;
import org.example.springConfig.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(SpringConfig.class);
        DataBase dataBase=(DataBase)applicationContext.getBean("dataBase");

        InitDataBase initDataBase= (InitDataBase) applicationContext.getBean("initDataBase");
        initDataBase.adminInit();
        initDataBase.addCompanyCard();
        AuthController authController=(AuthController) applicationContext.getBean("authController");
        authController.start();

    }
}
