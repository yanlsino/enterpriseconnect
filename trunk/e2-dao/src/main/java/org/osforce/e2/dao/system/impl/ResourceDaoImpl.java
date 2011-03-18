package org.osforce.e2.dao.system.impl;

import org.osforce.e2.dao.system.ResourceDao;
import org.osforce.e2.entity.system.Resource;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:55:09 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ResourceDaoImpl extends AbstractDao<Resource> 
	implements ResourceDao {

	public ResourceDaoImpl() {
		super(Resource.class);
	}
}
