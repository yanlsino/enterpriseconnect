package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.PermissionDao;
import org.osforce.connect.entity.system.Permission;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:13:43 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class PermissionDaoImpl extends AbstractDao<Permission> 
	implements PermissionDao {

	public PermissionDaoImpl() {
		super(Permission.class);
	}
}
