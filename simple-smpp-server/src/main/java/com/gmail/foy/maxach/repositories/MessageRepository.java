package com.gmail.foy.maxach.repositories;

import com.gmail.foy.maxach.database.MessageDB;
import com.gmail.foy.maxach.models.Message;

public class MessageRepository {

    private static final MessageDB messageDB = new MessageDB();

    public void create(Message message) {

        String SQL_INSERT_INTO_TABLE = "INSERT INTO messages" +
                "(" +
                    "publish_date," +
                    "message" +
                ") VALUES (" +
                "" + message.getTime() + "," +
                "'" + message.getMessage() + "')";

        messageDB.createStatement(SQL_INSERT_INTO_TABLE);
    }

}
