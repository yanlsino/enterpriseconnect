package org.osforce.connect.web.module.system.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.connect.web.security.util.PermissionUtil;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 10:11:38 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ProjectFragment {

	private RoleService roleService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	public ProjectFragment() {
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
	
	public String doProjectView(Project project, FragmentContext context) {
		if(project==null) {
			return "commons/blank";
		}
		context.putRequestData(AttributeKeys.PROJECT_KEY_READABLE, project);
		return "system/project";
	}
	
	public String doFormView(@Pref String templateCode, @Pref String categoryCode, 
			User user, Site site, FragmentContext context) {
		ProjectCategory category = projectCategoryService
				.getProjectCategory(site, categoryCode);
		Template template = templateService.getTemplate(category.getId(), templateCode);
		List<ProjectFeature> modules = ModuleUtil.parseToModules(template.getContent());
		for(ProjectFeature feature : modules) {
			Role role = roleService.getRole(feature.getRoleCode(), category.getId());
			feature.setRole(role);
		}
		Project project = new Project();
		project.setEnteredBy(user);
		project.setModifiedBy(user);
		project.setCategory(category);
		project.setFeatures(modules);
		context.putSessionData(AttributeKeys.PROJECT_KEY, project);
		context.putRequestData(AttributeKeys.PROJECT_KEY_READABLE, project);
		return "system/project_form";
	}
	
	public String doProjectMenuView(@Param String viewName, User user,
			TeamMember member, Project project, FragmentContext context) {
		if(project==null) {
			return "commons/blank";
		}
		List<ProjectFeature> features = project.getFeatures();
		List<ProjectFeature> tmp = new ArrayList<ProjectFeature>();
		for(ProjectFeature feature : features) {
			if(PermissionUtil.hasPermission(feature, project, user, member)) {
				tmp.add(feature);
			}
		}
		context.putRequestData(AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, tmp);
		//
		String featureCode = StringUtils.substringBetween(viewName, "/", "/");
		context.putRequestData(AttributeKeys.FEATURE_CODE_KEY_READABLE, featureCode);
		return "system/project_menu";
	}
	
}
