package org.osforce.e2.web.module.discussion.fragment;

import java.util.List;

import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.discussion.Forum;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.e2.service.discussion.ForumService;
import org.osforce.e2.service.discussion.TopicService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 6:10:25 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ForumFragment {

	private TopicService topicService;
	private ForumService forumService;
	private StatisticService statisticService;
	
	public ForumFragment() {
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	public String doListView(Page<Topic> page, 
			Project project, FragmentContext context) {
		List<Forum> forums = forumService.getForumList(project);
		for(Forum forum : forums) {
			page = topicService.getTopicPage(page, forum.getId());
			for(Topic topic : page.getResult()) {
				Statistic statistic = statisticService.getStatistic(topic.getId(), Topic.NAME);
				if(statistic!=null) {
					topic.setViews(statistic.getCount());
				}
			}
			forum.setTopics(page.getResult());
		}
		context.putRequestData(AttributeKeys.FORUM_LIST_KEY_READABLE, forums);
		return "discussion/forums_list";
	}
	
	public String doFormView(@Param Long forumId,
			User user, Project project, FragmentContext context) {
		Forum forum = new Forum();
		forum.setEnteredBy(user);
		forum.setModifiedBy(user);
		forum.setProject(project);
		if(forumId!=null) {
			forum = forumService.getForum(forumId);
		}
		context.putRequestData(AttributeKeys.FORUM_KEY_READABLE, forum);
		return "discussion/forum_form";
	}
}
