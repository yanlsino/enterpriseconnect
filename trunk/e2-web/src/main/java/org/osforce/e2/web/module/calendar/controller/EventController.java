package org.osforce.e2.web.module.calendar.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.e2.entity.calendar.Event;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.service.calendar.EventService;
import org.osforce.e2.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 3:57:47 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/calendar")
public class EventController {

	private EventService eventService;
	
	public EventController() {
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Event event) {
		event.setType(Event.TYPE_ACTIVITY);
		if(event.getId()==null) {
			eventService.createEvent(event);
		} else {
			eventService.updateEvent(event);
		}
		return Collections.singletonMap("id", event.getId());
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public @ResponseBody Map<String, Long> merge(Event event) {
		Event persisted = eventService.getEvent(event.getId());
		persisted.setStart(event.getStart());
		persisted.setEnd(event.getEnd());
		persisted.setAllDay(event.getAllDay());
		eventService.updateEvent(persisted);
		return Collections.singletonMap("id", event.getId());
	}
	
	@RequestMapping(value="/events", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> list(@RequestParam Long start, 
			@RequestParam Long end, WebRequest request) {
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, 
				WebRequest.SCOPE_REQUEST);
		List<Event> events = eventService.getEventList(
				project, new Date(start*1000), new Date(end*1000));
		List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
		for(Event e:events) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("id", e.getId());
			model.put("title", e.getTitle());
			// here use UNIX timestamp
			model.put("start", e.getStart().getTime()/1000);
			model.put("end", e.getEnd().getTime()/1000);
			model.put("allDay", e.getAllDay());
			eventList.add(model);
		}
		return eventList;
	}
}
