package info.yalamanchili.jpa;

import info.yalamanchili.commons.PropertyFileLoader;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NamingStratagy extends org.hibernate.cfg.ImprovedNamingStrategy {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(NamingStratagy.class);

	protected static Properties abbreviationProperties = new Properties();
	static {
		try {
			abbreviationProperties = PropertyFileLoader
					.loadProperties("abbrevations.properties");
			log.debug("abbrevations.properties");
		} catch (IllegalArgumentException exception) {
			throw new RuntimeException(
					"Error loading abbrevations properties file");
		}
	}

	@Override
	public String classToTableName(String className) {
		if (abbreviationProperties.get(className) == null) {
			return abbreviationProperties.getProperty(className);
		} else {
			return className;
		}
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		if (abbreviationProperties.get(propertyName) == null) {
			return abbreviationProperties.getProperty(propertyName);
		} else {
			return propertyName;
		}
	}
}
