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
			@Param Long categoryId, FragmentContext context) {
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(siteId);
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		if(categoryId==null && categories.size()>0) {
			categoryId = categories.get(0).getId();
		}
		//
		List<Permission> permissions = permissionService.getPermissionList(siteId, categoryId);
		context.putRequestData(AttributeKeys.PERMISSION_LIST_KEY_READABLE, permissions);
		return "system/permissions_list";
	}
	
	public String doFormView(@Param Long permissionId, @Param Long categoryId, 
			@Param Long roleId, @Param Long siteId, Project project, FragmentContext context) {
		List<Resource> resources = resourceService.getResourceList();
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(siteId);
		Permission permission = new Permission();
		permission.setProject(project);
		if(categoryId==null) {
			categoryId = categories.get(0).getId();
		}
		permission.setCategoryId(categoryId);
		List<Role> roles = roleService.getRoleList(permission.getCategoryId());
		if(!roles.isEmpty()) {
			permission.setRole(roles.get(roles.size()-1));
		}
		if(permissionId!=null) {
			permission = permissionService.getPermission(permissionId);
		}
		if(roleId!=null) {
			Role role = roleService.getRole(roleId);
			permission.setRole(role);
		}
		// filter resources
		List<Permission> permissions = permissionService.getPermissionList(siteId, categoryId);
		context.putRequestData(AttributeKeys.PERMISSION_KEY_READABLE, permission);
		context.putRequestData(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		context.putRequestData(AttributeKeys.RESOURCE_LIST_KEY_READABLE, resources);
		context.putRequestData(AttributeKeys.PERMISSION_LIST_KEY_READABLE, permissions);
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/permission_form";
	}
}
