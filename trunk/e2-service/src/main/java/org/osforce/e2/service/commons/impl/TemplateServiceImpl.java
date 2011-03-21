package org.osforce.e2.service.commons.impl;

import java.util.List;

import org.osforce.e2.dao.commons.TemplateDao;
import org.osforce.e2.dao.system.ProjectCategoryDao;
import org.osforce.e2.entity.commons.Template;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.service.commons.TemplateService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 10:16:18 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

	private TemplateDao templateDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public TemplateServiceImpl() {
	}
	
	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}
	
	public Template getTemplate(Long templateId) {
		return templateDao.get(templateId);
	}

	public void createTemplate(Template template) {
		updateTemplate(template);
	}

	public void updateTemplate(Template template) {
		if(template.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(template.getCategoryId());
			template.setCategory(category);
		}
		if(template.getId()==null) {
			templateDao.save(template);
		} else {
			templateDao.update(template);
		}
	}

	public void deleteTemplate(Long templateId) {
		templateDao.delete(templateId);
	}

	public Page<Template> getTemplatePage(Page<Template> page, Long siteId) {
		QueryAppender appender = new QueryAppender()
				.equal("template.category.site.id", siteId)
				.asc("template.code");
		return templateDao.findPage(page, appender);
	}
	
	public Template getTemplate(Long categoryId, String templateCode) {
		QueryAppender appender = new QueryAppender()
				.equal("template.category.id", categoryId)
				.equal("template.code", templateCode);
		return templateDao.findUnique(appender);
	}
	
	public List<Template> getTemplateList(Long siteId) {
		QueryAppender appender = new QueryAppender()
				.equal("template.category.site.id", siteId)
				.asc("template.code");
		return templateDao.find(appender);
	}
}
