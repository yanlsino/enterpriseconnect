package org.osforce.e2.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.system.ProjectService;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.service.team.MemberService;
import org.osforce.e2.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 12:26:47 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {

	private UserService userService;
	private ProjectService projectService;
	private MemberService memberService;
	
	public ControllerInterceptor() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// set context path as base to request
		request.setAttribute(AttributeKeys.BASE_KEY_READABLE, request.getContextPath());
		// resolve project
		String uniqueId = request.getParameter("uniqueId");
		if(StringUtils.isNotBlank(uniqueId)) {
			Project project = projectService.getProject(uniqueId);
			request.setAttribute(AttributeKeys.PROJECT_KEY, project);
			request.setAttribute(AttributeKeys.PROJECT_KEY_READABLE, project);
		}
		// resolve login user
		if(request.getSession().getAttribute(AttributeKeys.USER_KEY)==null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof UserDetails) {
				String username = ((UserDetails)principal).getUsername();
				User persisted = userService.getUser(username);
				request.getSession().setAttribute(AttributeKeys.USER_KEY, persisted);
				request.getSession().setAttribute(AttributeKeys.USER_KEY_READABLE, persisted);
			}
		} else {
			User user = (User) request.getSession().getAttribute(AttributeKeys.USER_KEY);
			User persisted = userService.getUser(user.getId());
			request.setAttribute(AttributeKeys.USER_KEY, persisted);
			request.setAttribute(AttributeKeys.USER_KEY_READABLE, persisted);
		}
		// member 
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY);
		User user = (User) request.getAttribute(AttributeKeys.USER_KEY);
		if(project!=null && user!=null) {
			TeamMember member = memberService.getMember(user.getId(), project.getId());
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY, member);
			request.setAttribute(AttributeKeys.TEAM_MEMBER_KEY_READABLE, member);
		}
		return super.preHandle(request, response, handler);
	}
	
}
