package org.osforce.e2.service.profile.impl;

import java.util.Date;

import org.osforce.e2.dao.commons.AttachmentDao;
import org.osforce.e2.dao.profile.ProfileDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.commons.Attachment;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.profile.ProfileService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:05:12 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	private UserDao userDao;
	private ProfileDao profileDao;
	private ProjectDao projectDao;
	private AttachmentDao attachmentDao;
	
	public ProfileServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public Profile getProfile(Long profileId) {
		return profileDao.get(profileId);
	}
	
	public Profile getProfile(Project project) {
		QueryAppender appender = new QueryAppender();
		appender.equal("profile.project.id", project.getId());
		return profileDao.findUnique(appender);
	}
	
	public Profile viewProfile(Project project, User user) {
		return getProfile(project);
	}

	public void createProfile(Profile profile) {
		updateProfile(profile);
	}

	public void updateProfile(Profile profile) {
		if(profile.getEnteredId()!=null) {
			User enteredBy = userDao.get(profile.getEnteredId());
			profile.setEnteredBy(enteredBy);
		}
		if(profile.getModifiedId()!=null) {
			User modifiedBy = userDao.get(profile.getModifiedId());
			profile.setModifiedBy(modifiedBy);
		}
		if(profile.getProjectId()!=null) {
			Project project = projectDao.get(profile.getProjectId());
			profile.setProject(project);
		}
		if(profile.getLogoId()!=null) {
			Attachment logo = attachmentDao.get(profile.getLogoId());
			profile.setLogo(logo);
		}
		Date now = new Date();
		profile.setModified(now);
		if(profile.getId()==null) {
			profile.setEntered(now);
			profileDao.save(profile);
		} else {
			profileDao.update(profile);
		}
	}

	public void deleteProfile(Long profileId) {
		profileDao.delete(profileId);
	}

	public Page<Profile> getProfilePage(Page<Profile> page, ProjectCategory category) {
		QueryAppender appender = new QueryAppender();
		appender.equal("profile.project.category.id", category.getId()).desc("profile.entered");
		return profileDao.findPage(page, appender);
	}
	
	public Page<Profile> getConcernedProfilePage(Page<Profile> page,
			ProjectCategory category, User user) {
		return profileDao.findConcernedPage(page, category, user);
	}
	
	public Page<Profile> getTopProfilePage(Page<Profile> page,
			ProjectCategory category) {
		
		return null;
	}
}
