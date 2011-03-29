package org.osforce.e2.service.system.impl;

import java.util.List;

import org.osforce.e2.dao.system.PermissionDao;
import org.osforce.e2.dao.system.ProjectCategoryDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.ResourceDao;
import org.osforce.e2.dao.system.RoleDao;
import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.service.system.PermissionService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 12:52:12 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	private RoleDao roleDao;
	private ProjectDao projectDao;
	private ResourceDao resourceDao;
	private PermissionDao permissionDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public PermissionServiceImpl() {
	}
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}
	
	public Permission getPermission(Long permissionId) {
		return permissionDao.get(permissionId);
	}
	
	public Permission getPermission(String code) {
		QueryAppender appender = new QueryAppender()
				.equal("permission.resource.code", code);
		return permissionDao.findUnique(appender);
	};
	
	public Permission getPermission(Long resourceId, Long categoryId) {
		QueryAppender appender = new QueryAppender()
				.equal("permission.resource.id", resourceId)
				.equal("permission.category.id", categoryId);
		return permissionDao.findUnique(appender);
	}
	
	public Permission getPermission(String resourceCode, Long categoryId) {
		QueryAppender appender = new QueryAppender()
				.equal("permission.resource.code", resourceCode)
				.equal("permission.category.id", categoryId);
		return permissionDao.findUnique(appender);
	}

	public void createPermission(Permission permission) {
		updatePermission(permission);
	}

	public void updatePermission(Permission permission) {
		if(permission.getRoleId()!=null) {
			Role role = roleDao.get(permission.getRoleId());
			permission.setRole(role);
		}
		if(permission.getProjectId()!=null) {
			Project project = projectDao.get(permission.getProjectId());
			permission.setProject(project);
		}
		if(permission.getResourceId()!=null) {
			Resource resource = resourceDao.get(permission.getResourceId());
			permission.setResource(resource);
		}
		if(permission.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(permission.getCategoryId());
			permission.setCategory(category);
		}
		if(permission.getId()==null) {
			permissionDao.save(permission);
		} else {
			permissionDao.update(permission);
		}
	}

	public void deletePermission(Long permissionId) {
		permissionDao.delete(permissionId);
	}

	public List<Permission> getPermissionList(Long siteId, Long categoryId) {
		QueryAppender appender = new QueryAppender();
		if(siteId!=null) {
			appender.equal("permission.category.site.id", siteId);
		}
		if(categoryId!=null) {
			appender.equal("permission.category.id", categoryId);
		}
		return permissionDao.find(appender);
	}
}
