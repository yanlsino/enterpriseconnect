package org.osforce.e2.task.blog;

import java.util.Map;

import org.osforce.e2.entity.blog.BlogPost;
import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.service.blog.BlogPostService;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 3:01:37 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogPostViewCountTask extends AbstractTask {

	private StatisticService statisticService;
	private BlogPostService blogPostService;
	
	public BlogPostViewCountTask() {
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long postId = (Long) context.get("postId");
		BlogPost post = blogPostService.getBlogPost(postId); 
		Statistic statistic = statisticService.getStatistic(postId, BlogPost.NAME);
		if(statistic==null) {
			statistic = new Statistic(postId, BlogPost.NAME);
		}
		statistic.countAdd();
		statistic.setProjectId(post.getProjectId());
		statisticService.createStatistic(statistic);
	}
}
