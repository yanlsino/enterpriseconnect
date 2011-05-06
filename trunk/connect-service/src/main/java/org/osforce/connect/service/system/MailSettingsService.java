package org.osforce.connect.service.system;

import java.util.List;

import org.osforce.connect.entity.system.MailSettings;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 4:50:22 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MailSettingsService {

	MailSettings getMailSettings(Long mailSettingsId);
	
	void createMailSettings(MailSettings mailSettings);
	
	void updateMailSettings(MailSettings mailSettings);
	
	void deleteMailSettings(Long mailSettingsId);

	Page<MailSettings> getMailSettingsPage(Page<MailSettings> page);

	List<MailSettings> getMailSettingsList();
}
