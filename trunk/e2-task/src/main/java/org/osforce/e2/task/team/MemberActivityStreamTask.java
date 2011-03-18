package org.osforce.e2.task.team;

import java.util.Map;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.team.MemberService;
import org.osforce.e2.service.vblog.ActivityService;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 9:39:41 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MemberActivityStreamTask extends AbstractTask {

	private Configuration configuration;
	private ActivityService activityService;
	private MemberService memberService;
	
	public MemberActivityStreamTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long memberId = (Long) context.get("memberId");
		TeamMember member = memberService.getMember(memberId);
		context.put("member", member);
		String template = (String) context.get("template");
		String description = FreemarkerUtil.render(configuration, template, context);
		Activity activity = new Activity();
		activity.setLinkedId(memberId);
		activity.setEntity(TeamMember.NAME);
		activity.setType(TeamMember.NAME);
		activity.setDescription(description);
		activity.setProjectId(member.getProjectId());
		activity.setEnteredId(member.getUserId());
		activityService.createActivity(activity);
	}
	
}
