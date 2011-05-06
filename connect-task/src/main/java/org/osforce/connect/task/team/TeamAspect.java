package org.osforce.connect.task.team;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.team.MemberService;
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
@Aspect
@Component
public class TeamAspect {
	private static final String TEMPLATE_MEMBER_UPDATE = "member_update.ftl";

	private MemberService memberService;

	private Task memberActivityStreamTask;
	private Task memberRequestEmailTask;
	private Task memberApproveEmailTask;

	public TeamAspect() {
	}

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Autowired
	@Qualifier("memberActivityStreamTask")
	public void setMemberActivityStreamTask(Task memberActivityStreamTask) {
		this.memberActivityStreamTask = memberActivityStreamTask;
	}

	@Autowired
	@Qualifier("memberRequestEmailTask")
	public void setMemberRequestEmailTask(Task memberRequestEmailTask) {
		this.memberRequestEmailTask = memberRequestEmailTask;
	}

	@Autowired
	@Qualifier("memberApproveEmailTask")
	public void setMemberApproveEmailTask(Task memberApproveEmailTask) {
		this.memberApproveEmailTask = memberApproveEmailTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.team.MemberService.createMember(..)) ||" +
			"execution(* org.osforce.connect.service.team.MemberService.updateMember(..))")
	public void updateMember(JoinPoint jp) {
		TeamMember member = (TeamMember) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("memberId", member.getId());
		context.put("template", TEMPLATE_MEMBER_UPDATE);
		memberActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.team.MemberService.requestMember(..))")
	public void requestMember(JoinPoint jp) {
		TeamMember member = (TeamMember) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("memberId", member.getId());
		context.put("siteId", member.getProject().getCategory().getSiteId());
		memberRequestEmailTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.team.MemberService.approveMember(..))")
	public void approveMember(JoinPoint jp) {
		Long memberId = (Long) jp.getArgs()[0];
		TeamMember member = memberService.getMember(memberId);
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("memberId", memberId);
		context.put("siteId", member.getProject().getCategory().getSiteId());
		memberApproveEmailTask.doAsyncTask(context);
	}
}
