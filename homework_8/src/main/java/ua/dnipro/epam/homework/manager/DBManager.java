package ua.dnipro.epam.homework.manager;

import ua.dnipro.epam.homework.exception.ConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static Connection connection;

    private static Properties readPropFile (String filePath){
        Properties properties = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new ConnectionException("Can`t read file", e);
        }
        return properties;
    }

    public static Connection getConnection(){
        if (connection != null){
            return connection;
        }
        Properties prop = readPropFile("src/main/resources/application.properties");

        final String JDBC_DRIVER = prop.getProperty("database.driver");
        final String DATABASE_URL = prop.getProperty("database.url");
        final String DATABASE_USERNAME = prop.getProperty("database.username");
        final String DATABASE_PASSWORD = prop.getProperty("database.password");

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new ConnectionException("Not connection", e);
        }
    }

}