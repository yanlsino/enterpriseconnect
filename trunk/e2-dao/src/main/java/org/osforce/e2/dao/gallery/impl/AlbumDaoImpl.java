package org.osforce.e2.dao.gallery.impl;

import org.osforce.e2.dao.gallery.AlbumDao;
import org.osforce.e2.entity.gallery.Album;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:27:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AlbumDaoImpl extends  AbstractDao<Album>
	implements AlbumDao {
	
	public AlbumDaoImpl() {
		super(Album.class);
	}

}
