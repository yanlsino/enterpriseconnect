package org.osforce.e2.web.module.system.fragment;

import java.util.List;

import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.service.system.PermissionService;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.e2.service.system.ResourceService;
import org.osforce.e2.service.system.RoleService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 23, 2011 - 11:37:43 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class PermissionFragment {

	private RoleService roleService;
	private ResourceService resourceService;
	private PermissionService permissionService;
	private ProjectCategoryService projectCategoryService;
	
	public PermissionFragment() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doListView(@Param Long siteId,
			Page<Permission> page, FragmentContext context) {
		page = permissionService.getPermissionPage(page, siteId);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/permissions_list";
	}
	
	public String doFormView(@Param Long permissionId, @Param Long categoryId, 
			@Param Long siteId, Project project, FragmentContext context) {
		List<Resource> resources = resourceService.getResourceList();
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(siteId);
		Permission permission = new Permission();
		permission.setProject(project);
		if(categoryId==null) {
			categoryId = categories.get(0).getId();
		}
		permission.setCategoryId(categoryId);
		if(permissionId!=null) {
			permission = permissionService.getPermission(permissionId);
		}
		List<Role> roles = roleService.getRoleList(permission.getCategoryId());
		if(!roles.isEmpty()) {
			permission.setRole(roles.get(roles.size()-1));
		}
		context.putRequestData(AttributeKeys.PERMISSION_KEY_READABLE, permission);
		context.putRequestData(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		context.putRequestData(AttributeKeys.RESOURCE_LIST_KEY_READABLE, resources);
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/permission_form";
	}
}
