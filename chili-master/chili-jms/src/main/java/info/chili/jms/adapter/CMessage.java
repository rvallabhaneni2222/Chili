/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.jms.adapter;

import java.io.Serializable;

/**
 * holder to transfer payload from app to jms layer
 *
 * @author ayalamanchili
 * @param <T>
 */
public class CMessage<T> implements Serializable {

    protected String adapterName;
    protected T payload;

    public CMessage(String adapterName, T payload) {
        this.adapterName = adapterName;
        this.payload = payload;
    }

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "AdapterMessage{" + "adapterName=" + adapterName + ", payload=" + payload + '}';
    }
}
