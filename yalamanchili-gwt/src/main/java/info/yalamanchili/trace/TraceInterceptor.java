package info.yalamanchili.trace;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.seam.log.Log;
import org.jboss.seam.log.Logging;

/**
 * @author jgilbert01
 */
public class TraceInterceptor {

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ctx) throws Exception {
		Boolean top = isTop(ctx);
		Log logger = getLog(ctx);
		try {
			if (logger.isDebugEnabled()) {
				pre(ctx, logger);
				Object output = ctx.proceed();
				post(ctx, output, logger);
				return output;
			} else {
				return ctx.proceed();
			}
		} catch (Throwable e) {
			throw new Exception(e);
		}
	}

	// ---------------------------------------------------------------

	@PostConstruct
	public void postConstruct(InvocationContext ctx) {
		logLifecycle(ctx, "postConstruct: ");
	}

	@PreDestroy
	public void preDestroy(InvocationContext ctx) {
		logLifecycle(ctx, "preDestroy: ");
	}

	@PrePassivate
	public void prePassivate(InvocationContext ctx) {
		logLifecycle(ctx, "prePassivate: ");
	}

	@PostActivate
	public void postActivate(InvocationContext ctx) {
		logLifecycle(ctx, "postActivate: ");
	}

	// ---------------------------------------------------------------

	protected static final String TOP = TraceInterceptor.class.getName()
			+ ".top";

	/**
	 * Determine if this is the top most instance of the interceptor.
	 */
	protected Boolean isTop(InvocationContext ctx) {
		Boolean top = (Boolean) ctx.getContextData().get(TOP);
		if (top == null) {
			top = Boolean.TRUE;
			ctx.getContextData().put(TOP, top);
		}
		return top;
	}

	/**
	 * Logging for life cycle methods
	 */
	protected void logLifecycle(InvocationContext ctx, String prefix) {
		Log logger = getLog(ctx);
		if (logger.isDebugEnabled()) {
			logger.debug(prefix + ctx.getTarget());
		}
	}

	/**
	 * Log arguments
	 */
	protected void pre(InvocationContext ctx, Log logger) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Invoking: ");
		createTraceName(buffer, ctx);
		Object[] args = ctx.getParameters();
		if (args != null) {
			buffer.append("Parameters:\n");
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null) {
					buffer.append(args[i].toString());
					buffer.append("\n");
				}
			}
		}
		logger.debug(buffer.toString());
	}

	/**
	 * Log output
	 */
	protected void post(InvocationContext ctx, Object output, Log logger) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Returning: ");
		createTraceName(buffer, ctx);
		if (output != null) {
			buffer.append(output.toString());
		}
		logger.debug(buffer.toString());
	}

	/**
	 * Identifies what component and method is being logged.
	 */
	protected void createTraceName(StringBuffer buffer, InvocationContext ctx) {
		buffer.append(ctx.getTarget().getClass().getName());
		buffer.append(".");
		buffer.append(ctx.getMethod().getName());
		buffer.append("\n");
	}

	/**
	 * Get logger for class being invoked
	 */
	protected Log getLog(InvocationContext ctx) {
		return Logging.getLog(ctx.getTarget().getClass());
	}
}