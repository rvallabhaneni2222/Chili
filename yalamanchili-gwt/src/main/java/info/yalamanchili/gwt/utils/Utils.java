package info.yalamanchili.gwt.utils;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	
	/**
	 * Gets the short date.
	 * 
	 * @param date the date
	 * 
	 * @return the short date
	 */
	public static String getShortDate(Date date) {
		if (date == null) {
			return "";
		}
		return DateTimeFormat.getShortDateFormat().format(date);
	}
}
