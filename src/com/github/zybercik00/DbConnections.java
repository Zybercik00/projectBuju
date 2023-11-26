package com.github.zybercik00;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.Driver;

public class DbConnections  {

    private static final String userName = "root";
    private static final String url = "jdbc:h2:mem:bujudb";
    private static final String password = "password";
    private static final String driver = "org.h2.Driver";

    public void createConnections(String noteId, String title, String note) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
         Statement statement = connection.createStatement();
         ) {
            String sql = """
                 create table BUJUTABELE
                 (
                     NOTE_ID    VARCHAR(255),
                     TITLE        VARCHAR(255),
                     NOTE   VARCHAR(1000)
                )
            """;
            String query ="insert into BUJUTABELE (" + "NOTE_ID" + ", " + "TITLE" + ", " + "NOTE" + ") " + "values (" + "'" + noteId + "'" + ", " + "'" + title + "'" + ", " + "'" + note + "'" + ")";
            String resultQuery = """
                    select * from BUJUTABELE
                    """;
            statement.executeUpdate(sql);
            statement.executeUpdate(query);
            ResultSet resultSet = statement.executeQuery(resultQuery);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }

        }
    }
            
}

