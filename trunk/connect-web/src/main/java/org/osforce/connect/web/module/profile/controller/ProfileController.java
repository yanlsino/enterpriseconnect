package org.osforce.connect.web.module.profile.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.service.commons.AttachmentService;
import org.osforce.connect.service.profile.ProfileService;
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
	private AttachmentService attachmentService;
	
	public ProfileController() {
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping(value="/profile/profile", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(@RequestParam String[] labels, 
			@RequestParam String[] values, Profile profile, WebRequest request) {
		profile.setAttributes(labels, values);
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
