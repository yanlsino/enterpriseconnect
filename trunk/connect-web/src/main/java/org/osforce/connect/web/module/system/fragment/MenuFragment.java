package org.osforce.connect.web.module.system.fragment;

import java.util.List;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 11:55:15 AM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MenuFragment {

	private UserService userService;
	private ProjectCategoryService projectCategoryService;

	public MenuFragment() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}

	public String doListView(@Param Long siteId, @Param Long parentId,
			Page<ProjectCategory> page, FragmentContext context) {
		if(parentId!=null) {
			ProjectCategory parent = projectCategoryService.getProjectCategory(parentId);
			context.putRequestData(AttributeKeys.PARENT_KEY_READABLE, parent);
		}
		page = projectCategoryService.getProjectCategoryPage(page, siteId, parentId); 
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/categories_list";
	}

	public String doFormView(@Param Long categoryId, 
			@Param Long parentId, @Param Long siteId, FragmentContext context) {
		ProjectCategory category = new ProjectCategory();
		category.setSiteId(siteId);
		category.setParentId(parentId);
		if(parentId!=null) {
			List<ProjectCategory> categories = projectCategoryService.getSiblingProjectCategoryList(siteId, parentId);
			context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		} else if (categoryId!=null) {
			category = projectCategoryService.getProjectCategory(categoryId);
			if(category.getParent()!=null) {
				List<ProjectCategory> categories = projectCategoryService
						.getSiblingProjectCategoryList(siteId, category.getParentId());
				context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
			}
		}
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_KEY_READABLE, category);
		return "system/category_form";
	}
	
	public String doMainMenuView(@Param String viewName,
			Project project, User user, Site site, FragmentContext context) {
		if(user!=null) {
			User persisted = userService.getUser(user.getId());
			context.putRequestData(AttributeKeys.USER_KEY_READABLE, persisted);
		}
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(site.getId());
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/main_menu";
	}
	
	public String doUserMenuView(User user, FragmentContext context) {
		if(user!=null) {
			User persisted = userService.getUser(user.getId());
			context.putRequestData(AttributeKeys.USER_KEY_READABLE, persisted);
		}
		return "system/user_menu";
	}
	
}
