package org.osforce.connect.web.module.admin.fragment;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.module.util.ModuleUtil;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 18, 2011 - 9:06:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AdminFragment {

	private RoleService roleService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	public AdminFragment() {
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
	
	public String doActionsView() {
		return "admin/actions";
	}
	
	public String doWelcomeView() {
		return "admin/welcome";
	}
	
	public String doProjectFormView(Project project,
			Site site, FragmentContext context) {
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(site.getId());
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		List<ProjectCategory> subCategories1 = projectCategoryService
				.getProjectCategoryList(site.getId(), project.getCategoryId());
		context.putRequestData("subCategories1", subCategories1);
		context.putRequestData(AttributeKeys.PROJECT_KEY_READABLE, project);
		return "admin/project_form";
	}
	
	public String doFeaturesFormView(@Pref String templateCode,
			Project project, FragmentContext context) {
		//
		Template template = templateService.getTemplate(project.getCategoryId(), templateCode);
		List<ProjectFeature> features = ModuleUtil.parseToModules(template.getContent());
		for(ProjectFeature feature : features) {
			for(ProjectFeature tmp : project.getFeatures()) {
				if(StringUtils.equals(feature.getCode(), tmp.getCode())) {
					feature.setId(tmp.getId());
					feature.setLabel(tmp.getLabel());
					feature.setShow(tmp.getShow());
					feature.setLevel(tmp.getLevel());
					feature.setRoleId(tmp.getRoleId());
				}
			}
			feature.setProjectId(project.getId());
		}
		context.putRequestData(AttributeKeys.PROJECT_FEATURE_LIST_KEY_READABLE, features);
		//
		List<Role> roles = roleService.getRoleList(project.getCategoryId());
		context.putRequestData(AttributeKeys.ROLE_LIST_KEY_READABLE, roles);
		return "admin/features_form";
	}
	
}
