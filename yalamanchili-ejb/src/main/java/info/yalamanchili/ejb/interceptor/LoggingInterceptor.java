package info.yalamanchili.ejb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingInterceptor {
	private static final Log log = LogFactory.getLog(LoggingInterceptor.class);

	@AroundInvoke
	public Object loggingInterceptor(InvocationContext ctx) throws Exception {
		log.debug("entering:" + ctx.getMethod().getName());
		try {
			return ctx.proceed();
		} finally {
			log.debug("exiting:" + ctx.getMethod().getName());
		}
	}

}
