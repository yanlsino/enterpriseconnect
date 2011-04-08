package org.osforce.e2.web.module.calendar.fragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 8, 2011 - 11:58:08 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class CalendarFragment {

	public CalendarFragment() {
	}
	
	public String doActionsView() {
		return "calendar/actions";
	}
	
	public String doCalendarView(@Param String date, FragmentContext context) throws ParseException {
		Date d = new Date();
		Date start = new Date();
		Date end = new Date();
		if(StringUtils.contains(date, "~")) {
			String startStr = StringUtils.substringBefore(date, "~");
			String endStr = StringUtils.substringAfter(date, "~");
			start = DateUtils.parseDate(startStr, new String[]{"yyyy/M/d"});
			end = DateUtils.parseDate(endStr, new String[]{"yyyy/M/d"});
		} else if(StringUtils.isNotBlank(date)) {
			d = DateUtils.parseDate(date, new String[]{"yyyy/M/d"});
			start = DateUtils.ceiling(DateUtils.addDays(d, -1), Calendar.DAY_OF_MONTH);
			end = DateUtils.ceiling(d, Calendar.DAY_OF_MONTH);
		}
		//
		List<List<Date>> month = getCalendarMonth(d);
		context.putRequestData("month", month);
		//
		context.putRequestData("date", d);
		context.putRequestData("start", start);
		context.putRequestData("end", end);
		return "calendar/calendar";
	}
	
	public static List<List<Date>> getCalendarMonth(Date date) {
		List<List<Date>> month = new ArrayList<List<Date>>();
		List<Date> week = Collections.emptyList();
		@SuppressWarnings("unchecked")
		Iterator<Calendar> iter = DateUtils.iterator(date, DateUtils.RANGE_MONTH_SUNDAY);
		while(iter.hasNext()) {
			Calendar calendar = iter.next();
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				week =  new ArrayList<Date>();
				month.add(week);
			}
			week.add(calendar.getTime());
		}
		return month;
	}
	
}
