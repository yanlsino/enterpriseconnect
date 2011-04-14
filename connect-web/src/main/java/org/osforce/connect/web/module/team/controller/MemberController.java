package org.osforce.connect.web.module.team.controller;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 16, 2011 - 5:31:04 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/team")
public class MemberController {

	private RoleService roleService;
	private UserService userService;
	private ProjectService projectService;
	private MemberService memberService;
	
	public MemberController() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
	
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public void update(TeamMember member) {
		if(member.getId()==null) {
			memberService.createMember(member);
		} else {
			memberService.updateMember(member);
		}
	}
	
	@RequestMapping(value="/join")
	public @ResponseBody Map<String, Long> join(
			@RequestParam Long projectId, WebRequest request) {
		Project project = projectService.getProject(projectId);
		User user = (User) request.getAttribute(
				AttributeKeys.USER_KEY, WebRequest.SCOPE_SESSION);
		if(project!=null && user!=null) {
			TeamMember member = (TeamMember) request.getAttribute(
					AttributeKeys.TEAM_MEMBER_KEY, WebRequest.SCOPE_SESSION);
			member.setUser(user);
			member.setProject(project);
			member.setStatus(TeamMember.STATUS_WAIT_APPROVE);
			memberService.createMember(member);
			request.removeAttribute(AttributeKeys.TEAM_MEMBER_KEY, 
					WebRequest.SCOPE_SESSION);
			return Collections.singletonMap("id", member.getId());
		}
		return null;
	}
	
	@RequestMapping(value={"/approve", "/accept"})
	public @ResponseBody Map<String, Long> approve(
			@RequestParam Long memberId) {
		memberService.approveMember(memberId);
		return Collections.singletonMap("id", memberId);
	}
	
	@RequestMapping(value="/members/invite")
	public @ResponseBody String invite(@RequestParam String emails,
			WebRequest request) {
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, 
				WebRequest.SCOPE_REQUEST);
		String[] emailsArray = StringUtils.split(emails, "\n");
		for(String email : emailsArray) {
			User user = userService.getUser(StringUtils.trim(email));
			if(user!=null) {
				Role defaultRole = roleService.getRole(project.getCategoryId(), 50);
				TeamMember member = new TeamMember(project, user, defaultRole);
				member.setStatus(TeamMember.STATUS_NEED_ACCEPT);
				memberService.createMember(member);
			}
		}
		return "success";
	}
}
