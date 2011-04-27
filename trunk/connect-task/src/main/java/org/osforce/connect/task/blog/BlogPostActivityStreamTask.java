package org.osforce.connect.task.blog;

import java.util.Map;

import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.service.blog.BlogPostService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:22:53 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogPostActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private BlogPostService blogPostService;

	public BlogPostActivityStreamTask() {
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
		String templateName = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(postId);
		activity.setEntity(BlogPost.NAME);
		activity.setType(BlogPost.NAME);
		activity.setDescription(templateName);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(post.getProjectId());
		activity.setEnteredId(post.getModifiedId());
		activityService.createActivity(activity);
	}

}
