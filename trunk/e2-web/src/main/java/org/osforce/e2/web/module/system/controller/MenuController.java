package org.osforce.e2.web.module.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 7:04:25 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class MenuController {

	private ProjectCategoryService projectCategoryService;
	
	public MenuController() {
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(ProjectCategory category) {
		if(category.getId()==null) {
			projectCategoryService.createProjectCategory(category);
		} else {
			projectCategoryService.updateProjectCategory(category);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", category.getId());
		model.put("siteId", category.getSiteId());
		return model;
	}
}
