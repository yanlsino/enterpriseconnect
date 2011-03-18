package org.osforce.e2.task.calendar;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.osforce.e2.entity.calendar.Event;
import org.osforce.e2.service.calendar.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventScheduler {

	private EventService eventService;
	
	public EventScheduler() {
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Scheduled(cron="0 0 * * * *")
	public void doSchedule() {
		Date today = new Date();
		Date dayAfterTomorrow = DateUtils.addDays(today, 3);
		List<Event> events = eventService.getEventList(today, dayAfterTomorrow);
		for(Event event : events) {
			eventService.notifyEvent(event);
		}
	}
}
