package ru.job4j.grabber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.grabber.store.PsqlStore;

import java.io.InputStream;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Properties;

public class StartUI {
    private static final Logger LOG = LoggerFactory.getLogger(StartUI.class.getName());

    public static void main(String[] args) {
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            try (PsqlStore store = new PsqlStore(properties)) {
                store.save(new Post(
                        "test title",
                        "test link",
                        "test description",
                        LocalDateTime.now()
                ));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
