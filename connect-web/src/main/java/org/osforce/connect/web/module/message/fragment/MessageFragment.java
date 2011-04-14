package org.osforce.connect.web.module.message.fragment;

import org.osforce.connect.entity.message.Message;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.message.MessageService;
import org.osforce.connect.service.system.ProjectService;
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
 * @create Feb 16, 2011 - 7:18:58 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MessageFragment {

	private ProjectService projectService;
	private MessageService messageService;
	
	public MessageFragment() {
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public String doActionsView() {
		return "message/actions";
	}
	
	public String doListView(@Param("inbox") String  box, Page<Message> page, 
			User user, Project project, FragmentContext context) {
		page = messageService.getMessagePage(page, project, box);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "message/messages_list";
	}
	
	public String doInfoView(Project project, FragmentContext context) {
		Long unreadCount = messageService.countUnreadMessage(project);
		if(unreadCount==0) {
			return "commons/blank";
		}
		context.putRequestData("unreadCount", unreadCount);
		return "message/message_info";
	}
	
	public String doMessageView(@Param Long messageId,
			User user, FragmentContext context) {
		Message message = messageService.getMessage(messageId);
		message.setReadBy(user);
		message.setRead(true);
		messageService.updateMessage(message);
		context.putRequestData(AttributeKeys.MESSAGE_KEY_READABLE, message);
		return "message/message";
	}
	
	public String doFormView(@Param Long messageId, @Param Long toId,
		 @Param Long fromId, Project project, User user, FragmentContext context) {
		Message message = new Message();
		message.setEnteredBy(user);
		if(fromId!=null) {
			Project from = projectService.getProject(fromId);
			message.setFrom(from);
		} else {
			message.setFrom(project);
		}
		if(toId!=null) {
			Project to = projectService.getProject(toId);
			message.setTo(to);
		}
		if(messageId!=null) {
			Message original = messageService.getMessage(messageId);
			message.setSubject(original.getSubject());
			message.setTo(original.getFrom());
			message.setReply(true);
		}
		context.putRequestData(AttributeKeys.MESSAGE_KEY_READABLE, message);
		return "message/message_form";
	}
}