/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ayalamanchili
 */
@Document(collection = "ipaddressrecords")
public class IPAddressRecord {

    /**
     *
     */
    protected String userId;
    /**
     *
     */
    protected String ipAddress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "CUserIPAddress{" + "userId=" + userId + ", ipAddress=" + ipAddress + '}';
    }

}
