package com.homework.epam;

import com.homework.epam.service.Note;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestAopXmlConfigRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop-developer-config.xml");
        Note randomNote = (Note) applicationContext.getBean("randomNote");
        randomNote.notateSomething();
    }

}
