package org.osforce.e2.service.system;

import org.osforce.e2.entity.system.Site;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:13:49 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface SiteService {
	
	Site getSite(Long siteId);

	void createSite(Site site);
	
	void updateSite(Site site);
	
	Page<Site> getSitePage(Page<Site> page);
	
	/**
	 * intercept by @code SystemAspect
	 * @param site
	 */
	void backupSite(Site site);
	void restoreSite(Site site);
}
