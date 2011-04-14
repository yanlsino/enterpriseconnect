package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.SiteDao;
import org.osforce.connect.dao.system.SiteLinkDao;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.SiteLink;
import org.osforce.connect.service.system.SiteLinkService;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:15:05 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class SiteLinkServiceImpl implements SiteLinkService {

	private SiteDao siteDao;
	private SiteLinkDao siteLinkDao;
	
	public SiteLinkServiceImpl() {
	}
	
	@Autowired
	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}
	
	@Autowired
	public void setSiteLinkDao(SiteLinkDao siteLinkDao) {
		this.siteLinkDao = siteLinkDao;
	}

	public SiteLink getSiteLink(Long siteLinkId) {
		return siteLinkDao.get(siteLinkId);
	}

	public void createSiteLink(SiteLink siteLink) {
		updateSiteLink(siteLink);
	}

	public void updateSiteLink(SiteLink siteLink) {
		if(siteLink.getSiteId()!=null) {
			Site site = siteDao.get(siteLink.getSiteId());
			siteLink.setSite(site);
		}
		if(siteLink.getId()==null) {
			siteLinkDao.save(siteLink);
		} else {
			siteLinkDao.update(siteLink);
		}
	}

	public void deleteSiteLink(Long siteLinkId) {
		siteLinkDao.delete(siteLinkId);
	}
	
	public List<SiteLink> getSiteLinkList(Long siteId) {
		QueryAppender appender = new QueryAppender()
				.equal("siteLink.site.id", siteId)
				.asc("siteLink.code");
		return siteLinkDao.find(appender);
	}
	
}
