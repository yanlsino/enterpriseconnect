package org.osforce.connect.service.discussion.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.discussion.ForumDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:51:45 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ForumServiceImpl implements ForumService {

	private UserDao userDao;
	private ForumDao forumDao;
	private ProjectDao projectDao;
	
	public ForumServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Forum getForum(Long forumId) {
		return forumDao.get(forumId);
	}

	public void createForum(Forum forum) {
		updateForum(forum);
	}

	public void updateForum(Forum forum) {
		if(forum.getEnteredId()!=null) {
			User enteredBy = userDao.get(forum.getEnteredId());
			forum.setEnteredBy(enteredBy);
		} 
		if(forum.getModifiedId()!=null) {
			User modifiedBy = userDao.get(forum.getModifiedId());
			forum.setModifiedBy(modifiedBy);
		}
		if(forum.getProjectId()!=null) {
			Project project = projectDao.get(forum.getProjectId());
			forum.setProject(project);
		}
		Date now = new Date();
		forum.setModified(now);
		if(forum.getId()==null) {
			forum.setEntered(now);
			forumDao.save(forum);
		} else {
			forumDao.update(forum);
		}
	}

	public void deleteForum(Long forumId) {
		forumDao.delete(forumId);
	}
	
	public List<Forum> getForumList(Project project) {
		QueryAppender appender = new QueryAppender();
		appender.equal("forum.project.id", project.getId());
		return forumDao.find(appender);
	}
}
