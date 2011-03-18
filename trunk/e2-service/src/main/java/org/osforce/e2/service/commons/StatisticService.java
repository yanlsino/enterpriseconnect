package org.osforce.e2.service.commons;

import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:42:42 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
public interface StatisticService {

	Statistic getStatistic(Long statisticId);

	void createStatistic(Statistic statistic);

	void updateStatistic(Statistic statistic);

	void deleteStatistic(Long statisticId);

	Statistic getStatistic(Long linkedId, String entity);

	Page<Statistic> getTopStatisticPage(Page<Statistic> page,
			ProjectCategory category, String entity);

	Page<Statistic> getTopStatisticPage(Page<Statistic> page, Project project,
			String entity);
}
