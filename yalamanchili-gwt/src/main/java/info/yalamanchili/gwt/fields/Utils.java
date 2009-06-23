package info.yalamanchili.gwt.fields;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Utils {
	public static String getShortDate(Date date) {
		if (date == null) {
			return "";
		}
		return DateTimeFormat.getShortDateFormat().format(date);
	}
}
