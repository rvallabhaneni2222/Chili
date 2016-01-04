/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.profile;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * this should be extended and add @componenet and @aspects
 *
 * @author phani
 */
@EnableAsync
public abstract class ProfileInterceptor {

    private static final Log log = LogFactory.getLog(ProfileInterceptor.class);

    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        if (enabled() && !ignoreMethod(pjp.getSignature().toShortString())) {
            long start = System.currentTimeMillis();
            Object output = pjp.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            if (persistLogData()) {
                persistProfileLog(pjp.getSignature().toShortString(), elapsedTime);
            }
            if (log.isDebugEnabled()) {
                if (!pjp.getSignature().toShortString().contains("ProfileLog")) {
                    log.debug("Method " + pjp.getSignature().toShortString() + " execution time: " + elapsedTime + " milliseconds.");
                }
            }
            return output;
        } else {
            return pjp.proceed();
        }
    }

    protected static List<String> ignoreMethods = new ArrayList<>();

    static {
        ignoreMethods.add("EnableLoginInterceptor");
        ignoreMethods.add("TemplateService");
        ignoreMethods.add("logBefore");
        ignoreMethods.add("profile");
    }

    protected boolean ignoreMethod(String name) {
        for (String key : ignoreMethods) {
            if (name.contains(key)) {
                return true;
            }
        }
        return false;
    }

    @Async
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
}
