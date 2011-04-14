package org.osforce.connect.task.blog;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 11:20:00 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class BlogAspect {
	private static final String TEMPLATE_BLOG_POST_UPDATE = "activity/blog_post_update.ftl";
	
	private Task blogPostViewCountTask;
	private Task blogPostActivityStreamTask;
	
	public BlogAspect() {
	}
	
	@Autowired
	@Qualifier("blogPostViewCountTask")
	public void setBlogPostViewCountTask(Task blogPostViewCountTask) {
		this.blogPostViewCountTask = blogPostViewCountTask;
	}
	
	@Autowired
	@Qualifier("blogPostActivityStreamTask")
	public void setBlogPostActivityStreamTask(Task blogPostActivityStreamTask) {
		this.blogPostActivityStreamTask = blogPostActivityStreamTask;
	}
	
	@AfterReturning("execution(* org.osforce.connect.service.blog.BlogPostService.viewBlogPost(..))") 
	public void viewBlogPost(JoinPoint jp) {
		Long postId = (Long) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("postId", postId);
		blogPostViewCountTask.doAsyncTask(context);
	}
	
	@AfterReturning("execution(* org.osforce.connect.service.blog.BlogPostService.createBlogPost(..)) ||"
			+ "execution(* org.osforce.connect.service.blog.BlogPostService.updateBlogPost(..))") 
	public void updateBlogPost(JoinPoint jp) {
		BlogPost post = (BlogPost) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("postId", post.getId());
		context.put("template", TEMPLATE_BLOG_POST_UPDATE);
		blogPostActivityStreamTask.doAsyncTask(context);
	}
}
