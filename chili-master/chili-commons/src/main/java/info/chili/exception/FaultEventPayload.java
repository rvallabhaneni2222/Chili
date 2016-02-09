/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.exception;

import java.io.Serializable;

/**
 *
 * @author ayalamanchili
 */
public class FaultEventPayload implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    protected Object payload;
    /**
     *
     */
    protected String payloadType;

    public FaultEventPayload(Object payload, String payloadType) {
        this.payload = payload;
        this.payloadType = payloadType;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

}
