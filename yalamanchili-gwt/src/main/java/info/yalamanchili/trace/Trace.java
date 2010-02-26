package info.yalamanchili.trace;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jboss.seam.annotations.intercept.Interceptors;

/**
 * Add the TraceInterceptor to a Seam component.
 * 
 * @author jgilbert01
 */
@Interceptors(TraceInterceptor.class)
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Trace {
}
