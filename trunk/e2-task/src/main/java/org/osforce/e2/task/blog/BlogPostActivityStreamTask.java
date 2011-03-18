package org.osforce.e2.task.blog;

import java.util.Map;

import org.osforce.e2.entity.blog.BlogPost;
import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.service.blog.BlogPostService;
import org.osforce.e2.service.vblog.ActivityService;
import org.osforce.e2.task.support.FreemarkerUtil;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:22:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogPostActivityStreamTask extends AbstractTask {

	private Configuration configuration;
	private ActivityService activityService;
	private BlogPostService blogPostService;
	
	public BlogPostActivityStreamTask() {
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long postId = (Long) context.get("postId");
		BlogPost post = blogPostService.getBlogPost(postId);
		context.put("post", post);
		context.put("site", post.getProject().getCategory().getSite());
		String templateName = (String) context.get("template");
		String description = FreemarkerUtil.render(configuration, templateName, context);
		Activity activity = new Activity();
		activity.setLinkedId(postId);
		activity.setEntity(BlogPost.NAME);
		activity.setType(BlogPost.NAME);
		activity.setDescription(description);
		activity.setProjectId(post.getProjectId());
		activity.setEnteredId(post.getModifiedId());
		activityService.createActivity(activity);
	}

}
