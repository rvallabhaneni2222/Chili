/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class XSSFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(XSSFilter.class);

    /**
     * @param request
     * @return 
     * @see ContainerRequestFilter#filter(ContainerRequest)
     */
    @Override
    public ContainerRequest filter(ContainerRequest request) {
        // Clean the query strings
        cleanParams(request.getQueryParameters());

        // Clean the headers
        cleanParams(request.getRequestHeaders());

        // Clean the cookies
        cleanParams(request.getCookieNameValueMap());

        // Return the cleansed request
        return request;
    }

    /**
     * Apply the XSS filter to the parameters
     *
     * @param parameters
     * @param type
     */
    private void cleanParams(MultivaluedMap<String, String> parameters) {
        LOG.debug("Checking for XSS Vulnerabilities: {}", parameters);

        for (Map.Entry<String, List<String>> params : parameters.entrySet()) {
            String key = params.getKey();
            List<String> values = params.getValue();

            List<String> cleanValues = new ArrayList<String>();
            for (String value : values) {
                cleanValues.add(stripXSS(value));
            }

            parameters.put(key, cleanValues);
        }

        LOG.debug("XSS Vulnerabilities removed: {}", parameters);
    }

    /**
     * Strips any potential XSS threats out of the value
     *
     * @param value
     * @return
     */
    public String stripXSS(String value) {
        if (value == null) {
            return null;
        }

        // Use the ESAPI library to avoid encoded attacks.
        value = ESAPI.encoder().canonicalize(value);

        // Avoid null characters
        value = value.replaceAll("\0", "");

        // Clean out HTML
        value = Jsoup.clean(value, Whitelist.none());

        return value;
    }
}
