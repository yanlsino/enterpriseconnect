package org.osforce.e2.dao.map.impl;

import org.osforce.e2.dao.map.MarkerDao;
import org.osforce.e2.entity.map.Marker;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:47:22 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MarkerDaoImpl extends AbstractDao<Marker> 
	implements MarkerDao {

	public MarkerDaoImpl() {
		super(Marker.class);
	}
}
