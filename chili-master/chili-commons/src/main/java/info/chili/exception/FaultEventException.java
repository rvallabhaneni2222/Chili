package info.chili.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anuyalamanchili
 */
public class FaultEventException extends RuntimeException {

    protected FaultEventPayload faultEventPayload;
    protected Boolean enableEmailNotification;

    public FaultEventException(FaultEventPayload faultEventPayload, Boolean enableEmailNotification, Throwable ex) {
        this.faultEventPayload = faultEventPayload;
        this.enableEmailNotification = enableEmailNotification;
    }

    public FaultEventPayload getFaultEventPayload() {
        return faultEventPayload;
    }

    public void setFaultEventPayload(FaultEventPayload faultEventPayload) {
        this.faultEventPayload = faultEventPayload;
    }

    public Boolean isEnableEmailNotification() {
        return enableEmailNotification;
    }

    public void setEnableEmailNotification(Boolean enableEmailNotification) {
        this.enableEmailNotification = enableEmailNotification;
    }

}
