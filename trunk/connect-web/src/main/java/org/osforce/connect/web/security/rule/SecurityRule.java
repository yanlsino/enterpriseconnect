package org.osforce.connect.web.security.rule;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.connect.web.security.util.PermissionUtil;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.osforce.platform.web.framework.core.InvokerInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 9:20:35 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SecurityRule extends InvokerInterceptorAdapter {

	public static final String NAME = StringUtils.uncapitalize(SecurityRule.class.getSimpleName());
	
	private PermissionService permissionService;
	private ProjectCategoryService projectCategoryService;
	
	public SecurityRule() {
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
	
	@Override
	public boolean preInvokerHandle(FragmentContext context) {
		Map<String, String> ruleConfig = context.getFragmentConfig().getRule(NAME);
		Project project = (Project) context.getRequestData(AttributeKeys.PROJECT_KEY);
		User user = (User) context.getRequestData(AttributeKeys.USER_KEY);
		TeamMember member = (TeamMember) context.getRequestData(AttributeKeys.TEAM_MEMBER_KEY);
		Site site = (Site) context.getRequestData(AttributeKeys.SITE_KEY);
		//
		String codeStr = ruleConfig.get("code");
		String categoryCode = ruleConfig.get("categoryCode");
		String userRequired = ruleConfig.get("userRequired");
		String projectRequired = ruleConfig.get("projectRequired");
		//
		if((StringUtils.equals(userRequired, "true") || 
				StringUtils.equals(userRequired, "yes")) && user==null) {
			return false;
		}
		if((StringUtils.equals(projectRequired, "true") || 
				StringUtils.equals(projectRequired, "yes")) && project==null) {
			return false;
		}
		//
		if(StringUtils.isBlank(codeStr)) {
			return true;
		}
		//
		Long categoryId = null;
		if(project!=null) {
			categoryId = project.getCategoryId();
		} else if(StringUtils.isNotBlank(categoryCode)) {
			ProjectCategory category = projectCategoryService.getProjectCategory(site, categoryCode);
			categoryId = category.getId();
		} else {
			return true;
		}
		//
		String[] codes = null;
		if(StringUtils.contains(codeStr, "|")) {
			 codes = StringUtils.split(codeStr, "|");
		} else {
			codes = new String[]{codeStr};
		}
		boolean flag = false;
		for(String code : codes) {
			Permission permission = permissionService.getPermission(code, categoryId);
			if(flag = PermissionUtil.hasPermission(permission, project, user, member)) {
				break;
			}
		}
		return flag;
	}
}
