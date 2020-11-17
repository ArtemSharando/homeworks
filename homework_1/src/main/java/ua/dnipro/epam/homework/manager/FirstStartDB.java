package ua.dnipro.epam.homework.manager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class FirstStartDB {

    @PostConstruct
    public void load() {
        // Load your data here.
    }
}
