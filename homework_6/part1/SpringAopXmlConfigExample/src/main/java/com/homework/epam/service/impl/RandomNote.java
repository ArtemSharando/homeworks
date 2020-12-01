package com.homework.epam.service.impl;

import com.homework.epam.annotation.Timed;
import com.homework.epam.aspect.Logging;
import com.homework.epam.service.Note;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RandomNote implements Note {
    private static final Logger LOG = LogManager.getLogger(Logging.class.getName());

    private final String message = "Hello";
    //random amount of repeating. From 1 to 9;
    private final int repeat = (int) (1 + (Math.random() * 9));

    public RandomNote() {

    }

    @Timed
    public void notateSomething() {
        for (int i = 0; i < repeat; i++) {
            LOG.info("Note =  " + message);
        }
    }

    @Timed
    public void notateSomething(int number) {
        for (int i = 0; i < number; i++) {
            LOG.info("Note =  " + message);
        }
    }
}
