package info.yalamanchili.commons;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public final static Date getNextYear(Date now, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, i);
		return calendar.getTime();
	}

	public final static Date getNextMonth(Date now, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.MONTH, i);
		return calendar.getTime();
	}

	public final static Date getNextWeek(Date now, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_YEAR, i * 7);
		return calendar.getTime();
	}

	public final static Date getNextDay(Date now, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_YEAR, i);
		return calendar.getTime();
	}

	private final static long MS_DAY_LONG = 1000 * 60 * 60 * 24;

	public final static long getBetweenDay(Date beginTime, Date endTime) {
		return (endTime.getTime() - beginTime.getTime()) / MS_DAY_LONG;
	}

}
