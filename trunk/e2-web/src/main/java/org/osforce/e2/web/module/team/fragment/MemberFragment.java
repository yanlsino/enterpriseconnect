package org.osforce.e2.web.module.team.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.team.MemberService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
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

	private MemberService memberService;
	
	public MemberFragment() {
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public String doInfoView(User user, Project project, 
			FragmentContext context) {
		if(user!=null && project!=null) {
			TeamMember teamMember = memberService.getMember(user.getId(), project.getId());
			List<TeamMember> needApprove = new ArrayList<TeamMember>();
			if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
					NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0 ||
					(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
				needApprove = memberService.getNeedApproveMemberList(project, user);
			}
			context.putRequestData("needApprove", needApprove);
			if(!needApprove.isEmpty()) {
				return "team/member_info";
			}
		}
		return "commons/blank";
	}
	
	public String doListView(Page<TeamMember> page, User user,
			Project project, FragmentContext context) {
		if(user!=null) {
			TeamMember teamMember = memberService.getMember(user.getId(), project.getId());
			List<TeamMember> needApprove = new ArrayList<TeamMember>();
			List<TeamMember> waitApprove = new ArrayList<TeamMember>();
			if(NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
					(teamMember!=null && teamMember.getRole().getLevel()<=10)) {
				needApprove = memberService.getNeedApproveMemberList(project, user);
				waitApprove = memberService.getWaitApproveMemberList(project, user);
			}
			context.putRequestData("needApprove", needApprove);
			context.putRequestData("waitApprove", waitApprove);
		}
		page = memberService.getMemberPage(page, project);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "team/members_list";
	}
}
