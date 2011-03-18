package org.osforce.e2.task.message;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.osforce.e2.entity.message.Message;
import org.osforce.e2.service.message.MessageService;
import org.osforce.e2.task.support.AbstractEmailTask;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 3:21:12 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MessageSendEmailTask extends AbstractEmailTask {
	private static final String TEMPLATE_MESSAGE_CREATE_SUBJECT = "email/message_create_subject.ftl";
	private static final String TEMPLATE_MESSAGE_CREATE_CONTENT = "email/message_create_content.ftl";
	
	private Configuration configuration;
	private MessageService messageService;
	
	public MessageSendEmailTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws TemplateException, IOException, MessagingException {
		Long messageId = (Long) context.get("messageId");
		Message message = messageService.getMessage(messageId);
		context.put("message", message);
		String subject = FreemarkerUtil.render(configuration, 
				TEMPLATE_MESSAGE_CREATE_SUBJECT, context); 
		String content = FreemarkerUtil.render(configuration, 
				TEMPLATE_MESSAGE_CREATE_CONTENT, context);
		helper.setSubject(subject);
		helper.setText(content, true);
		helper.addTo(message.getTo().getEnteredBy().getEmail(), 
				message.getTo().getEnteredBy().getNickname());
	}
	
}
