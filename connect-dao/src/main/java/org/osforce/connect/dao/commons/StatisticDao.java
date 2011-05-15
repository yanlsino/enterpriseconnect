package org.osforce.connect.dao.commons;

import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.platform.dao.BaseDao;
import org.osforce.platform.dao.support.Page;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:40:20 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface StatisticDao extends BaseDao<Statistic> {

	public Page<Statistic> findTopPage(Page<Statistic> page,
			ProjectCategory category, String entity);

}
