package org.osforce.connect.web.module.system.fragment;

import java.util.List;

import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 19, 2011 - 3:53:42 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class UserFragment {

	private UserService userService;
	private RoleService roleService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	public UserFragment() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doListView(@Param Long siteId, 
			Page<User> page, FragmentContext context) {
		if(siteId==null) {
			page = userService.getUserPage(page);
		} else {
			page = userService.getUserPage(page, siteId);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/users_list";
	}
	
	public String doFormView(@Param Long userId, FragmentContext context) {
		User user = userService.getUser(userId);
		context.putRequestData(AttributeKeys.USER_KEY_READABLE, user);
		return "system/user_form";
	}
	
	public String doLoginFormView(FragmentContext context) {
		User user = new User();
		context.putRequestData(AttributeKeys.USER_KEY_READABLE, user);
		return "system/login_form";
	}
	
	public String doRegisterFormView(@Pref("people-features") String templateCode,
			@Pref("people") String categoryCode, Site site, FragmentContext context) {
		ProjectCategory category = projectCategoryService.getProjectCategory(site, categoryCode);
		User user = new User();
		context.putRequestData(AttributeKeys.USER_KEY_READABLE, user);
		//
		Template template = templateService.getTemplate(category.getId(), templateCode);
		List<ProjectFeature> modules = ModuleUtil.parseToModules(template.getContent());
		Project project = new Project();
		// set project category
		project.setCategory(category);
		// set features
		project.setFeatures(modules);
		// set default role to features
		for(ProjectFeature feature : modules) {
			Role role = roleService.getRole(feature.getRoleCode(), category.getId());
			feature.setRole(role);
		}
		context.putSessionData(AttributeKeys.PROJECT_KEY, project);
		return "system/register_form";
	}
}
