package org.osforce.e2.task.system;

import java.util.Map;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.system.ProjectService;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.task.support.AbstractEmailTask;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 5, 2011 - 3:52:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class UserRegisterEmailTask extends AbstractEmailTask {
	
	private static final String USER_REGISTER_SUBJECT = "email/user_register_subject.ftl";
	private static final String USER_REGISTER_CONTENT = "email/user_register_content.ftl";

	private Configuration configuration;
	private ProjectService projectService;
	private UserService userService;
	
	public UserRegisterEmailTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void prepareMessage(MimeMessageHelper helper,
			Map<Object, Object> context) throws Exception {
		Long userId = (Long) context.get("userId");
		Long projectId = (Long) context.get("projectId");
		User user = userService.getUser(userId);
		Project project = projectService.getProject(projectId);
		context.put("user", user);
		context.put("project", project);
		helper.addTo(user.getEmail(), user.getNickname());
		String subject = FreemarkerUtil.render(configuration, USER_REGISTER_SUBJECT, context);
		String content = FreemarkerUtil.render(configuration, USER_REGISTER_CONTENT, context);
		helper.setSubject(subject);
		helper.setText(content, true);
		// 
		user.setEnabled(false);
		userService.updateUser(user);
	}
	
}
