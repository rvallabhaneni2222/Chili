/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.identity.config;

import info.chili.spring.SpringContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayalamanchili
 */
@Component
@PropertySources({
    @PropertySource("classpath:default-chili-identity-services.properties"),
    @PropertySource(value = "classpath:chili-identity-services.properties", ignoreResourceNotFound = true)
})
public class IdentityServiceConfiguration {

    @Value("#{environment.getProperty('chili.identity.service.host')}")
    protected String host;

    @Value("#{environment.getProperty('chili.identity.service.path')}")
    protected String path;

    @ManagedAttribute
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @ManagedAttribute
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceUrl() {
        return getHost() + getPath();
    }

    public static IdentityServiceConfiguration instance() {
        return SpringContext.getBean(IdentityServiceConfiguration.class);
    }

}
