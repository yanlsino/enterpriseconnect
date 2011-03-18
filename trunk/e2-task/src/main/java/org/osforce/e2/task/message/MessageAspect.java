package org.osforce.e2.task.message;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.e2.entity.message.Message;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:47:59 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class MessageAspect {
	
	private Task messageSendEmailTask;
	
	public MessageAspect() {
	}
	
	@Autowired
	@Qualifier("messageSendEmailTask")
	public void setMessageSendEmailTask(Task messageSendEmailTask) {
		this.messageSendEmailTask = messageSendEmailTask;
	}
	
	@AfterReturning("execution(* org.osforce.e2.service.message.MessageService.createMessage(..))")
	public void sendMessage(JoinPoint jp) {
		Message message = (Message) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("siteId", message.getFrom().getCategory().getSiteId());
		context.put("messageId", message.getId());
		messageSendEmailTask.doAsyncTask(context);
	}
	
}
