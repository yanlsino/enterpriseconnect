package org.osforce.connect.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 3, 2010 - 9:42:11 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="permissions")
@Cacheable
public class Permission extends IdEntity {
	private static final long serialVersionUID = 4919735365992683302L;
	
	private Boolean enabled = true;
	// helper
	private Long roleId;
	private Long projectId;
	private Long resourceId;
	private Long categoryId;
	// refer
	private Role role;
	private Project project;
	private Resource resource;
	private ProjectCategory category;
	
	public Permission() {
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getRoleId() {
		if(roleId==null && role!=null) {
			roleId = role.getId();
		}
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Transient
	public Long getProjectId() {
		if(projectId==null && project!=null) {
			projectId = project.getId();
		}
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	@Transient
	public Long getCategoryId() {
		if(categoryId==null && category!=null) {
			categoryId = category.getId();
		}
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	public Long getResourceId() {
		if(resourceId==null && resource!=null) {
			resourceId = resource.getId();
		}
		return resourceId;
	}
	
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="resource_id")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="category_id")
	public ProjectCategory getCategory() {
		return category;
	}
	
	public void setCategory(ProjectCategory category) {
		this.category = category;
	}
}