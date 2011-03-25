package org.osforce.e2.entity.gallery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 9:54:46 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="albums")
@Cacheable
public class Album extends IdEntity {
	private static final long serialVersionUID = 6616350844990456656L;
	
	private String name;
	private Date entered;
	private Date modified;
	// helper
	private Long enteredId;
	private Long modifiedId;
	private Long projectId;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Project project;
	private List<Photo> photos = new ArrayList<Photo>();

	public Album() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEntered() {
		return entered;
	}

	public void setEntered(Date entered) {
		this.entered = entered;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Transient
	public Long getEnteredId() {
		if(enteredId==null && enteredBy!=null) {
			enteredId = enteredBy.getId();
		}
		return enteredId;
	}

	public void setEnteredId(Long enteredId) {
		this.enteredId = enteredId;
	}

	@Transient
	public Long getModifiedId() {
		if(modifiedId==null && modifiedBy!=null) {
			modifiedId = modifiedBy.getId();
		}
		return modifiedId;
	}

	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
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

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="modified_by_id")
	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="album")
	public List<Photo> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
}
