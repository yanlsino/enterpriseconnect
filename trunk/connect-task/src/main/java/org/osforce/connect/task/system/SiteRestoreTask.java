package org.osforce.connect.task.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.castor.CastorUtil;
import org.osforce.commons.xml.XMLUtil;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.commons.TemplateService;
import org.osforce.connect.service.system.PermissionService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.service.system.ResourceService;
import org.osforce.connect.service.system.RoleService;
import org.osforce.connect.service.system.SiteService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 15, 2011 - 5:17:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SiteRestoreTask extends AbstractTask implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	private SiteService siteService;
	private RoleService roleService;
	private ResourceService resourceService;
	private TemplateService templateService;
	private PermissionService permissionService;
	private ProjectCategoryService projectCategoryService;
	
	public SiteRestoreTask() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long siteId = (Long) context.get("siteId");
		Site site = siteService.getSite(siteId);
		//
		org.springframework.core.io.Resource resource = resourceLoader
				.getResource("/WEB-INF/sites/"+site.getDomain()+"/site.xml");
		Element dataEle = XMLUtil.parseToElement(resource.getFile());
		//
		restoreSite(site, dataEle);
		restoreProjectCategory(site, dataEle);
		restoreRole(site, dataEle);
		restoreResource(site, dataEle);
		restorePermission(site, dataEle);
		restoreTemplate(site, dataEle);
	}
	
	protected void restoreSite(Site site, Element dataEle) {
		Element siteEle = XMLUtil.getChildElement(dataEle, "site");
		site.setTitle(XMLUtil.getAttribute(siteEle, "title"));
		site.setDescription(XMLUtil.getAttribute(siteEle, "description"));
		site.setKeywords(XMLUtil.getAttribute(siteEle, "keywords"));
		Integer port1 = CastorUtil.castTo(XMLUtil.getAttribute(siteEle, "port"), Integer.class);
		site.setPort(port1);
		site.setContextPath(XMLUtil.getAttribute(siteEle, "contextPath"));
		Boolean enabled1 = StringUtils.equals(XMLUtil.getAttribute(siteEle, "enabled"), "true");
		site.setEnabled(enabled1);
		Element themeEle = XMLUtil.getChildElement(siteEle, "theme");
		Theme theme = site.getTheme();
		theme.setName(XMLUtil.getAttribute(themeEle, "name"));
		Boolean enabled2 = StringUtils.equals(XMLUtil.getAttribute(themeEle, "enabled"), "true");
		theme.setEnabled(enabled2);
		site.setTheme(theme);
		Element mailEle = XMLUtil.getChildElement(siteEle, "mail");
		MailSettings settings = site.getMailSettings();
		settings.setHost(XMLUtil.getAttribute(mailEle, "host"));
		Integer port2 = CastorUtil.castTo(XMLUtil.getAttribute(mailEle, "port"), Integer.class);
		settings.setPort(port2);
		settings.setUsername(XMLUtil.getAttribute(mailEle, "username"));
		settings.setPassword(XMLUtil.getAttribute(mailEle, "password"));
		Boolean enabled3 = StringUtils.equals(XMLUtil.getAttribute(mailEle, "enabled"), "true");
		settings.setEnabled(enabled3);
		site.setMailSettings(settings);
		siteService.updateSite(site);
	}
	
	protected void restoreProjectCategory(Site site, Element dataEle) {
		Element categoriesEle = XMLUtil.getChildElement(dataEle, "categories");
		List<Element> categoryEles = XMLUtil.getChildElements(categoriesEle, "category");
		for(Element categoryEle : categoryEles) {
			String code = XMLUtil.getAttribute(categoryEle, "code");
			ProjectCategory projectCategory = projectCategoryService.getProjectCategory(site, code);
			if(projectCategory==null) {
				projectCategory = new ProjectCategory();
			}
			projectCategory.setCode(code);
			projectCategory.setLabel(XMLUtil.getAttribute(categoryEle, "label"));
			Integer level = CastorUtil.castTo(XMLUtil.getAttribute(categoryEle, "level"), Integer.class);
			projectCategory.setLevel(level);
			projectCategory.setSite(site);
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(categoryEle, "enabled"), "true");
			projectCategory.setEnabled(enabled);
			Long parentId = CastorUtil.castTo(XMLUtil.getAttribute(categoryEle, "parentId"), Long.class);
			projectCategory.setParentId(parentId);
			projectCategoryService.createProjectCategory(projectCategory);
		}
	}
	
	protected void restoreRole(Site site, Element dataEle) {
		Element rolesEle =  XMLUtil.getChildElement(dataEle, "roles");
		List<Element> roleEles = XMLUtil.getChildElements(rolesEle, "role");
		for(Element roleEle : roleEles) {
			String code = XMLUtil.getAttribute(roleEle, "code");
			Long categoryId = CastorUtil.castTo(XMLUtil.getAttribute(roleEle, "categoryId"), Long.class);
			Role role = roleService.getRole(code, categoryId);
			if(role==null) {
				role = new Role();
			}
			role.setName(XMLUtil.getAttribute(roleEle, "name"));
			role.setCode(code);
			role.setCategoryId(categoryId);
			role.setDescription(XMLUtil.getAttribute(roleEle, "description"));
			Integer level = CastorUtil.castTo(XMLUtil.getAttribute(roleEle, "level"), Integer.class);
			role.setLevel(level);
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(roleEle, "enabled"), "true");
			role.setEnabled(enabled);
			roleService.createRole(role);
		}
	}
	
	protected void restoreResource(Site site, Element dataEle) {
		Element resourcesEle = XMLUtil.getChildElement(dataEle, "resources");
		List<Element> resourceEles = XMLUtil.getChildElements(resourcesEle, "resource");
		for(Element resourceEle : resourceEles) {
			String code = XMLUtil.getAttribute(resourceEle, "resource");
			Resource resource = resourceService.getResource(code);
			if(resource==null) {
				resource = new Resource();
			}
			resource.setName(XMLUtil.getAttribute(resourceEle, "name"));
			resource.setCode(code);
			resource.setDescription(XMLUtil.getAttribute(resourceEle, "description"));
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(resourceEle, "enabled"), "true");
			resource.setEnabled(enabled);
			resourceService.createResource(resource);
		}
	}
	
	protected void restorePermission(Site site, Element dataEle) {
		Element permissionsEle = XMLUtil.getChildElement(dataEle, "permissions");
		List<Element> permissionEles =  XMLUtil.getChildElements(permissionsEle, "permission");
		for(Element permissionEle : permissionEles) {
			Long resourceId = CastorUtil.castTo(XMLUtil.getAttribute(permissionEle, "resourceId"), Long.class);
			Long categoryId = CastorUtil.castTo(XMLUtil.getAttribute(permissionEle, "categoryId"), Long.class);
			Permission permission = permissionService.getPermission(resourceId, categoryId);
			if(permission==null) {
				permission = new Permission();
			}
			Long roleId = CastorUtil.castTo(XMLUtil.getAttribute(permissionEle, "roleId"), Long.class);
			permission.setResourceId(resourceId);
			permission.setCategoryId(categoryId);
			permission.setRoleId(roleId);
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(permissionEle, "enabled"), "true");
			permission.setEnabled(enabled);
			permissionService.createPermission(permission);
		}
	}
	
	protected void restoreTemplate(Site site, Element dataEle) {
		Element templatesEle = XMLUtil.getChildElement(dataEle, "templates");
		List<Element> templateEles = XMLUtil.getChildElements(templatesEle, "template");
		for(Element templateEle : templateEles) {
			String code = XMLUtil.getAttribute(templateEle, "code");
			Long categoryId = CastorUtil.castTo(XMLUtil.getAttribute(templateEle, "categoryId"), Long.class);
			Template template = templateService.getTemplate(categoryId, code);
			if(template==null) {
				template = new Template();
			}
			template.setName(XMLUtil.getAttribute(templateEle, "name"));
			template.setCode(code);
			template.setContent(XMLUtil.getAttribute(templateEle, "content"));
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(templateEle, "enabled"), "true");
			template.setEnabled(enabled);
			template.setCategoryId(categoryId);
			templateService.createTemplate(template);
		}
	}
	
}
