package org.osforce.e2.web.module.discussion.fragment;

import java.util.List;

import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.discussion.Forum;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.e2.service.discussion.ForumService;
import org.osforce.e2.service.discussion.TopicService;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 10:41:24 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TopicFragment {

	private TopicService topicService;
	private ForumService forumService;
	private StatisticService statisticService;
	private ProjectCategoryService projectCategoryService;
	
	public TopicFragment() {
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
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doTopView(@Pref String categoryCode, Project project,
			Page<Statistic> page, Site site, FragmentContext context) {
		page = statisticService.getTopStatisticPage(page, project, Topic.NAME);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Statistic statistic : page.getResult()) {
			Object linkedEntity = topicService.getTopic(statistic.getLinkedId());
			statistic.setLinkedEntity(linkedEntity);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topics_top";
	}
	
	public String doRecentView(Page<Topic> page, Project project,
			FragmentContext context) {
		page = topicService.getTopicPage(page, project);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Topic topic : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(topic.getId(), Topic.NAME);
			if(statistic!=null) {
				topic.setViews(statistic.getCount());
			}
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topics_recent";
	}
	
	public String doListView(@Param Long forumId, 
			Page<Topic> page,  FragmentContext context) {
		page = topicService.getTopicPage(page, forumId);
		for(Topic topic : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(topic.getId(), Topic.NAME);
			if(statistic!=null) {
				topic.setViews(statistic.getCount());
			}
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/topics_list";
	}
	
	public String doFormView( @Param Long topicId, @Param Long forumId,
			User user, Project project, FragmentContext context) {
		Topic topic = new Topic();
		topic.setEnteredBy(user);
		topic.setModifiedBy(user);
		if(topicId!=null) {
			topic = topicService.getTopic(topicId);
		}
		if(forumId!=null) {
			Forum forum = forumService.getForum(forumId);
			topic.setForum(forum);
		}
		List<Forum> forums = forumService.getForumList(project);
		context.putRequestData(AttributeKeys.TOPIC_KEY_READABLE, topic);
		context.putRequestData(AttributeKeys.FORUM_LIST_KEY_READABLE, forums);
		return "discussion/topic_form";
	}
	
}
