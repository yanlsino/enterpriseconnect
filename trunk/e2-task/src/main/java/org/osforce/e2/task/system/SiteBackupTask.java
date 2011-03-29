package org.osforce.e2.task.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.osforce.commons.xml.XMLUtil;
import org.osforce.e2.entity.commons.Template;
import org.osforce.e2.entity.system.MailSettings;
import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.Theme;
import org.osforce.e2.service.commons.TemplateService;
import org.osforce.e2.service.system.PermissionService;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.e2.service.system.ResourceService;
import org.osforce.e2.service.system.RoleService;
import org.osforce.e2.service.system.SiteService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 15, 2011 - 3:23:05 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SiteBackupTask extends AbstractTask implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	private SiteService siteService;
	private RoleService roleService;
	private ResourceService resourceService;
	private PermissionService permissionService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	private Document xmlDoc = XMLUtil.createDocument();
	
	public SiteBackupTask() {
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
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long siteId = (Long) context.get("siteId");
		Site site = siteService.getSite(siteId);
		//
		Element dataEle = xmlDoc.createElement("data");
		xmlDoc.appendChild(dataEle);
		//
		backupSite(site, dataEle);
		backupProjectCategory(site, dataEle);
		backupRole(site, dataEle);
		backupResource(site, dataEle);
		backupPermission(site, dataEle);
		backupTemplate(site, dataEle);
		//
		String xmlStr = XMLUtil.toString(xmlDoc);
		org.springframework.core.io.Resource resource = resourceLoader
				.getResource("/WEB-INF/sites/"+site.getDomain()+"/site.xml");
		FileUtils.writeStringToFile(resource.getFile(), xmlStr);
	}
	
	protected void backupSite(Site site, Element dataEle) {
		Element siteEle = xmlDoc.createElement("site");
		siteEle.setAttribute("title", site.getTitle());
		siteEle.setAttribute("keywords", site.getKeywords());
		siteEle.setAttribute("description", site.getDescription());
		siteEle.setAttribute("domain", site.getDomain());
		siteEle.setAttribute("port", String.valueOf(site.getPort()));
		siteEle.setAttribute("contextPath", site.getContextPath());
		siteEle.setAttribute("enabled", String.valueOf(site.getEnabled()));
		Theme theme = site.getTheme();
		Element themeEle = xmlDoc.createElement("theme");
		themeEle.setAttribute("name", theme.getName());
		themeEle.setAttribute("enabled", String.valueOf(theme.getEnabled()));
		MailSettings mailSettings = site.getMailSettings();
		Element settingsEle = xmlDoc.createElement("mail");
		settingsEle.setAttribute("host", mailSettings.getHost());
		settingsEle.setAttribute("port", String.valueOf(mailSettings.getPort()));
		settingsEle.setAttribute("username", mailSettings.getUsername());
		settingsEle.setAttribute("password", mailSettings.getPassword());
		settingsEle.setAttribute("enabled", String.valueOf(mailSettings.getEnabled()));
		dataEle.appendChild(siteEle);
		siteEle.appendChild(themeEle);
		siteEle.appendChild(settingsEle);
	}
	
	protected void backupPermission(Site site, Element dataEle) {
		List<Permission> permissions = permissionService.getPermissionList(site.getId(), null);
		Element permissionsEle = xmlDoc.createElement("permissions");
		for(Permission permission : permissions) {
			Element permissionEle = xmlDoc.createElement("permission");
			permissionEle.setAttribute("roleId", String.valueOf(permission.getRoleId()));
			permissionEle.setAttribute("resourceId", String.valueOf(permission.getResourceId()));
			permissionEle.setAttribute("categoryId", String.valueOf(permission.getCategoryId()));
			permissionEle.setAttribute("enabled", String.valueOf(permission.getEnabled()));
			permissionsEle.appendChild(permissionEle);
		}
		dataEle.appendChild(permissionsEle);
	}
	
	protected void backupResource(Site site, Element dataEle) {
		List<Resource> resources = resourceService.getResourceList();
		Element resourcesEle = xmlDoc.createElement("resources");
		for(Resource resource : resources) {
			Element resourceEle = xmlDoc.createElement("resource");
			resourceEle.setAttribute("name", resource.getName());
			resourceEle.setAttribute("code", resource.getCode());
			resourceEle.setAttribute("description", resource.getDescription());
			resourceEle.setAttribute("enabled", "true");
			resourcesEle.appendChild(resourceEle);
		}
		dataEle.appendChild(resourcesEle);
	}
	
	protected void backupRole(Site site, Element dataEle) {
		List<Role> roles = roleService.getRoleList(site);
		Element rolesEle = xmlDoc.createElement("roles");
		for(Role role : roles) {
			Element roleEle = xmlDoc.createElement("role");
			roleEle.setAttribute("name", role.getName());
			roleEle.setAttribute("code", role.getCode());
			roleEle.setAttribute("level", String.valueOf(role.getLevel()));
			roleEle.setAttribute("description", role.getDescription());
			roleEle.setAttribute("categoryId", String.valueOf(role.getCategoryId()));
			roleEle.setAttribute("enabled", String.valueOf(role.getEnabled()));
			rolesEle.appendChild(roleEle);
		}
		dataEle.appendChild(rolesEle);
	}
	
	protected void backupTemplate(Site site, Element dataEle) {
		List<Template> templates = templateService.getTemplateList(site.getId());
		Element templatesEle = xmlDoc.createElement("templates");
		for(Template template : templates) {
			Element templateEle = xmlDoc.createElement("template");
			templateEle.setAttribute("name", template.getName());
			templateEle.setAttribute("code", template.getCode());
			templateEle.setAttribute("content", template.getContent());
			templateEle.setAttribute("categoryId", String.valueOf(template.getCategoryId()));
			templateEle.setAttribute("enabled", String.valueOf(template.getEnabled()));
			templatesEle.appendChild(templateEle);
		}
		dataEle.appendChild(templatesEle);
	}
	
	protected void backupProjectCategory(Site site, Element dataEle) {
		List<ProjectCategory> categories = projectCategoryService.getProjectCategoryList(site.getId());
		Element categoriesEle = xmlDoc.createElement("categories");
		for(ProjectCategory category : categories) {
			Element categoryEle = xmlDoc.createElement("category");
			categoryEle.setAttribute("label", category.getLabel());
			categoryEle.setAttribute("code", category.getCode());
			if(category.getParentId()!=null) { 
				categoryEle.setAttribute("parentId", String.valueOf(category.getParentId()));
			}
			categoryEle.setAttribute("enabled", String.valueOf(category.getEnabled()));
			categoriesEle.appendChild(categoryEle);
		}
		dataEle.appendChild(categoriesEle);
	}
	
}
