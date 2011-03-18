package org.osforce.e2.entity.team;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 25, 2010 - 10:25:30 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="team_members")
@Cacheable
public class TeamMember extends IdEntity {
	private static final long serialVersionUID = -3168336180073285176L;
	
	public static final String NAME = TeamMember.class.getSimpleName();
	
	private Boolean enabled = false;
	// helper
	private Long userId;
	private Long projectId;
	private Long roleId;
	// refer
	private User user;
	private Role role;
	private Project project;
	
	public TeamMember() {
	}
	
	public TeamMember(User user) {
		this.user = user;
	}

	public TeamMember(Project project, User user, Role defaultRole) {
		this.project = project;
		this.user = user;
		this.role = defaultRole;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getUserId() {
		if(userId==null && user!=null) {
			userId = user.getId();
		}
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Long getRoleId() {
		if(roleId==null && role!=null) {
			roleId = role.getId();
		}
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
