package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.entity.system.Role;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:10:43 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}
}
