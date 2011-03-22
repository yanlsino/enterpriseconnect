package org.osforce.e2.web.module.team.fragment;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.osforce.e2.entity.message.Message;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.system.ProjectService;
import org.osforce.e2.service.team.MemberService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 16, 2011 - 5:39:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MemberFragment {

	private ProjectService projectService;
	private MemberService memberService;
	
	public MemberFragment() {
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public String doInfoView(User user, Project project, 
			FragmentContext context) {
		if(user!=null && project!=null) {
			TeamMember teamMember = memberService.getMember(user.getId(), project.getId());
			List<TeamMember> needApprove = Collections.emptyList();
			List<TeamMember> needAccept = Collections.emptyList();
			if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
					NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0 ||
					(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
				needApprove = memberService.getMemberList(project, user, 
						TeamMember.STATUS_WAIT_APPROVE, true);
				needAccept = memberService.getMemberList(project, user, 
						TeamMember.STATUS_NEED_ACCEPT, false);
			}
			context.putRequestData("needApprove", needApprove);
			context.putRequestData("needAccept", needAccept);
			if(!needApprove.isEmpty() || !needAccept.isEmpty()) {
				return "team/member_info";
			}
		}
		return "commons/blank";
	}
	
	public String doListView(Page<TeamMember> page, User user,
			Project project, FragmentContext context) {
		if(user!=null) {
			TeamMember teamMember = memberService.getMember(user.getId(), project.getId());
			List<TeamMember> needApprove = Collections.emptyList();
			List<TeamMember> waitApprove = Collections.emptyList();
			List<TeamMember> needAccept = Collections.emptyList();
			List<TeamMember> waitAccept = Collections.emptyList();
			if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
					(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
				needApprove = memberService.getMemberList(project, user, 
						TeamMember.STATUS_WAIT_APPROVE, true);
				waitApprove = memberService.getMemberList(project, user, 
						TeamMember.STATUS_WAIT_APPROVE, false);
				needAccept = memberService.getMemberList(project, user, 
						TeamMember.STATUS_NEED_ACCEPT, false);
				waitAccept = memberService.getMemberList(project, user, 
						TeamMember.STATUS_NEED_ACCEPT, true);
			}
			context.putRequestData("needApprove", needApprove);
			context.putRequestData("waitApprove", waitApprove);
			context.putRequestData("needAccept", needAccept);
			context.putRequestData("waitAccept", waitAccept);
		}
		page = memberService.getMemberPage(page, project);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "team/members_list";
	}
	// TODO need role code support
	public String doShowView(@Pref String uniqueId, @Pref String roleCode,
			Page<TeamMember> page, FragmentContext context) {
		if(StringUtils.isNotBlank(uniqueId)) {
			Project project = projectService.getProject(uniqueId);
			if(project!=null) {
				page = memberService.getMemberPage(page, project);
				context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
				return "team/members_show";
			}
		}
		return "commons/blank";
	}
	
	public String doInviteView(Project project, User user, FragmentContext context) {
		Message message = new Message();
		message.setFrom(project);
		message.setEnteredBy(user);
		context.putRequestData(AttributeKeys.MESSAGE_KEY_READABLE, message);
		return "team/members_invite";
	}
}
