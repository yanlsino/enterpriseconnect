package org.osforce.e2.service.document.impl;

import java.util.Date;
import java.util.List;

import org.osforce.e2.dao.commons.AttachmentDao;
import org.osforce.e2.dao.document.FileItemDao;
import org.osforce.e2.dao.document.FolderDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.commons.Attachment;
import org.osforce.e2.entity.document.FileItem;
import org.osforce.e2.entity.document.Folder;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.document.FileItemService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:37:35 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class FileItemServiceImpl implements FileItemService {

	private FileItemDao fileItemDao;
	private FolderDao folderDao;
	private UserDao userDao;
	private AttachmentDao attachmentDao;
	
	public FileItemServiceImpl() {
	}
	
	@Autowired
	public void setFileItemDao(FileItemDao fileItemDao) {
		this.fileItemDao = fileItemDao;
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
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public FileItem getFileItem(Long fileItemId) {
		return fileItemDao.get(fileItemId);
	}

	public void createFileItem(FileItem fileItem) {
		updateFileItem(fileItem);
	}

	public void updateFileItem(FileItem fileItem) {
		if(fileItem.getEnteredId()!=null) {
			User enteredBy = userDao.get(fileItem.getEnteredId());
			fileItem.setEnteredBy(enteredBy);
		} 
		if(fileItem.getModifiedId()!=null) {
			User modifiedBy = userDao.get(fileItem.getModifiedId());
			fileItem.setModifiedBy(modifiedBy);
		}
		if(fileItem.getFolderId()!=null) {
			Folder folder = folderDao.get(fileItem.getFolderId());
			fileItem.setFolder(folder);
		}
		if(fileItem.getRealFileId()!=null) {
			Attachment realFile = attachmentDao.get(fileItem.getRealFileId());
			fileItem.setRealFile(realFile);
		}
		Date now = new Date();
		fileItem.setModified(now);
		if(fileItem.getId()==null) {
			fileItem.setEntered(now);
			fileItemDao.save(fileItem);
		} else {
			fileItemDao.update(fileItem);
		}
	}

	public void deleteFileItem(Long fileItemId) {
		fileItemDao.delete(fileItemId);
	}
	
	public List<FileItem> getFileItemList(Long folderId) {
		QueryAppender appender = new QueryAppender()
				.equal("fileItem.folder.id", folderId);
		return fileItemDao.find(appender);
	}
}
