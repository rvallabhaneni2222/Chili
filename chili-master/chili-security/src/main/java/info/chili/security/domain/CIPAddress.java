/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.domain;

import info.chili.jpa.AbstractEntity;
import info.chili.jpa.validation.Unique;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class CIPAddress extends AbstractEntity {

    /**
     *
     */
    @NotEmpty
    protected String name;
    /**
     *
     */
    @Size(max = 40, message = "{user.passwordHash.length.invalid.msg}")
    @NotEmpty
    @Index(name = "IP_ADDR")
    protected String ipAddress;
    /**
     *
     */
    @Enumerated(EnumType.STRING)
    @Index(name = "IP_ADDR_TYP")
    protected CIPAddressType addressType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public CIPAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(CIPAddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "CIPAddress{" + "name=" + name + ", ipAddress=" + ipAddress + ", type=" + addressType + '}';
    }

}
