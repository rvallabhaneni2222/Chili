/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms;

import info.chili.spring.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessageToDefaultDestination(final String message) {
        jmsTemplate.convertAndSend(message);
    }

    public static MessageProducer instance() {
        return SpringContext.getBean(MessageProducer.class);
    }
}
