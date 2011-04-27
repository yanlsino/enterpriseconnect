package org.osforce.connect.task.calendar;

import java.util.Map;

import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 2:00:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class EventActivityStreamTask extends AbstractTask {

	private EventService eventService;
	private ActivityService activityService;

	public EventActivityStreamTask() {
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long eventId = (Long) context.get("eventId");
		Event event = eventService.getEvent(eventId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(eventId);
		activity.setEntity(Event.NAME);
		activity.setType(Event.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(event.getProjectId());
		activity.setEnteredId(event.getModifiedId());
		activityService.createActivity(activity);
	}

}
