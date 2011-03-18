package org.osforce.e2.dao.system.impl;

import org.osforce.e2.dao.system.ThemeDao;
import org.osforce.e2.entity.system.Theme;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:11:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ThemeDaoImpl extends AbstractDao<Theme> implements ThemeDao  {

	protected ThemeDaoImpl() {
		super(Theme.class);
	}

}
