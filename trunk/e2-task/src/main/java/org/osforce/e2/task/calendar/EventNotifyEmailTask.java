package org.osforce.e2.task.calendar;

import java.util.Map;

import org.osforce.e2.entity.calendar.Event;
import org.osforce.e2.service.calendar.EventService;
import org.osforce.e2.task.support.AbstractEmailTask;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 15, 2011 - 11:17:21 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class EventNotifyEmailTask extends AbstractEmailTask {
	private static final String TEMPLATE_EVENT_NOTIFY_SUBJECT = "email/event_notify_subject"; 
	private static final String TEMPLATE_EVENT_NOTIFY_CONTENT = "email/event_notify_content";
	
	private Configuration configuration;
	private EventService eventService;
	
	public EventNotifyEmailTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long eventId = (Long) context.get("eventId");
		Event event = eventService.getEvent(eventId);
		context.put("event", event);
		context.put("site", event.getProject().getCategory().getSite());
		String subject = FreemarkerUtil.render(configuration, 
				TEMPLATE_EVENT_NOTIFY_SUBJECT, context); 
		String content = FreemarkerUtil.render(configuration, 
				TEMPLATE_EVENT_NOTIFY_CONTENT, context);
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.addTo(event.getEnteredBy().getNickname(), 
				event.getEnteredBy().getEmail());
		// TODO add members to event notification list
	}
}
