package org.osforce.e2.service.system;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:07:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface UserService {

	User getUser(Long userId);
	
	User getUser(String username);
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(Long userId);

	Page<User> getUserPage(Page<User> page);

	Page<User> getUserPage(Page<User> page, Long siteId);

	void register(User user, Project project);

}
