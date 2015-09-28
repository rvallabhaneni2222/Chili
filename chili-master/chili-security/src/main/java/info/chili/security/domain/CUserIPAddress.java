/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.domain;

import info.chili.jpa.AbstractEntity;
import info.chili.jpa.validation.Unique;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ayalamanchili
 */
@Entity
@Audited
@Unique(entity = CIPAddress.class, fields = {"ipAddress"})
public class CUserIPAddress extends AbstractEntity {

    protected String name;
    /**
     *
     */
    @Size(max = 40)
    @NotEmpty
    @Index(name = "USR_IP_ADDR_USRID")
    protected String userId;
    /**
     *
     */
    @Size(max = 40)
    @NotEmpty
    @Index(name = "USR_IP_ADDR")
    protected String ipAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
