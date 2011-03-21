package org.osforce.e2.service.system;

import java.util.List;

import org.osforce.e2.entity.system.Permission;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:15:33 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface PermissionService {

	Permission getPermission(Long permissionId);
	
	Permission getPermission(String code);
	
	Permission getPermission(Long resourceId, Long categoryId);
	
	void createPermission(Permission permission);
	
	void updatePermission(Permission permission);
	
	void deletePermission(Long permissionId);
	
	List<Permission> getPermissionList(Long siteId);

	Page<Permission> getPermissionPage(Page<Permission> page, Long siteId);

}
