/**
 *
 */
package org.osforce.connect.service.oauth.impl;

import org.osforce.connect.dao.oauth.AuthorizationDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.oauth.Authorization;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.oauth.AuthorizationService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 3:59:14 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

	private UserDao userDao;
	private AuthorizationDao authorizationDao;

	public AuthorizationServiceImpl() {
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	public Authorization getAuthorization(Long authorizationId) {
		return authorizationDao.get(authorizationId);
	}

	public Authorization getAuthorization(String target, Long userId) {
		QueryAppender appender = new QueryAppender()
				.equal("authorization.target", target)
				.equal("authorization.user.id", userId);
		return authorizationDao.findUnique(appender);
	}

	public void createAuthorization(Authorization authorization) {
		updateAuthorization(authorization);
	}

	public void updateAuthorization(Authorization authorization) {
		if(authorization.getUserId()!=null) {
			User user = userDao.get(authorization.getUserId());
			authorization.setUser(user);
		}
		if(authorization.getId()==null) {
			authorizationDao.save(authorization);
		} else {
			authorizationDao.update(authorization);
		}
	}

	public void deleteAuthorization(Long authorizationId) {
		authorizationDao.delete(authorizationId);
	}

}
