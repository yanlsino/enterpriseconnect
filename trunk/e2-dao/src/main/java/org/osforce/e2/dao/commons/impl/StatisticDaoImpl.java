package org.osforce.e2.dao.commons.impl;

import org.osforce.e2.dao.commons.StatisticDao;
import org.osforce.e2.entity.commons.Statistic;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:41:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class StatisticDaoImpl extends AbstractDao<Statistic>
	implements StatisticDao {

	public StatisticDaoImpl() {
		super(Statistic.class);
	}
}
