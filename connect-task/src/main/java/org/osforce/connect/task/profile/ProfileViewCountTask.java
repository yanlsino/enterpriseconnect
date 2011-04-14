package org.osforce.connect.task.profile;

import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.commons.Statistic;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.commons.StatisticService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:59:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ProfileViewCountTask extends AbstractTask {

	private ProjectService projectService;
	private StatisticService statisticService;
	
	public ProfileViewCountTask() {
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Project project = (Project) context.get("project");
		User user = (User) context.get("user");
		Project p = projectService.getProject(project.getId());
		if(user==null || NumberUtils
				.compare(p.getEnteredBy().getId(), user.getId())!=0) {
			Statistic statistic = statisticService.getStatistic(
					p.getProfile().getId(), Profile.NAME);
			if(statistic==null) {
				statistic = new Statistic(p.getProfile().getId(), Profile.NAME);
			}
			statistic.countAdd();
			statistic.setProjectId(p.getId());
			statisticService.createStatistic(statistic);
		}
	}

}
