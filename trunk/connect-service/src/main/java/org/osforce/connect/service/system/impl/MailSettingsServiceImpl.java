package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.MailSettingsDao;
import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.service.system.MailSettingsService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 4:51:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class MailSettingsServiceImpl implements MailSettingsService {

	private MailSettingsDao mailSettingsDao;
	
	public MailSettingsServiceImpl() {
	}
	
	@Autowired
	public void setMailSettingsDao(MailSettingsDao mailSettingsDao) {
		this.mailSettingsDao = mailSettingsDao;
	}

	public MailSettings getMailSettings(Long mailSettingsId) {
		return mailSettingsDao.get(mailSettingsId);
	}

	public void createMailSettings(MailSettings mailSettings) {
		updateMailSettings(mailSettings);
	}

	public void updateMailSettings(MailSettings mailSettings) {
		if(mailSettings.getId()==null) {
			mailSettingsDao.save(mailSettings);
		} else {
			mailSettingsDao.update(mailSettings);
		}
	}

	public void deleteMailSettings(Long mailSettingsId) {
		mailSettingsDao.delete(mailSettingsId);
	}
	
	public Page<MailSettings> getMailSettingsPage(Page<MailSettings> page) {
		QueryAppender appender = new QueryAppender();
		return mailSettingsDao.findPage(page, appender);
	}
	
	public List<MailSettings> getMailSettingsList() {
		QueryAppender appender = new QueryAppender();
		return mailSettingsDao.find(appender);
	}
}
