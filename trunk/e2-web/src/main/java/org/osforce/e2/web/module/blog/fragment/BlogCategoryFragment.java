package org.osforce.e2.web.module.blog.fragment;

import java.util.List;

import org.osforce.e2.entity.blog.BlogCategory;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.service.blog.BlogCategoryService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 11:06:23 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogCategoryFragment {

	private BlogCategoryService blogCategoryService;
	
	public BlogCategoryFragment() {
	}
	
	@Autowired
	public void setBlogCategoryService(BlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}
	
	public String doListView(Project project, FragmentContext context) {
		List<BlogCategory> categories = blogCategoryService
				.getBlogCategoryList(project);
		if(categories.size()==0) {
			return "commons/blank";
		}
		context.putRequestData(AttributeKeys.BLOG_CATEGORY_LIST_KEY_READABLE, categories);
		return "blog/categories_list";
	}
	
	public String doFormView(@Param Long categoryId, 
			Project project, FragmentContext context) {
		BlogCategory category = new BlogCategory();
		category.setProjectId(project.getId());
		if(categoryId!=null) {
			category = blogCategoryService.getBlogCategory(categoryId);
		}
		context.putRequestData(AttributeKeys.BLOG_CATEGORY_KEY_READABLE, category);
		return "blog/category_form";
	}
}
