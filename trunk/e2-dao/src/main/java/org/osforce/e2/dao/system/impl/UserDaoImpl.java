package org.osforce.e2.dao.system.impl;

import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 10:09:37 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

}
