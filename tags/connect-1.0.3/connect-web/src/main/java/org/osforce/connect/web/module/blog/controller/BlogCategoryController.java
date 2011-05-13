package org.osforce.connect.web.module.blog.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.connect.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 11:13:56 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/blog")
public class BlogCategoryController {

	private BlogCategoryService blogCategoryService;
	
	public BlogCategoryController() {
	}
	
	@Autowired
	public void setBlogCategoryService(BlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(BlogCategory category) {
		if(category.getId()==null) {
			blogCategoryService.createBlogCategory(category);
		} else {
			blogCategoryService.updateBlogCategory(category);
		}
		return Collections.singletonMap("id", category.getId());
	}
}
