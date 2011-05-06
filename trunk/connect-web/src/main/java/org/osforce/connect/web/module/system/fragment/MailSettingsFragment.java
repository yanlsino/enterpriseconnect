package org.osforce.connect.web.module.system.fragment;

import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.service.system.MailSettingsService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 4:59:22 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MailSettingsFragment {
	
	private MailSettingsService mailSettingsService;

	public MailSettingsFragment() {
	}
	
	@Autowired
	public void setMailSettingsService(MailSettingsService mailSettingsService) {
		this.mailSettingsService = mailSettingsService;
	}
	
	public String doListView(Page<MailSettings>page, 
			FragmentContext context) {
		page = mailSettingsService.getMailSettingsPage(page);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/mail_settings_list";
	}
	
	public String doFormView(@Param Long mailSettingsId, FragmentContext context) {
		MailSettings mailSettings = new MailSettings();
		if(mailSettingsId!=null) {
			mailSettings = mailSettingsService.getMailSettings(mailSettingsId);
		}
		context.putRequestData(AttributeKeys.MAIL_SETTINGS_KEY_READABLE, mailSettings);
		return "system/mail_settings_form";
	}
}
