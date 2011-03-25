package org.osforce.e2.service.gallery;

import java.util.List;

import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.entity.system.Project;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:34:14 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AlbumService {

	Album getAlbum(Long albumId);
	
	void createAlbum(Album album);
	
	void updateAlbum(Album album);
	
	void deleteAlbum(Long albumId);

	Page<Album> getAlbumPage(Page<Album> page, Project project);

	List<Album> getAlbumList(Project project);
}
