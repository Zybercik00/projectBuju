package com.github.zybercik00;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.h2.Driver;

public class DbConnections  {

    private static final String userName = "root";
    private static final String url = "jdbc:h2:mem:bujudb";
    private static final String password = "password";
    private static final String driver = "org.h2.Driver";


    public Connection createConnections() {
        Connection connection = null;
        try {
            Class.forName(driver);

            System.out.println("Connecting to database....");
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Done!");
        return connection;
    }

    public void createTable() throws SQLException {
        Connection connection = null;
        try (Statement statement = connection.createStatement()) {
            String sql = """
                create table BUJUTABELE
                (
                    NOTE_ID    BIGINT not null,
                    TITLE        VARCHAR(255),
                    NOTE   VARCHAR(1000)
                )
                """;
            statement.executeUpdate(sql);
        }
    }
            
}

