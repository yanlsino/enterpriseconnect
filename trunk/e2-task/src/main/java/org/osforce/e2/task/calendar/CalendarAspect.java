package org.osforce.e2.task.calendar;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.e2.entity.calendar.Event;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 15, 2011 - 11:11:41 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class CalendarAspect {

	private Task eventNotifyEmailTask;
	
	public CalendarAspect() {
	}
	
	@Autowired
	@Qualifier("eventNotifyEmailTask")
	public void setEventNotifyEmailTask(Task eventNotifyEmailTask) {
		this.eventNotifyEmailTask = eventNotifyEmailTask;
	}
	
	@AfterReturning("execution(* org.osforce.e2.service.calendar.EventService.notifyEvent(..))")
	public void eventNotify(JoinPoint jp) {
		Event event = (Event) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("eventId", event);
		eventNotifyEmailTask.doAsyncTask(context);
	}
}
