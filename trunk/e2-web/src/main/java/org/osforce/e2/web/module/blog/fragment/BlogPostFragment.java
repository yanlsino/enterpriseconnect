package org.osforce.e2.web.module.blog.fragment;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.blog.BlogCategory;
import org.osforce.e2.entity.blog.BlogPost;
import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.blog.BlogCategoryService;
import org.osforce.e2.service.blog.BlogPostService;
import org.osforce.e2.service.commons.CommentService;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.e2.service.system.ProjectService;
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
 * @create Feb 13, 2011 - 9:44:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogPostFragment {
	
	private ProjectService projectService;
	private StatisticService statisticService;
	private BlogPostService blogPostService;
	private CommentService commentService;
	private BlogCategoryService blogCategoryService;
	
	public BlogPostFragment() {
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@Autowired
	public void setBlogCategoryService(BlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}
	
	public String doTopView(Page<Statistic> page, Project project,
			FragmentContext context) {
		page = statisticService.getTopStatisticPage(page, project, BlogPost.NAME);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Statistic statistic : page.getResult()) {
			Object linkedEntity = blogPostService.getBlogPost(statistic.getLinkedId());
			statistic.setLinkedEntity(linkedEntity);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/posts_top";
	}
	
	public String doRecentView(@Pref String uniqueId, @Pref String categoryLabel,
			Page<BlogPost> page, Project project, FragmentContext context) {
		if(StringUtils.isNotBlank(uniqueId)) {
			Project tmp = projectService.getProject(uniqueId);
			if(tmp!=null) {
				project = tmp;
			}
		}
		BlogCategory blogCategory = null;
		if(StringUtils.isNotBlank(categoryLabel)) {
			 blogCategory = blogCategoryService
					.getBlogCategory(project.getId(), categoryLabel);
		}
		page = blogPostService.getBlogPostPage(page, project, 
				blogCategory!=null?blogCategory.getId():null);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(BlogPost post : page.getResult()) {
			Statistic statistic = statisticService.getStatistic(post.getId(), BlogPost.NAME);
			if(statistic!=null) {
				post.setViews(statistic.getCount());
			}
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/posts_recent";
	}
	
	public String doListView(@Param Long categoryId, Project project,
			Page<BlogPost> page,  FragmentContext context) {
		page = blogPostService.getBlogPostPage(page, project, categoryId);
		for(BlogPost post : page.getResult()) {
			// views
			Statistic statistic = statisticService.getStatistic(post.getId(), BlogPost.NAME);
			if(statistic!=null) {
				post.setViews(statistic.getCount());
			}
			// count comment
			Long commentNumber = commentService.countComment(post.getId(), BlogPost.NAME);
			post.setCommentNumber(commentNumber);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "blog/posts_list";
	}
	
	public String doPostView(@Param Long postId, 
		User user, FragmentContext context) {
		BlogPost post = blogPostService.viewBlogPost(postId);
		context.putRequestData(AttributeKeys.BLOG_POST_KEY_READABLE, post);
		return "blog/post";
	}
	
	public String doFormView(@Param Long postId, 
			User user, Project project, FragmentContext context) {
		BlogPost post = new BlogPost();
		post.setEnteredId(user.getId());
		post.setModifiedId(user.getId());
		post.setProjectId(project.getId());
		if(postId!=null) {
			post = blogPostService.getBlogPost(postId);
		}
		context.putRequestData(AttributeKeys.BLOG_POST_KEY_READABLE, post);
		// blog categories
		List<BlogCategory> categories = blogCategoryService.getBlogCategoryList(project);
		context.putRequestData(AttributeKeys.BLOG_CATEGORY_LIST_KEY_READABLE, categories);
		return "blog/post_form";
	}
	
}
