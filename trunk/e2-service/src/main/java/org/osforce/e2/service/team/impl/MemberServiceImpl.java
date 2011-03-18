package org.osforce.e2.service.team.impl;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.dao.system.RoleDao;
import org.osforce.e2.dao.system.UserDao;
import org.osforce.e2.dao.team.MemberDao;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.team.MemberService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:43:19 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private UserDao userDao;
	private RoleDao roleDao;
	private ProjectDao projectDao;
	private MemberDao memberDao;
	
	public MemberServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public TeamMember getMember(Long memberId) {
		return memberDao.get(memberId);
	}
	
	public TeamMember getMember(Long userId, Long projectId) {
		QueryAppender appender = new QueryAppender()
				.equal("teamMember.user.id", userId)
				.equal("teamMember.project.id", projectId);
		return memberDao.findUnique(appender);
	}

	public void createMember(TeamMember member) {
		updateMember(member);
	}

	public void updateMember(TeamMember member) {
		if(member.getUserId()!=null) {
			User user = userDao.get(member.getUserId());
			member.setUser(user);
		}
		if(member.getRoleId()!=null) {
			Role role = roleDao.get(member.getRoleId());
			member.setRole(role);
		}
		if(member.getProjectId()!=null) {
			Project project = projectDao.get(member.getProjectId());
			member.setProject(project);
		}
		if(member.getId()==null) {
			memberDao.save(member);
		} else {
			memberDao.update(member);
		}
	}

	public void deleteMember(Long memberId) {
		memberDao.delete(memberId);
	}

	public Page<TeamMember> getMemberPage(Page<TeamMember> page, Project project) {
		QueryAppender appender = new QueryAppender();
		appender.equal("teamMember.project.id", project.getId())
				.equal("teamMember.enabled", true);
		return memberDao.findPage(page, appender);
	}
	
	public List<TeamMember> getNeedApproveMemberList(
			Project project, User user) {
		QueryAppender appender = new QueryAppender();
		appender.equal("teamMember.project.id", project.getId())
				.notEqual("teamMember.user.id", user.getId())
				.equal("teamMember.enabled", false);
		return memberDao.find(appender);
	}
	
	public List<TeamMember> getWaitApproveMemberList(
			Project project, User user) {
		QueryAppender appender = new QueryAppender();
		appender.notEqual("teamMember.project.id", project.getId())
				.equal("teamMember.user.id", user.getId())
				.equal("teamMember.enabled", false);
		return memberDao.find(appender);
	}
	
	public void approveMember(Long memberId) {
		TeamMember member = memberDao.get(memberId);
		member.setEnabled(true);
		memberDao.update(member);
		if(NumberUtils.compare(member.getProject().getId(), 
				member.getProject().getEnteredBy().getProject().getId())==0) {
			TeamMember otherSide = new TeamMember();
			otherSide.setEnabled(true);
			otherSide.setProject(member.getUser().getProject());
			otherSide.setUser(member.getProject().getEnteredBy());
			otherSide.setRole(member.getRole());
			memberDao.save(otherSide);
		}
	}
}
