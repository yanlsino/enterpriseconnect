package org.osforce.connect.entity.discussion;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.system.Project;
import org.osforce.platform.entity.support.IdEntity;

/**
 * use commons category
 * @author gavin
 * @since 1.0.0
 * @create Nov 13, 2010 - 11:27:18 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="topic_categories")
@Cacheable
public class TopicCategory extends IdEntity {

	private static final long serialVersionUID = 6450678968833158268L;
	
	private String label;
	private Boolean enabled = false;
	// helper
	private Long projectId;
	// refer
	private Project project; 

	public TopicCategory() {
	}

	@Column(nullable=false)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
