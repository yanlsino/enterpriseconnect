package org.osforce.e2.web.security.rule;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.system.PermissionService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.e2.web.security.util.PermissionUtil;
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

	private static final String NAME = StringUtils.uncapitalize(SecurityRule.class.getSimpleName());
	
	private PermissionService permissionService;
	
	public SecurityRule() {
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Override
	public boolean preInvokerHandle(FragmentContext context) {
		Map<String, String> ruleConfig = context.getFragmentConfig().getRule(NAME);
		Project project = (Project) context.getRequestData(AttributeKeys.PROJECT_KEY);
		User user = (User) context.getRequestData(AttributeKeys.USER_KEY);
		TeamMember member = (TeamMember) context.getRequestData(AttributeKeys.TEAM_MEMBER_KEY);
		//
		String codeStr = ruleConfig.get("code");
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
		String[] codes = null;
		if(StringUtils.contains(codeStr, "|")) {
			 codes = StringUtils.split(codeStr, "|");
		} else {
			codes = new String[]{codeStr};
		}
		boolean flag = false;
		for(String code : codes) {
			Permission permission = permissionService.getPermission(code);
			if(flag = PermissionUtil.hasPermission(permission, project, user, member)) {
				break;
			}
		}
		return flag;
	}
}
