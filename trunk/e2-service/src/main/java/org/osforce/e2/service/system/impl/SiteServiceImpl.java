package org.osforce.e2.service.system.impl;

import org.osforce.e2.dao.system.MailSettingsDao;
import org.osforce.e2.dao.system.SiteDao;
import org.osforce.e2.dao.system.ThemeDao;
import org.osforce.e2.entity.system.MailSettings;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.Theme;
import org.osforce.e2.service.system.SiteService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:14:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

	private SiteDao siteDao;
	private ThemeDao themeDao;
	private MailSettingsDao mailSettingsDao;
	
	public SiteServiceImpl() {
	}
	
	@Autowired
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Autowired
	public void setThemeDao(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}
	
	@Autowired
	public void setMailSettingsDao(MailSettingsDao mailSettingsDao) {
		this.mailSettingsDao = mailSettingsDao;
	}
	
	public Site getSite(Long siteId) {
		return siteDao.get(siteId);
	}
	
	public Site getSite(String domain) {
		QueryAppender appender = new QueryAppender()
				.equal("site.domain", domain);
		return siteDao.findUnique(appender);
	}

	public void createSite(Site site) {
		updateSite(site);
	}

	public void updateSite(Site site) {
		if(site.getThemeId()!=null) {
			Theme theme = themeDao.get(site.getThemeId());
			site.setTheme(theme);
		}
		if(site.getMailSettingsId()!=null) {
			MailSettings mailSettings = mailSettingsDao.get(site.getMailSettingsId());
			site.setMailSettings(mailSettings);
		}
		if(site.getId()==null) {
			siteDao.save(site);
		} else {
			siteDao.update(site);
		}
	}

	public Page<Site> getSitePage(Page<Site> page) {
		return siteDao.findPage(page, new QueryAppender());
	}
	
	/**
	 * interceptor by @code SystemAspect
	 */
	public void backupSite(Site site) {}
	public void restoreSite(Site site) {}
}
