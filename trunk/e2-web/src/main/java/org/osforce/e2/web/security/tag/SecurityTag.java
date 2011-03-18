package org.osforce.e2.web.security.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.system.PermissionService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.e2.web.security.util.PermissionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 2:21:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class SecurityTag extends TagSupport {
	private static final long serialVersionUID = -4533925423387947005L;

	private String code;
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
		Project project = (Project) pageContext.getRequest().getAttribute(AttributeKeys.PROJECT_KEY);
		User user = (User) pageContext.getRequest().getAttribute(AttributeKeys.USER_KEY);
		TeamMember member = (TeamMember) pageContext.getRequest()
				.getAttribute(AttributeKeys.TEAM_MEMBER_KEY);
		if(userRequired.booleanValue() && user==null) {
			return SKIP_BODY;
		}
		if(projectRequired.booleanValue() && project==null) {
			return SKIP_BODY;
		}
		PermissionService permissionService = applicationContext.getBean(PermissionService.class);
		Permission permission = permissionService.getPermission(code);
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
