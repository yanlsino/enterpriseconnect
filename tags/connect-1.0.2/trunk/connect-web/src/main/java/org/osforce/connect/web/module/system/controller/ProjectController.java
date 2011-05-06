package org.osforce.connect.web.module.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.service.system.ProjectFeatureService;
import org.osforce.connect.service.system.ProjectService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 20, 2011 - 4:50:15 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class ProjectController {

	private ProjectService projectService;
	private ProjectFeatureService projectFeatureService;
	
	public ProjectController() {
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
	
	@RequestMapping(value="/project", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(
			Project project, WebRequest request) {
		if(project.getId()==null) {
			projectService.createProject(project);
		} else {
			projectService.updateProject(project);
		}
		Project tmp = (Project) request.getAttribute(
				AttributeKeys.PROJECT_KEY,  WebRequest.SCOPE_SESSION);
		// create project feature
		List<ProjectFeature> features = tmp.getFeatures();
		for(ProjectFeature feature : features) {
			feature.setProject(project);
			projectFeatureService.createProjectFeature(feature);
		}
		//
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", project.getId());
		model.put("uniqueId", project.getUniqueId());
		return  model;
	}
	
	@RequestMapping(value="project/exist", method=RequestMethod.GET)
	public @ResponseBody Boolean exist(@RequestParam String uniqueId) {
		Project project = projectService.getProject(uniqueId);
		return project!=null;
	}
}
