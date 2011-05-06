package org.osforce.connect.service.document.impl;

import java.util.Date;
import java.util.List;

import org.osforce.connect.dao.document.FolderDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.document.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:40:53 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class FolderServiceImpl implements FolderService {

	private FolderDao folderDao;
	private UserDao userDao;
	private ProjectDao projectDao;
	
	public FolderServiceImpl() {
	}
	
	@Autowired
	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Folder getFolder(Long folderId) {
		return folderDao.get(folderId);
	}

	public void createFolder(Folder folder) {
		updateFolder(folder);
	}

	public void updateFolder(Folder folder) {
		if(folder.getModifiedId()!=null) {
			User modifiedBy = userDao.get(folder.getModifiedId());
			folder.setModifiedBy(modifiedBy);
		}
		if(folder.getEnteredId()!=null) {
			User enteredBy = userDao.get(folder.getEnteredId());
			folder.setEnteredBy(enteredBy);
		}
		if(folder.getProjectId()!=null) {
			Project project = projectDao.get(folder.getProjectId());
			folder.setProject(project);
		}
		if(!folder.getRoot() && folder.getParentId()!=null) {
			Folder parent = folderDao.get(folder.getParentId());
			folder.setParent(parent);
		}
		Date now = new Date();
		folder.setModified(now);
		if(folder.getId()==null) {
			folder.setEntered(now);
			folderDao.save(folder);
		} else {
			folderDao.update(folder);
		}
	}

	public void deleteFolder(Long folderId) {
		folderDao.delete(folderId);
	}
	
	public List<Folder> getRootForlders(Long projectId) {
		return folderDao.findRoot(projectId);
	}
}
