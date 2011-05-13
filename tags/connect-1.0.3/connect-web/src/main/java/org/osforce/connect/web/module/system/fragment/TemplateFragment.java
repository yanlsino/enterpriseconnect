package org.osforce.connect.web.module.system.fragment;

import java.util.List;

import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.ProjectCategoryService;
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
 * @create Feb 13, 2011 - 10:09:54 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TemplateFragment {

	private TemplateService templateService;
	private ProjectCategoryService  projectCategoryService;
	
	public TemplateFragment() {
	}

	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doListView(@Param Long siteId, 
			Page<Template> page, FragmentContext context) {
		page = templateService.getTemplatePage(page, siteId);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/templates_list";
	}
	
	public String doFormView(@Param Long templateId,
			@Param Long siteId, FragmentContext context) {
		Template template = new Template();
		if(templateId!=null) {
			template = templateService.getTemplate(templateId);
		}
		List<ProjectCategory> categories =  projectCategoryService.getProjectCategoryList(siteId);
		context.putRequestData(AttributeKeys.TEMPLATE_KEY_READABLE, template);
		context.putRequestData(AttributeKeys.PROJECT_CATEGORY_LIST_KEY_READABLE, categories);
		return "system/template_form";
	}
}
