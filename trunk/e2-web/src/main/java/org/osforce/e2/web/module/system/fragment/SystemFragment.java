package org.osforce.e2.web.module.system.fragment;

import java.util.List;

import org.osforce.e2.entity.commons.Template;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.ProjectFeature;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.TemplateService;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.e2.service.system.RoleService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.e2.web.module.util.ModuleUtil;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:19:03 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SystemFragment {
	
	private RoleService roleService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	public SystemFragment() {
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
	
	public String doWelcomeView() {
		return "system/welcome";
	}
	
	public String doActionsView() {
		return "system/actions";
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
