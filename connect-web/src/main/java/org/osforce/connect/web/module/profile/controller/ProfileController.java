package org.osforce.connect.web.module.profile.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.commons.custom.CustomForm;
import org.osforce.commons.custom.CustomFormUtil;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 11:40:09 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
public class ProfileController {

	private ProfileService profileService;
	private ProjectService projectService;
	private TemplateService templateService;
	private AttachmentService attachmentService;
	
	public ProfileController() {
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping(value="/profile/profile", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(
			Profile profile, WebRequest request) {
		Project project = projectService.getProject(profile.getProjectId());
		Map<String, String[]> context = request.getParameterMap();
		Template attributesTemplate = templateService.getTemplate(
				project.getCategoryId(), profile.getAttributesTemplateCode());
		CustomForm customForm = CustomFormUtil.populate(
				attributesTemplate.getContent(), context);
		String attributes = CustomFormUtil.renderJSONObject(customForm);
		profile.setAttributes(attributes);
		profileService.updateProfile(profile);
		return Collections.singletonMap("id", profile.getId());
	}
	
	@RequestMapping(value="/profile/logo")
	public @ResponseBody Map<String, Long> update(
			@RequestParam Long profileId, WebRequest request) {
		Attachment logo = (Attachment) request.getAttribute(
				AttributeKeys.ATTACHMENT_KEY, WebRequest.SCOPE_REQUEST);
		Profile profile = profileService.getProfile(profileId);
		if(profile.getLogo()!=null) {
			attachmentService.deleteAttachment(profile.getLogoId());
		}
		profile.setLogo(logo);
		profileService.updateProfile(profile);
		return Collections.singletonMap("id", profile.getId());
	}
}
