package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.commons.Template;
import org.osforce.e2.service.commons.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 11:10:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class TemplateController {
	
	private TemplateService templateService;

	public TemplateController() {
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@RequestMapping(value="/template", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Template template) {
		if(template.getId()==null) {
			templateService.createTemplate(template);
		} else {
			templateService.updateTemplate(template);
		}
		return Collections.singletonMap("id", template.getId());
	}
}
