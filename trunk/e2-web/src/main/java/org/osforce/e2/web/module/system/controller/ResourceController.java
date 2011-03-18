package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.service.system.ResourceService;
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
 * @create Feb 25, 2011 - 11:16:34 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class ResourceController {

	private ResourceService resourceService;
	
	public ResourceController() {
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@RequestMapping(value="/resource", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Resource resource) {
		if(resource.getId()==null) {
			resourceService.createResource(resource);
		} else {
			resourceService.updateResource(resource);
		}
		return Collections.singletonMap("id", resource.getId());
	}
	
	@RequestMapping(value="/resources/synchronize")
	public String synchronize(@RequestParam Long siteId) {
		resourceService.syncResource();
		return "redirect:/system/resources?siteId="+siteId;
	}

}
