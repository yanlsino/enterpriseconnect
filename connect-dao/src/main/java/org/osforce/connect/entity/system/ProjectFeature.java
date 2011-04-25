package org.osforce.connect.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
 * @create Jan 29, 2011 - 10:41:25 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="project_features")
@Cacheable
public class ProjectFeature extends IdEntity {
	private static final long serialVersionUID = 918888194481698938L;

	private String label;
	private String code;
	private Integer level;
	private Boolean show = false;
	// helper
	private Long roleId;
	private Long projectId;
	private String roleCode;
	// refer
	private Role role;
	private Project project;

	public ProjectFeature() {
	}

	public ProjectFeature(String label, String code) {
		this.label = label;
		this.code = code;
		this.show = true;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
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
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
