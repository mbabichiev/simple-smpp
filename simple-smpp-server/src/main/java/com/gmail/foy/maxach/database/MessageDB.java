package com.gmail.foy.maxach.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class MessageDB {

    private static final Logger log = LoggerFactory.getLogger(MessageDB.class);
    private static final String DB_NAME = "smpp";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection;


    public Connection getConnection() {
        return connection;
    }


    public MessageDB() {

        // try to find mysql driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // try to connect to mysql
        try {
            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD);
            log.info("Connected to database '{}'", DB_NAME);

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // try to create table "messages"
        try {
            Statement statement = connection.createStatement();
            String SQL_CREATE_TABLE =
                    "CREATE TABLE messages" +
                            "(" +
                                "id SERIAL PRIMARY KEY," +
                                "publish_date BIGINT NOT NULL," +
                                "message TEXT NOT NULL" +
                            ");";
            statement.executeUpdate(SQL_CREATE_TABLE);
            log.info("Table 'messages' was created");

        } catch (SQLException e) {
            if(e.getErrorCode() == 1050) {
                log.info("Table 'messages' is already exists");
            }
            else {
                log.info("Creating table with error code: {}", e.getErrorCode());
                e.printStackTrace();
            }
        }

    }


    public int createStatement(String mysqlStatement) {

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(mysqlStatement);
            return result;

        } catch (SQLException e) {
            if(e.getErrorCode() != 0) {
                log.info("Statement with error code: {}", e.getErrorCode());
                e.printStackTrace();
            }
            return -1;
        }
    }
}
