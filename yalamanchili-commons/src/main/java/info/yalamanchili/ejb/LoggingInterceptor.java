package info.yalamanchili.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class LoggingInterceptor.
 */
public class LoggingInterceptor {
	
	/** The Constant log. */
	private static final Log log = LogFactory.getLog(LoggingInterceptor.class);

	/**
	 * Logging interceptor.
	 * 
	 * @param ctx the ctx
	 * 
	 * @return the object
	 * 
	 * @throws Exception the exception
	 */
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
