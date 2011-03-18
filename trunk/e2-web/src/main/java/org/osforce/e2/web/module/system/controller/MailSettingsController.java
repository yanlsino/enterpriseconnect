package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.MailSettings;
import org.osforce.e2.service.system.MailSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 4:55:04 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class MailSettingsController {

	private MailSettingsService mailSettingsService;
	
	public MailSettingsController() {
	}
	
	@Autowired
	public void setMailSettingsService(MailSettingsService mailSettingsService) {
		this.mailSettingsService = mailSettingsService;
	}
	
	@RequestMapping(value="/mail_settings", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(MailSettings mailSettings) {
		if(mailSettings.getId()==null) {
			mailSettingsService.createMailSettings(mailSettings);
		} else {
			mailSettingsService.updateMailSettings(mailSettings);
		}
		return Collections.singletonMap("id", mailSettings.getId());
	}
	
}
