package info.chili.gwt.date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import info.chili.gwt.config.ChiliClientConfig;
import java.util.Date;

public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
    private static final String DEFAULT_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ssZZZ";

    private static final String DEFAULT_FORMAT3 = "dd-MMM-YYYY'T'HH:mm:ss.SSSZZZ";
    private static final String DEFAULT_FORMAT4 = "dd-MMM-YYYY'T'HH:mm:ssZZZ";
    /**
     * unix
     */
    private static final String DEFAULT_FORMAT5 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String DEFAULT_FORMAT_TZ = "yyyy-MM-dd";

    public static String toDateString(Date date) {
        DateTimeFormat formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT);
        if (ChiliClientConfig.instance().getTimeZone() != null) {
            return formatter.format(date, ChiliClientConfig.instance().getTimeZone());
        } else {
            return formatter.format(date);
        }
    }

    public static void main(String... str) {
        System.out.println("ddd:" + toDate("2016-02-04T00:00:00Z"));
    }

    public static Date toDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        dateString = dateString.trim();
        DateTimeFormat formatter;
        switch (dateString.length()) {
            case 29:
                formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT);
                break;
            case 25:
                formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT2);
                break;
            case 20:
                formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT5);
                break;
            case 30:
                formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT3);
                break;
            case 26:
                formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT4);
                break;
            default:
                throw new RuntimeException(":" + dateString + ": is not supported date string format");
        }
        return formatter.parse(dateString);
    }
// this fixes the issue with tz moves dates back by a day

    public static Date toDateTZ(String dateString) {
        dateString = dateString.substring(0, dateString.indexOf("T"));
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        DateTimeFormat formatter = DateTimeFormat.getFormat(DEFAULT_FORMAT_TZ);
        return formatter.parse(dateString);
    }

    public static String getFormatedDate(String dateString, PredefinedFormat format) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        if (ChiliClientConfig.instance().getTimeZone() != null) {
//            do we need the time zone since the time zone parsing error is fixed above
            return DateTimeFormat.getFormat(format).format(toDate(dateString), ChiliClientConfig.instance().getTimeZone());
        } else {
            return DateTimeFormat.getFormat(format).format(toDate(dateString));
        }
    }

    public static String formatDate(String dateString) {
        String date = getFormatedDate(dateString, PredefinedFormat.DATE_SHORT);
        String[] dates = date.split("-");
        String formatteddate = "";
        formatteddate = formatteddate.concat(dates[dates.length - 2]).concat("/");
        formatteddate = formatteddate.concat(dates[dates.length - 1]).concat("/");
        formatteddate = formatteddate.concat(dates[0]);
        return formatteddate;
    }
}
