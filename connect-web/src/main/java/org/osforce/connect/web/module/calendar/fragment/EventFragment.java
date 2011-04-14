package org.osforce.connect.web.module.calendar.fragment;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.osforce.commons.date.DateUtil;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 3:53:58 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class EventFragment {

	private EventService eventService;
	
	public EventFragment() {
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	public String doRecentView(Page<Event> page, Project project, 
			FragmentContext context) {
		page = eventService.getEventPage(page, project, new Date());
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "calendar/events_recent";
	}
	
	public String doListView(@Param String date, 
			Project project, FragmentContext context) throws ParseException {
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
		List<Event> events = eventService.getEventList(project, start, DateUtils.addDays(end, 1));
		context.putRequestData(AttributeKeys.EVENT_LIST_KEY_READABLE, events);
		//
		context.putRequestData("date", d);
		context.putRequestData("start", start);
		context.putRequestData("end", end);
		return "calendar/events_list";
	}
	
	public String doFormView(@Param Long eventId, @Param String date,
			User user, Project project, FragmentContext context) {
		Event event = new Event();
		event.setEnteredBy(user);
		event.setModifiedBy(user);
		event.setProject(project);
		if(StringUtils.isNotBlank(date)) {
			Date start = DateUtil.parse(date+" 9:00");
			Date end = DateUtil.parse(date+" 17:00");
			event.setStart(start);
			event.setEnd(end);
		}
		if(eventId!=null) {
			event = eventService.getEvent(eventId);
		}
		context.putRequestData(AttributeKeys.EVENT_KEY_READABLE, event);
		return "calendar/event_form";
	}
	
}
