package org.osforce.e2.web.module.calendar.fragment;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.date.DateUtil;
import org.osforce.e2.entity.calendar.Event;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.calendar.EventService;
import org.osforce.e2.web.AttributeKeys;
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
	
	public String doListView() {
		//List<Event> events = eventService.getEventBySiteAndDate();
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
