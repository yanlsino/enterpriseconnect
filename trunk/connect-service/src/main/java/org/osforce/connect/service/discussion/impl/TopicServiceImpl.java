package org.osforce.connect.service.discussion.impl;

import java.util.Date;

import org.osforce.connect.dao.discussion.ForumDao;
import org.osforce.connect.dao.discussion.ReplyDao;
import org.osforce.connect.dao.discussion.TopicDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:12:32 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

	private UserDao userDao;
	private TopicDao topicDao;
	private ReplyDao replyDao;
	private ForumDao forumDao;
	
	
	public TopicServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	
	@Autowired
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Autowired
	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}
	
	public Topic viewTopic(Long topicId) {
		return topicDao.get(topicId);
	}

	public Topic getTopic(Long topicId) {
		return topicDao.get(topicId);
	}

	public void createTopic(Topic topic) {
		updateTopic(topic);
	}

	public void updateTopic(Topic topic) {
		if(topic.getAnswerId()!=null) {
			Reply answer = replyDao.get(topic.getAnswerId());
			topic.setAnswer(answer);
		}
		if(topic.getEnteredId()!=null) {
			User enteredBy = userDao.get(topic.getEnteredId());
			topic.setEnteredBy(enteredBy);
		}
		if(topic.getModifiedId()!=null) {
			User modifiedBy = userDao.get(topic.getModifiedId());
			topic.setModifiedBy(modifiedBy);
		}
		if(topic.getForumId()!=null) {
			Forum forum = forumDao.get(topic.getForumId());
			topic.setForum(forum);
		}
		Date now = new Date();
		topic.setModified(now);
		if(topic.getId()==null) {
			topic.setEntered(now);
			topicDao.save(topic);
		} else {
			topicDao.update(topic);
		}
	}

	public void deleteTopic(Long topicId) {
		topicDao.delete(topicId);
	}
	
	public Page<Topic> getTopicPage(Page<Topic> page) {
		QueryAppender appender = new QueryAppender();
		appender.desc("topic.entered");
		return topicDao.findPage(page, appender);
	}
	
	public Page<Topic> getTopicPage(Page<Topic>  page, Long forumId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("topic.forum.id", forumId)
				.desc("topic.modified");
		return topicDao.findPage(page, appender);
	}
	
	public Page<Topic> getTopicPage(Page<Topic> page, Project project) {
		QueryAppender appender = new QueryAppender();
		if(project!=null) {
			appender.equal("topic.forum.project.id", project.getId());
		}
		appender.desc("topic.entered");
		return topicDao.findPage(page, appender);
	}
}
