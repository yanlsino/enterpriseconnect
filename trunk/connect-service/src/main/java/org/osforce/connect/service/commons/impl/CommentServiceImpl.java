package org.osforce.connect.service.commons.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.commons.CommentDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.commons.Comment;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.CommentService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 3:16:08 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	private UserDao userDao;
	private CommentDao commentDao;
	
	public CommentServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public Comment getComment(Long commentId) {
		return commentDao.get(commentId);
	}

	public void createComment(Comment comment) {
		updateComment(comment);
	}

	public void updateComment(Comment comment) {
		if(comment.getEnteredId()!=null) {
			User enteredBy = userDao.get(comment.getEnteredId());
			comment.setEnteredBy(enteredBy);
		}
		if(comment.getModifiedId()!=null) {
			User modifiedBy = userDao.get(comment.getModifiedId());
			comment.setModifiedBy(modifiedBy);
		}
		Date now = new Date();
		comment.setModified(now);
		if(comment.getId()==null) {
			comment.setEntered(now);
			commentDao.save(comment);
		} else {
			commentDao.update(comment);
		}
	}

	public void deleteComment(Long commentId) {
		commentDao.delete(commentId);
	}
	
	public List<Comment> getCommentList(Long linkedId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("comment.linkedId", linkedId)
				.equal("comment.entity", entity);
		return commentDao.find(appender);
	}
	
	public Long countComment(Long linkedId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("comment.linkedId", linkedId)
				.equal("comment.entity", entity);
		return commentDao.count(appender);
	}
}
