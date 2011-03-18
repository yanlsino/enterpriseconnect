package org.osforce.e2.task.team;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 9:36:24 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TeamAspect {
	private static final String TEMPLATE_MEMBER_UPDATE = "member_update.ftl";
	
	private Task memberActivityStreamTask;
	
	public TeamAspect() {
	}
	
	@Autowired
	@Qualifier("memberActivityStreamTask")
	public void setMemberActivityStreamTask(Task memberActivityStreamTask) {
		this.memberActivityStreamTask = memberActivityStreamTask;
	}
	
	@AfterReturning("execution(* org.osforce.e2.service.team.MemberService.createMember(..))")
	public void updateMember(JoinPoint jp) {
		TeamMember member = (TeamMember) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("memberId", member.getId());
		context.put("template", TEMPLATE_MEMBER_UPDATE);
		memberActivityStreamTask.doAsyncTask(context);
	}
}
