package org.osforce.e2.dao.system.impl;

import org.osforce.e2.dao.system.SiteDao;
import org.osforce.e2.entity.system.Site;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * implement SiteDao interface
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 1:46:19 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class SiteDaoImpl extends AbstractDao<Site> implements SiteDao {

	public SiteDaoImpl() {
		super(Site.class);
	}
	
}
