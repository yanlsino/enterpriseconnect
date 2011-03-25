package org.osforce.e2.service.gallery.impl;

import java.util.Date;

import org.osforce.e2.dao.commons.AttachmentDao;
import org.osforce.e2.dao.gallery.AlbumDao;
import org.osforce.e2.dao.gallery.PhotoDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.commons.Attachment;
import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.entity.gallery.Photo;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.gallery.PhotoService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:35:32 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	private UserDao userDao;
	private PhotoDao photoDao;
	private AlbumDao albumDao;
	private AttachmentDao attachmentDao;
	
	
	public PhotoServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	@Autowired
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	@Autowired
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public Photo getPhoto(Long photoId) {
		return photoDao.get(photoId);
	}

	public void createPhoto(Photo photo) {
		updatePhoto(photo);
	}

	public void updatePhoto(Photo photo) {
		if(photo.getEnteredId()!=null) {
			User enteredBy = userDao.get(photo.getEnteredId());
			photo.setEnteredBy(enteredBy);
		}
		if(photo.getModifiedId()!=null) {
			User modifiedBy = userDao.get(photo.getModifiedId());
			photo.setModifiedBy(modifiedBy);
		}
		if(photo.getAlbumId()!=null) {
			Album album = albumDao.get(photo.getAlbumId());
			photo.setAlbum(album);
		}
		if(photo.getRealFileId()!=null) {
			Attachment realFile = attachmentDao.get(photo.getRealFileId());
			photo.setRealFile(realFile);
		}
		Date now = new Date();
		photo.setModified(now);
		if(photo.getId()==null) {
			photo.setEntered(now);
			photoDao.save(photo);
		} else {
			photoDao.update(photo);
		}
	}

	public void deletePhoto(Long photoId) {
		photoDao.delete(photoId);
	}
	
	public Page<Photo> getPhotoPage(Page<Photo> page, Long albumId) {
		QueryAppender appender =new QueryAppender()
				.equal("photo.album.id", albumId)
				.desc("photo.modified");
		return photoDao.findPage(page, appender);
	}
	
}
