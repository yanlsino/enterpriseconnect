package org.osforce.connect.task.profile;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:46:26 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class ProfileAspect {
	private final String TEMPLATE_PROFILE_UPDATE = "activity/profile_update.ftl";
	//private final String TEMPLATE_PROFILE_VIEW = "activity/profile_view.ftl";
	
	private Task profileViewCountTask;
	private Task profileActivityStreamTask;
	
	public ProfileAspect() {
	}
	
	@Autowired
	@Qualifier("profileViewCountTask")
	public void setProfileViewCountTask(Task profileViewCountTask) {
		this.profileViewCountTask = profileViewCountTask;
	}
	
	@Autowired
	@Qualifier("profileActivityStreamTask")
	public void setProfileActivityStreamTask(Task profileActivityStreamTask) {
		this.profileActivityStreamTask = profileActivityStreamTask;
	}
	
	@AfterReturning("execution(* org.osforce.connect.service.profile.ProfileService.viewProfile(..))")
	public void viewProfile(JoinPoint jp) throws Exception {
		Project project = (Project) jp.getArgs()[0];
		User user = (User) jp.getArgs()[1];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("project", project);
		context.put("user", user);
		profileViewCountTask.doAsyncTask(context);
	}
	
	@AfterReturning("execution(* org.osforce.connect.service.profile.ProfileService.createProfile(..))"
			+ "execution(* org.osforce.connect.service.profile.ProfileService.updateProfile(..))")
	public void updateProfile(JoinPoint jp) throws Exception {
		Map<Object, Object> context = new HashMap<Object, Object>();
		Profile profile = (Profile) jp.getArgs()[0];
		context.put("profile", profile);
		context.put("template", TEMPLATE_PROFILE_UPDATE);
		profileActivityStreamTask.doAsyncTask(context);
	}
	
}
