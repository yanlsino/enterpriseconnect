package org.osforce.connect.web.security.tag;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

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
import org.osforce.connect.web.security.rule.SecurityRule;
import org.osforce.connect.web.security.util.PermissionUtil;
import org.osforce.platform.web.framework.config.FragmentConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 2:21:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
//TODO need refactory the code @see  SecurityRule
public class SecurityTag extends TagSupport {
	private static final long serialVersionUID = -4533925423387947005L;

	private String code;
	private Project project;
	private Boolean userRequired = false;
	private Boolean projectRequired = false;
	
	public SecurityTag() {
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Boolean getUserRequired() {
		return userRequired;
	}
	
	public void setUserRequired(Boolean userRequired) {
		this.userRequired = userRequired;
	}
	
	public Boolean getProjectRequired() {
		return projectRequired;
	}
	
	public void setProjectRequired(Boolean projectRequired) {
		this.projectRequired = projectRequired;
	}
	
	@Override
	public int doStartTag() throws JspException {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(pageContext.getServletContext());
		FragmentConfig fragmentConfig = (FragmentConfig) pageContext.getRequest().getAttribute(FragmentConfig.NAME);
		if(project==null) {
			project = (Project) pageContext.getRequest().getAttribute(AttributeKeys.PROJECT_KEY);
		}
		User user = (User) pageContext.getRequest().getAttribute(AttributeKeys.USER_KEY);
		TeamMember member = (TeamMember) pageContext.getRequest()
				.getAttribute(AttributeKeys.TEAM_MEMBER_KEY);
		Site site = (Site) pageContext.getRequest().getAttribute(AttributeKeys.SITE_KEY);
		//
		String categoryCode = null;
		Map<String, String> ruleConfig = fragmentConfig.getRule(SecurityRule.NAME);
		if(ruleConfig!=null) {
			categoryCode = ruleConfig.get("categoryCode"); 
		}
		if(userRequired.booleanValue() && user==null) {
			return SKIP_BODY;
		}
		if(projectRequired.booleanValue() && project==null) {
			return SKIP_BODY;
		}
		//
		Long categoryId = null;
		if(project!=null) {
			categoryId = project.getCategoryId();
		} else if(StringUtils.isNotBlank(categoryCode)) {
			ProjectCategoryService projectCategoryService = applicationContext.getBean(ProjectCategoryService.class);
			ProjectCategory category = projectCategoryService.getProjectCategory(site, categoryCode);
			categoryId = category.getId();
		} else {
			//throw new NullPointerException("project or categoryCode can not be both null!");
			return EVAL_BODY_INCLUDE;
		}
		//
		PermissionService permissionService = applicationContext.getBean(PermissionService.class);
		Permission permission = permissionService.getPermission(code, categoryId);
		if(PermissionUtil.hasPermission(permission, project, user, member)) {
			return EVAL_BODY_INCLUDE;
		} 
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
