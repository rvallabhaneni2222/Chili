/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.profile;

import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * this should be extended and add @componenet and @aspects
 *
 * @author phani
 */
public abstract class ProfileInterceptor {

    private static final Log log = LogFactory.getLog(ProfileInterceptor.class);

    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        if (!enabled()) {
            return pjp.proceed();
        } else {
            long start = System.currentTimeMillis();
            Object output = pjp.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            if (!ignoreMethod(pjp.getSignature().toShortString())) {
                if (log.isDebugEnabled()) {
                    log.debug("Method " + pjp.getSignature().toShortString() + " execution time: " + elapsedTime + " milliseconds.");
                }
                if (persistLogData()) {
                    persistProfileLog(pjp.getSignature().toShortString(), elapsedTime);
                }
            }
            return output;
        }
    }

    public void persistProfileLog(String serviceName, Long executionTime) {
        getProfileLogDao().save(new ProfileLog(serviceName, executionTime));
    }

    protected boolean enabled() {
        return false;
    }

    protected boolean persistLogData() {
        return false;
    }
    protected ProfileLogDao profileLogDao;

    protected ProfileLogDao getProfileLogDao() {
        if (profileLogDao == null) {
            profileLogDao = ProfileLogDao.instance();
        }
        return profileLogDao;
    }

    protected List<String> ingoreMethods() {
        return Collections.emptyList();
    }

    protected boolean ignoreMethod(String name) {
        for (String key : ingoreMethods()) {
            if (name.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
