package org.osforce.e2.service.gallery.impl;

import java.util.Date;
import java.util.List;

import org.osforce.e2.dao.gallery.AlbumDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.gallery.AlbumService;
import org.osforce.platform.dao.support.AbstractDao;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:42:22 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class AlbumServiceImpl extends AbstractDao<Album>
	implements AlbumService {

	private UserDao userDao;
	private AlbumDao albumDao;
	private ProjectDao projectDao;
	
	public AlbumServiceImpl() {
		super(Album.class);
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public Album getAlbum(Long albumId) {
		return albumDao.get(albumId);
	}

	public void createAlbum(Album album) {
		updateAlbum(album);
	}

	public void updateAlbum(Album album) {
		if(album.getEnteredId()!=null) {
			User enteredBy = userDao.get(album.getEnteredId());
			album.setEnteredBy(enteredBy);
		}
		if(album.getModifiedId()!=null) {
			User modifiedBy = userDao.get(album.getModifiedId());
			album.setModifiedBy(modifiedBy);
		}
		if(album.getProjectId()!=null) {
			Project project = projectDao.get(album.getProjectId());
			album.setProject(project);
		}
		Date now = new Date();
		album.setEntered(now);
		if(album.getId()==null) {
			album.setEntered(now);
			albumDao.save(album);
		} else {
			albumDao.update(album);
		}
	}

	public void deleteAlbum(Long albumId) {
		albumDao.delete(albumId);
	}
	
	public Page<Album> getAlbumPage(Page<Album> page, Project project) {
		QueryAppender appender = new QueryAppender()
				.equal("album.project.id", project.getId())
				.desc("album.modified");
		return albumDao.findPage(page, appender);
	}
	
	public List<Album> getAlbumList(Project project) {
		QueryAppender appender = new QueryAppender()
				.equal("album.project.id", project.getId())
				.desc("album.modified");
		return albumDao.find(appender);
	}
	
}
