package org.osforce.e2.service.discussion.impl;

import java.util.Date;

import org.osforce.e2.dao.discussion.ReplyDao;
import org.osforce.e2.dao.discussion.TopicDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.discussion.Reply;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.discussion.ReplyService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:56:07 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	private UserDao userDao;
	private TopicDao topicDao;
	private ReplyDao replyDao;
	
	
	public ReplyServiceImpl() {
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
	
	public Reply getReply(Long replyId) {
		return replyDao.get(replyId);
	}

	public void createReply(Reply reply) {
		updateReply(reply);
	}

	public void updateReply(Reply reply) {
		if(reply.getEnteredId()!=null) {
			User enteredBy = userDao.get(reply.getEnteredId());
			reply.setEnteredBy(enteredBy);
		}
		if(reply.getModifiedId()!=null) {
			User modifiedBy = userDao.get(reply.getModifiedId());
			reply.setModifiedBy(modifiedBy);
		}
		if(reply.getTopicId()!=null) {
			Topic topic = topicDao.get(reply.getTopicId());
			reply.setTopic(topic);
		}
		if(reply.getQuoteId()!=null) {
			Reply quote = replyDao.get(reply.getQuoteId());
			reply.setQuote(quote);
		}
		Date now = new Date();
		reply.setEntered(now);
		if(reply.getId()==null) {
			reply.setModified(now);
			replyDao.save(reply);
		} else {
			replyDao.update(reply);
		}
	}

	public void deleteReply(Long replyId) {
		replyDao.delete(replyId);
	}
	
	public Page<Reply> getReplyPage(Page<Reply> page, Long topicId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("reply.topic.id", topicId).asc("topic.entered");
		return replyDao.findPage(page, appender);
	}
}
