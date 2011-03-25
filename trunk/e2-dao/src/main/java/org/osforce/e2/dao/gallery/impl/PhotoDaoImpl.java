package org.osforce.e2.dao.gallery.impl;

import org.osforce.e2.dao.gallery.PhotoDao;
import org.osforce.e2.entity.gallery.Photo;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:26:32 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class PhotoDaoImpl extends AbstractDao<Photo>
	implements PhotoDao {

	public PhotoDaoImpl() {
		super(Photo.class);
	}
	
}
