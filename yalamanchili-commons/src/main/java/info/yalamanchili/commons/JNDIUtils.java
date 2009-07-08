package info.yalamanchili.commons;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class JNDIUtils.
 */
public class JNDIUtils {
	private static final Log log = LogFactory.getLog(JNDIUtils.class);
	/** The properties. */
	protected static Properties properties = new Properties();

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Instantiates a new jNDI utils.
	 */
	public JNDIUtils() {
		loadProperties();
	}

	/**
	 * Load properties.
	 */
	public void loadProperties() {
		properties = PropertyLoader
				.loadProperties("yalamanchili-jndi.properties");
	}

	/**
	 * Gets the secure initial context.
	 * 
	 * @param session
	 *            the session
	 * 
	 * @return the secure initial context
	 * 
	 * @throws NamingException
	 *             the naming exception
	 */
	protected Context getSecureInitialContext(HttpSession session)
			throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, properties.get(
				JNDIConstants.SECURE_INITIAL_CONTEXT_FACTORY).toString());
		props.put(Context.URL_PKG_PREFIXES, properties.get(
				JNDIConstants.SECURE_URL_PKG_PREFIXES).toString());
		props.put(Context.PROVIDER_URL, properties.get(
				JNDIConstants.SECURE_PROVIDER_URL).toString());
		props.setProperty(Context.SECURITY_PRINCIPAL, session.getAttribute(
				"username").toString());
		props.setProperty(Context.SECURITY_CREDENTIALS, session.getAttribute(
				"password").toString());
		return new InitialContext(props);
	}

	/**
	 * Gets the secure initial context.
	 * 
	 * @return the secure initial context
	 * 
	 * @throws NamingException
	 *             the naming exception
	 */
	protected Context getSecureInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, properties.get(
				JNDIConstants.SECURE_INITIAL_CONTEXT_FACTORY).toString());
		props.put(Context.URL_PKG_PREFIXES, properties.get(
				JNDIConstants.SECURE_URL_PKG_PREFIXES).toString());
		props.put(Context.PROVIDER_URL, properties.get(
				JNDIConstants.SECURE_PROVIDER_URL).toString());
		props.setProperty(Context.SECURITY_PRINCIPAL, properties.get(
				JNDIConstants.SECURE_SECURITY_PRINCIPAL).toString());
		props.setProperty(Context.SECURITY_CREDENTIALS, properties.get(
				JNDIConstants.SECURE_SECURITY_CREDENTIALS).toString());
		return new InitialContext(props);
	}

	/**
	 * Gets the initial context.
	 * 
	 * @return the initial context
	 * 
	 * @throws NamingException
	 *             the naming exception
	 */
	protected Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put(Context.URL_PKG_PREFIXES, properties.get(
				JNDIConstants.URL_PKG_PREFIXES).toString());
		props.put(Context.PROVIDER_URL, properties.get(
				JNDIConstants.PROVIDER_URL).toString());
		return new InitialContext(props);
	}

	/**
	 * Lookup.
	 * 
	 * @param jndiName
	 *            the jndi name
	 * 
	 * @return the object
	 */
	public Object lookup(String jndiName) {
		Object object;
		try {
			Context ctx = getInitialContext();
			object = ctx.lookup(jndiName);
		} catch (NamingException ne) {
			throw new RuntimeException("JNDI Lookup Error for " + jndiName, ne);
		}
		return object;
	}
}
