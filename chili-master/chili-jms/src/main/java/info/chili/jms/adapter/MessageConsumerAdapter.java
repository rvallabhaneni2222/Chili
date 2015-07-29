/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms.adapter;

/**
 * super class that should be extends by all Adpaters to process the CMessage
 * object
 *
 * @author ayalamanchili
 * @param <T>
 */
public abstract class MessageConsumerAdapter<T> {

    public abstract void process(T payload);
}
