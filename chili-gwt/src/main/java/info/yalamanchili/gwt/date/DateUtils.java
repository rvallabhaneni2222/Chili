package info.yalamanchili.gwt.date;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
    private static final String DEFAULT_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ssZZZ";

    public static String toDateString(Date date) {
        DateTimeFormat formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT);
        return formatter.format(date);
    }

    public static Date toDate(String dateString) {
        DateTimeFormat formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT2);
        return formatter.parse(dateString);
    }

    public static String getFormatedDate(String dateString, PredefinedFormat format) {
        return DateTimeFormat.getFormat(format).format(toDate(dateString));
    }
}
