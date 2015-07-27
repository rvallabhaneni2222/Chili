/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service(value = "cMessagingServiceDefaultConsumer")
public class CMessagingServiceDefaultConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            String msg = ((ObjectMessage) message).toString();
            System.out.println("Message has been consumed : " + msg);
        } else {
            throw new IllegalArgumentException(
                    "Message must be of type TextMessage");
        }
    }
}
