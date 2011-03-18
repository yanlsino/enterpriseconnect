package org.osforce.e2.service.commons.impl;

import org.osforce.e2.dao.commons.StatisticDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:43:58 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

	private ProjectDao projectDao;
	private StatisticDao statisticDao;
	
	public StatisticServiceImpl() {
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

	public Statistic getStatistic(Long statisticId) {
		return statisticDao.get(statisticId);
	}
	
	public Statistic getStatistic(Long linkedId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("statistic.linkedId", linkedId)
				.equal("statistic.entity", entity);
		return statisticDao.findUnique(appender);
	}

	public void createStatistic(Statistic statistic) {
		updateStatistic(statistic);
	}

	public void updateStatistic(Statistic statistic) {
		if(statistic.getProject()==null && statistic.getProjectId()!=null) {
			Project project = projectDao.get(statistic.getProjectId());
			statistic.setProject(project);
		}
		if(statistic.getId()==null) {
			statisticDao.save(statistic);
		} else {
			statisticDao.update(statistic);
		}
	}

	public void deleteStatistic(Long statisticId) {
		statisticDao.delete(statisticId);
	}
	
	public Page<Statistic> getTopStatisticPage(Page<Statistic> page,
			ProjectCategory category, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("statistic.project.category.id", category.getId())
				.equal("statistic.entity", entity).desc("statistic.count");
		return statisticDao.findPage(page, appender);
	}
	
	public Page<Statistic> getTopStatisticPage(Page<Statistic> page,
			Project project, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("statistic.project.id", project.getId())
				.equal("statistic.entity", entity).desc("statistic.count");
		return statisticDao.findPage(page, appender);
	}
	
}
