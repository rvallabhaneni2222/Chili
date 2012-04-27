package info.yalamanchili.gwt.date;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtils {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";

	public static String toDateString(Date date) {
		DateTimeFormat formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT);
		return formatter.format(date);
	}
}
