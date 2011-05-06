package org.osforce.connect.task.team;

import java.util.Map;

import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.task.support.AbstractEmailTask;
import org.osforce.connect.task.support.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 *
 * @author gavin
 * @since 1.0.2
 * @create May 5, 2011 - 11:23:08 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MemberRequestEmailTask extends AbstractEmailTask {

	private static final String MEMBER_REQUEST_SUBJECT = "email/member_request_subject.ftl";
	private static final String MEMBER_REQUEST_CONTENT = "email/member_request_content.ftl";

	private Configuration configuration;
	private MemberService memberService;

	public MemberRequestEmailTask() {
	}

	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long memberId = (Long) context.get("memberId");
		TeamMember member = memberService.getMember(memberId);
		context.put("member", member);
		helper.addTo(member.getProject().getEnteredBy().getEmail(),
				member.getProject().getEnteredBy().getNickname());
		String subject = FreemarkerUtil.render(configuration, MEMBER_REQUEST_SUBJECT, context);
		String content = FreemarkerUtil.render(configuration, MEMBER_REQUEST_CONTENT, context);
		helper.setSubject(subject);
		helper.setText(content, true);
	}

}
