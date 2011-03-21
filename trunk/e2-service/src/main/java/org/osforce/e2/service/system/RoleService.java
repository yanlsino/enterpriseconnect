package org.osforce.e2.service.system;

import java.util.List;

import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.Site;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:08:52 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface RoleService {

	Role getRole(Long roleId);
	
	Role getRole(Long categoryId, Integer roleLevel);
	
	Role getRole(String code, Long categoryId);
	
	void createRole(Role role);
	
	void updateRole(Role role);
	
	void delete(Long roleId);

	Page<Role> getRolePage(Page<Role> page, Long siteId);

	List<Role> getRoleList(Long categoryId);

	List<Role> getRoleList(Site site);

}
