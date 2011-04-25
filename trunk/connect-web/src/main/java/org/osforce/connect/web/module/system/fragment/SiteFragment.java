package org.osforce.connect.web.module.system.fragment;

import java.util.List;

import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.system.MailSettingsService;
import org.osforce.connect.service.system.SiteService;
import org.osforce.connect.service.system.ThemeService;
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
 * @create Jan 29, 2011 - 11:45:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SiteFragment {

	private SiteService siteService;
	private ThemeService themeService;
	private MailSettingsService mailSettingsService;

	public SiteFragment() {
	}

	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	@Autowired
	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}

	@Autowired
	public void setMailSettingsService(MailSettingsService mailSettingsService) {
		this.mailSettingsService = mailSettingsService;
	}

	public String doListView(Page<Site> page, FragmentContext context) {
		page = siteService.getSitePage(page);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/sites_list";
	}

	public String doCopyrightView(Site site, FragmentContext context) {
		context.putRequestData(AttributeKeys.SITE_KEY_READABLE, site);
		return "system/site_copyright";
	}

	public String doFormView(@Param Long siteId, FragmentContext context) {
		Site site = new Site();
		if(siteId!=null) {
			site = siteService.getSite(siteId);
		}
		List<Theme> themes = themeService.getThemeList();
		List<MailSettings> mailSettings = mailSettingsService.getMailSettingsList();
		context.putRequestData(AttributeKeys.THEME_LIST_KEY_READABLE, themes);
		context.putRequestData(AttributeKeys.MAIL_SETTINGS_LIST_KEY_READABLE, mailSettings);
		context.putRequestData(AttributeKeys.SITE_KEY_READABLE, site);
		return "system/site_form";
	}
}
