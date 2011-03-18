package org.osforce.e2.dao.map.impl;

import org.osforce.e2.dao.map.MapDao;
import org.osforce.e2.entity.map.Map;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:45:55 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MapDaoImpl extends AbstractDao<Map> 
	implements MapDao {

	protected MapDaoImpl() {
		super(Map.class);
	}

}
