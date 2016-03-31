/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.identity.jrsc;

import info.chili.identity.config.IdentityServiceConfiguration;
import info.chili.identity.jrs.IdentityResource;
import info.chili.security.domain.CUser;
import info.chili.spring.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ayalamanchili
 */
@Component
@Scope("prototype")
public class IdentityServiceClient {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected IdentityServiceConfiguration identityServiceConfiguration;

    public CUser createUser(CUser user) {
        String url = identityServiceConfiguration.getServiceUrl() + IdentityResource.PATH + IdentityResource.CREATE_USER;
        return restTemplate.postForEntity(url, user, CUser.class).getBody();
    }

    public static IdentityServiceClient instance() {
        return SpringContext.getBean(IdentityServiceClient.class);
    }
}
