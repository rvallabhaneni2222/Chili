/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.filters;

/**
 *
 * @author ayalamanchili
 */
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import info.chili.service.jrs.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: @johnwilander Date: 2010-nov-07
 */
public class CsrfProtectionFilter implements ResourceFilter, ContainerRequestFilter {

    private static final Log logger = LogFactory.getLog(CsrfProtectionFilter.class);
    public static final String SIMPLE_COOKIE_NAME = "cookieToken";
    public static final int RANDOM_COOKIE_NAME_LENGTH = 16;
    public static final int RANDOM_COOKIE_VALUE_LENGTH = 16;
    public static final String PARAM_TOKEN = "paramToken";
    private HttpServletRequest httpServletRequest;

    public CsrfProtectionFilter(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public ContainerRequest filter(ContainerRequest containerRequest) throws ServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("Checking anti-csrf cookie against anti-csrf request parameter");
        }
        if (isTokenOK()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Anti-csrf cookie and/or request parameter OK");
            }
            return containerRequest;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Anti-csrf cookie and/or request parameter bad");
            }
            throw new ServiceException(ServiceException.StatusCode.INVALID_REQUEST, "SYSTEM", "invalid.request", "CXRF attack");
        }
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    public ContainerResponseFilter getResponseFilter() {
        return null;  // No response filter
    }

    // Private help methods
    private String getCsrfProtectionTokenCookie() {
        int numOfSimpleCookies = 0, numOfRandomCookies = 0;
        boolean foundRandomCookie = false;
        Cookie[] cookies = httpServletRequest.getCookies();
        String csrfProtectionCookieValue = null;
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (logger.isDebugEnabled()) {
                logger.debug("Checking cookie: " + cookieName);
            }
            if (!foundRandomCookie && SIMPLE_COOKIE_NAME.equals(cookieName)) {
                csrfProtectionCookieValue = cookie.getValue();
                numOfSimpleCookies++;
            } else if (cookieName.length() == RANDOM_COOKIE_NAME_LENGTH
                    && isValidAlphaNumericString(cookieName)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Found random cookie: " + cookieName);
                }
                foundRandomCookie = true;  // Do no longer look for simple anti-csrf cookies
                csrfProtectionCookieValue = cookie.getValue();
                numOfRandomCookies++;
            }
        }
        if (numOfSimpleCookies > 1 || numOfRandomCookies > 1) {
            if (logger.isWarnEnabled()) {
                logger.warn("Got multiple anti-csrf cookies");
            }
            throw new ServiceException(ServiceException.StatusCode.INVALID_REQUEST, "SYSTEM", "invalid.request", "CXRF attack");
        }
        if (logger.isDebugEnabled()) {
            if (foundRandomCookie) {
                logger.debug("Found random cookie");
            } else {
                logger.debug("Found simple cookie");
            }
        }
        return csrfProtectionCookieValue;
    }

    private String getCsrfProtectionTokenFormParam() {
        return httpServletRequest.getParameter(PARAM_TOKEN);
    }

    private boolean isTokenOK() {
        String cookie = getCsrfProtectionTokenCookie();
        String param = getCsrfProtectionTokenFormParam();
        if (cookie != null && !"".equals(cookie) && cookie.equals(param)) {
            return true;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Anti-csrf cookie value == " + cookie + " and request parameter == " + param);
            }
            return false;
        }
    }

    private static final int MAX_SIZE = 1024;
    private static final String CHARS;

    static {
        int numOfChars = 36;
        char[] buf = new char[numOfChars];
        for (int index = 0; index < 10; index++) {
            buf[index] = (char) ('0' + index);
        }
        for (int index = 10; index < numOfChars; index++) {
            buf[index] = (char) ('a' + index - 10);
        }
        CHARS = new String(buf);
    }

    public static boolean isValidAlphaNumericString(String string) {
        int length = string.length();
        if (length > MAX_SIZE) {
            return false;
        }
        for (int index = 0; index < length; index++) {
            if (CHARS.indexOf(string.charAt(index)) == -1) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Found illegal cookie character: " + string.charAt(index));
                }
                return false;
            }
        }
        return true;
    }
}
