package org.osforce.connect.service.team;

import java.util.List;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:41:45 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MemberService {
	
	TeamMember getMember(Long memberId);
	
	TeamMember getMember(Long userId, Long projectId);
	
	void createMember(TeamMember member);
	
	void updateMember(TeamMember member);
	
	void deleteMember(Long memberId);

	Page<TeamMember> getMemberPage(Page<TeamMember> page, Project project);

	/*List<TeamMember> getNeedApproveMemberList(Project project, User user);

	List<TeamMember> getWaitApproveMemberList(Project project, User user);*/
	
	List<TeamMember> getMemberList(Project project, User user, String status, Boolean reverse);

	void approveMember(Long memberId);
	
}
