package org.osforce.e2.service.gallery;

import org.osforce.e2.entity.gallery.Photo;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 11:33:25 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PhotoService {

	Photo getPhoto(Long photoId);
	
	void createPhoto(Photo photo);
	
	void updatePhoto(Photo photo);
	
	void deletePhoto(Long photoId);

	Page<Photo> getPhotoPage(Page<Photo> page, Long albumId);
}
