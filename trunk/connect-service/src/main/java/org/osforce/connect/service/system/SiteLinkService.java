package org.osforce.connect.service.system;

import java.util.List;

import org.osforce.connect.entity.system.SiteLink;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:14:40 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface SiteLinkService {

	SiteLink getSiteLink(Long siteLinkId);
	
	void createSiteLink(SiteLink siteLink);
	
	void updateSiteLink(SiteLink siteLink);
	
	void deleteSiteLink(Long siteLinkId);

	List<SiteLink> getSiteLinkList(Long siteId);
}
