package org.osforce.e2.entity.commons;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.e2.entity.system.Project;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Dec 6, 2010 - 4:42:41 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="statistics")
@Cacheable
public class Statistic extends IdEntity {

	private static final long serialVersionUID = 5152544874427530115L;
	
	private Long linkedId;
	private String entity;
	private Long count = 0L;
	// helper
	private Long projectId;
	private Object linkedEntity;
	// refer
	private Project project;
	
	public Statistic() {
	}
	
	public Statistic(Long linkedId, String entity) {
		this.linkedId = linkedId;
		this.entity = entity;
	}
	
	@Column(nullable=false)
	public Long getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Transient
	public void countAdd() {
		this.count += 1;
	}
	
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
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
	public Object getLinkedEntity() {
		return linkedEntity;
	}
	
	public void setLinkedEntity(Object linkedEntity) {
		this.linkedEntity = linkedEntity;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
}
