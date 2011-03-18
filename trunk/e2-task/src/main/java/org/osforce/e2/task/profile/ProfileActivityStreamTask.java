package org.osforce.e2.task.profile;

import java.io.IOException;
import java.util.Map;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.service.profile.ProfileService;
import org.osforce.e2.service.vblog.ActivityService;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 1, 2011 - 11:15:03 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ProfileActivityStreamTask extends AbstractTask {
	private Configuration configuration;
	private ProfileService profileService;
	private ActivityService activityService;
	
	public ProfileActivityStreamTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	protected void doTask(Map<Object, Object> context) throws IOException, TemplateException {
		Profile profile = (Profile) context.get("profile");
		String templateName = (String) context.get("template");
		profile = profileService.getProfile(profile.getId());
		context.put("profile", profile);
		context.put("site", profile.getProject().getCategory().getSite());
		String description = FreemarkerUtil.render(configuration, templateName, context);
		Activity activity = new Activity();
		activity.setLinkedId(profile.getId());
		activity.setEntity(Profile.NAME);
		activity.setType(Profile.NAME);
		activity.setProjectId(profile.getProjectId());
		activity.setDescription(description);
		activity.setEnteredId(profile.getModifiedId());
		activityService.createActivity(activity);
	}
	
}
