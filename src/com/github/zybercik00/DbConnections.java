package com.github.zybercik00;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.h2.Driver;

public class DbConnections  {

    static final String userName = "root";
    static final String url = "jdbc:h2:mem:bujudb";
    static final String password = "password";
    static final String driver = "org.h2.Driver";


    public Connection createConnections() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driver);

            System.out.println("Connecting to database....");
            connection = DriverManager.getConnection(url, userName, password);

            System.out.println("Creating table in database");
            statement = connection.createStatement();

            String sql = """
                create table BUJUTABELE
                (
                    NOTE_ID    BIGINT not null,
                    TITLE        VARCHAR(255),
                    NOTE   VARCHAR(1000)
                )
                """;
            statement.executeUpdate(sql);
            System.out.println("Table was created: " + statement);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Done!");
        return connection;
    }
}
