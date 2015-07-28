/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms.adapter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author ayalamanchili
 */
@Service
public class AdapterMessageConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            AdapterMessage adapterMessage;
            try {
                adapterMessage = (AdapterMessage) ((ObjectMessage) message).getObject();
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Adapter Message has been consumed : " + adapterMessage.toString());
        } else {
            throw new IllegalArgumentException(
                    "Message must be of type ObjectMessage");
        }
    }

}
