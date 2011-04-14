package org.osforce.connect.web.module.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.service.system.ProjectFeatureService;
import org.osforce.connect.service.system.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 18, 2011 - 9:07:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private ProjectService projectService;
	private ProjectFeatureService projectFeatureService;
	
	public AdminController() {
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	public void setProjectFeatureService(
			ProjectFeatureService projectFeatureService) {
		this.projectFeatureService = projectFeatureService;
	}
	
	@RequestMapping(value="/feature", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(ProjectFeature feature) {
		if(feature.getId()==null) {
			projectFeatureService.createProjectFeature(feature);
		} else {
			projectFeatureService.updateProjectFeature(feature);
		}
		//
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", feature.getId());
		return model;
	}
	
	@RequestMapping(value="/project", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(Project project, WebRequest request) {
		if(project.getId()==null) {
			projectService.createProject(project);
		} else {
			projectService.updateProject(project);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", project.getId());
		model.put("uniqueId", project.getUniqueId());
		return model;
	}

}
