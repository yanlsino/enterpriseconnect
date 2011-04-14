package org.osforce.connect.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.ProjectFeatureDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.UserService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:15:39 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private ProjectFeatureDao projectFeatureDao;
	
	public UserServiceImpl() {
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
	public void setProjectFeatureDao(ProjectFeatureDao projectFeatureDao) {
		this.projectFeatureDao = projectFeatureDao;
	}
	
	public User getUser(Long userId) {
		return userDao.get(userId);
	}

	public void createUser(User user) {
		updateUser(user);
	}

	public void updateUser(User user) {
		if(user.getId()==null) {
			userDao.save(user);
		} else {
			userDao.update(user);
		}
	}

	public void deleteUser(Long userId) {
		userDao.delete(userId);
	}
	
	public User getUser(String username) {
		QueryAppender appender = new QueryAppender();
		appender.equal("user.username", username);
		return userDao.findUnique(appender);
	}
	
	public Page<User> getUserPage(Page<User> page) {
		QueryAppender appender = new QueryAppender();
		appender.desc("user.entered");
		return userDao.findPage(page, appender);
	}
	
	public Page<User> getUserPage(Page<User> page, Long siteId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("user.project.category.site.id", siteId)
				.desc("user.entered");
		return userDao.findPage(page, appender);
	}
	
	public Page<User> getUserPage(Page<User> page, String startWith) {
		QueryAppender appender = new QueryAppender()
				.like("user.username", startWith+"%");
		return userDao.findPage(page, appender);
	}
	
	public void register(User user, Project project) {
		// create user
		Date now = new Date();
		user.setEmail(user.getUsername());
		user.setEntered(now);
		user.setLastLogin(now);
		user.setEnabled(true);
		user.setToken(UUID.randomUUID().toString());
		userDao.save(user);
		// create project
		String uniqueId = cleanEmail(user.getEmail());
		Long count = projectDao.count(new QueryAppender());
		uniqueId =  uniqueId + "-" + ++count;
		project.setTitle(user.getNickname());
		project.setUniqueId(uniqueId);
		project.setModified(now);
		project.setModifiedBy(user);
		project.setEntered(now);
		project.setEnteredBy(user);
		project.setEnabled(true);
		projectDao.save(project);
		// create project feature
		List<ProjectFeature> features = project.getFeatures();
		for(ProjectFeature feature : features) {
			feature.setProject(project);
			projectFeatureDao.save(feature);
		}
		// update user project
		user.setProject(project);
		userDao.update(user);
	}
	
	protected String cleanEmail(String email) {
		email = StringUtils.lowerCase(email);
		String uniqueId = StringUtils.substringBefore(email, "@");
		StringBuffer buffer = new StringBuffer();
		for(char c : uniqueId.toCharArray()) {
			if((c>='0' && c<='9') || (c>='a' && c<='z') || c=='_' || c=='-') {
				buffer.append(c);
			} else {
				buffer.append('-');
			}
		}
		return buffer.toString();
	}
}
