package org.osforce.e2.service.message.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.dao.message.MessageDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.message.Message;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.message.MessageService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:21:41 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private MessageDao messageDao;
	
	public MessageServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public Message getMessage(Long messageId) {
		return messageDao.get(messageId);
	}

	public void createMessage(Message message) {
		updateMessage(message);
	}

	public void updateMessage(Message message) {
		if(message.getEnteredId()!=null) {
			User enteredBy = userDao.get(message.getEnteredId());
			message.setEnteredBy(enteredBy);
		}
		if(message.getFromId()!=null) {
			Project from = projectDao.get(message.getFromId());
			message.setFrom(from);
		}
		if(message.getToId()!=null) {
			Project to = projectDao.get(message.getToId());
			message.setTo(to);
		}
		Date now = new Date();
		if(message.getId()==null) {
			message.setEntered(now);
			messageDao.save(message);
		} else {
			messageDao.update(message);
		}
	}

	public void deleteMessage(Long messageId) {
		messageDao.delete(messageId);
	}
	
	public Page<Message> getMessagePage(Page<Message> page, 
			Project project, String box) {
		QueryAppender appender = new QueryAppender();
		if(StringUtils.equals(box, "inbox")) {
			appender.equal("message.to.id", project.getId());
		} else if(StringUtils.equals(box, "sentbox")) {
			appender.equal("message.from.id", project.getId());
		}
		appender.desc("message.entered");
		return messageDao.findPage(page, appender);
	}
	
	public Long countUnreadMessage(Project project) {
		QueryAppender appender = new QueryAppender();
		appender.equal("message.to.id", project.getId());
		appender.equal("message.read", false);
		return messageDao.count(appender);
	}
}
