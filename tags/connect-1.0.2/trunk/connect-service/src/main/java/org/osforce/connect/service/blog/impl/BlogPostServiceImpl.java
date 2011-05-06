package org.osforce.connect.service.blog.impl;

import java.util.Date;

import org.osforce.connect.dao.blog.BlogCategoryDao;
import org.osforce.connect.dao.blog.BlogPostDao;
import org.osforce.connect.dao.system.ProjectDao;
import org.osforce.connect.dao.system.UserDao;
import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.blog.BlogPostService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:19:23 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {

	private UserDao userDao;
	private ProjectDao projectDao;
	private BlogPostDao blogPostDao;
	private BlogCategoryDao blogCategoryDao;
	
	public BlogPostServiceImpl() {
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Autowired
	public void setBlogPostDao(BlogPostDao blogPostDao) {
		this.blogPostDao = blogPostDao;
	}
	
	@Autowired
	public void setBlogCategoryDao(BlogCategoryDao blogCategoryDao) {
		this.blogCategoryDao = blogCategoryDao;
	}

	public BlogPost viewBlogPost(Long postId) {
		return blogPostDao.get(postId);
	}
	
	public BlogPost getBlogPost(Long postId) {
		return blogPostDao.get(postId);
	}

	public void createBlogPost(BlogPost post) {
		updateBlogPost(post);
	}

	public void updateBlogPost(BlogPost post) {
		if(post.getEnteredId()!=null) {
			User enteredBy = userDao.get(post.getEnteredId());
			post.setEnteredBy(enteredBy);
		}
		if(post.getModifiedId()!=null) {
			User modifiedBy = userDao.get(post.getModifiedId());
			post.setModifiedBy(modifiedBy);
		}
		if(post.getCategoryId()!=null) {
			BlogCategory category = blogCategoryDao.get(post.getCategoryId());
			post.setCategory(category);
		}
		if(post.getProjectId()!=null) {
			Project project = projectDao.get(post.getProjectId());
			post.setProject(project);
		}
		Date now = new Date();
		post.setModified(now);
		if(post.getId()==null) {
			post.setEntered(now);
			blogPostDao.save(post);
		} else {
			blogPostDao.update(post);
		}
	}

	public void deleteBlogPost(Long postId) {
		blogPostDao.delete(postId);
	}
	
	public Page<BlogPost> getBlogPostPage(Page<BlogPost> page) {
		QueryAppender appender = new QueryAppender();
		appender.desc("blogPost.entered");
		return blogPostDao.findPage(page, appender);
	}
	
	public Page<BlogPost> getBlogPostPage(Page<BlogPost> page,
			Project project) {
		QueryAppender appender = new QueryAppender();
		if(project!=null) {
			appender.equal("blogPost.project.id", project.getId());
		}
		appender.desc("blogPost.entered");
		return blogPostDao.findPage(page, appender);
	}
	
	public Page<BlogPost> getBlogPostPage(Page<BlogPost> page, Project project,
			Long categoryId) {
		QueryAppender appender = new QueryAppender();
		if(project!=null) {
			appender.equal("blogPost.project.id", project.getId());
		}
		if(categoryId!=null) {
			appender.equal("blogPost.category.id", categoryId);
		}
		appender.desc("blogPost.entered");
		return blogPostDao.findPage(page, appender);
	}
}
