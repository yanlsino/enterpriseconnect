package org.osforce.connect.web.module.system.fragment;

import java.util.List;

import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 19, 2011 - 4:33:10 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class RoleFragment {

	private RoleService roleService;
	private ProjectCategoryService projectCategoryService;
	
	public RoleFragment() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doListView(@Param Long siteId,
			Page<Role> page, FragmentContext context) {
		page = roleService.getRolePage(page, siteId);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/roles_list";
	}
	
	public String doFormView(@Param Long roleId, @Param Long siteId, 
			FragmentContext context) {
		Role role = new Role();
		if(roleId!=null) {
			role = roleService.getRole(roleId);
			siteId = role.getCategory().getSite().getId();
		}
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(siteId);
		context.putRequestData(AttributeKeys.ROLE_KEY_READABLE, role);
		context.putRequestData("categories", categories);
		return "system/role_form";
	}
}
