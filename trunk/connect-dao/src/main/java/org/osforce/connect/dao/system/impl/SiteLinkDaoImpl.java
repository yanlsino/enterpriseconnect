package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.SiteLinkDao;
import org.osforce.connect.entity.system.SiteLink;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:12:45 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class SiteLinkDaoImpl extends AbstractDao<SiteLink> 
	implements SiteLinkDao {

	public SiteLinkDaoImpl() {
		super(SiteLink.class);
	}
	
}
