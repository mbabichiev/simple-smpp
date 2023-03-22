package com.gmail.foy.maxach.services;

import com.gmail.foy.maxach.SMPPServerSimulator;
import com.gmail.foy.maxach.models.Message;
import com.gmail.foy.maxach.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageService {

    private static final MessageRepository messageRepository = new MessageRepository();
    private static final Logger log = LoggerFactory.getLogger(SMPPServerSimulator.class);


    public void create(String text) {
        log.info("Create message: '{}'", text);
        Message message = new Message(System.currentTimeMillis(), text);
        messageRepository.create(message);
    }
}
