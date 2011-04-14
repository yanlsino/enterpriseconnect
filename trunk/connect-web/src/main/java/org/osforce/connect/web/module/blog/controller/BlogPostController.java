package org.osforce.connect.web.module.blog.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.connect.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 10:10:11 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/blog")
public class BlogPostController {

	private BlogPostService blogPostService;
	
	public BlogPostController() {
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(BlogPost post) {
		if(post.getId()==null) {
			blogPostService.createBlogPost(post);
		} else {
			blogPostService.updateBlogPost(post);
		}
		return Collections.singletonMap("id", post.getId());
	}
	
	public @ResponseBody String upload(@RequestParam Long attachmentId) {
		
		return "";
	}
}
