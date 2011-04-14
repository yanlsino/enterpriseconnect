package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.LinkDao;
import org.osforce.connect.entity.commons.Link;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:11:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class LinkDaoImpl extends AbstractDao<Link> implements LinkDao {

	protected LinkDaoImpl() {
		super(Link.class);
	}

}
