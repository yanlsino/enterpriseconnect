package org.osforce.connect.entity.profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;

@Entity
@Table(name="profiles")
@Indexed(index="profiles")
@Cacheable
public class Profile extends IdEntity {
	private static final long serialVersionUID = -7252838349384546418L;
	public static final String NAME = Profile.class.getSimpleName();

	private String title;
	private String shortDescription;
	private String description;
	private String attributes;
	private Date entered;
	private Date modified;
	// helper
	private Long enteredId;
	private Long modifiedId;
	private Long projectId;
	private Long logoId;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Project project;
	private Attachment logo;
	
	public Profile() {
	}
	
	@Field(name="title", index=Index.TOKENIZED, store=Store.NO)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(length=1000)
	@Field(name="shortDescription", index=Index.TOKENIZED, store=Store.NO)
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Lob@Type(type="org.hibernate.type.StringClobType")
	@Field(name="description", index=Index.UN_TOKENIZED, store=Store.NO)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Lob@Type(type="org.hibernate.type.StringClobType")
	public String getAttributes() {
		return attributes;
	}
	
	@Transient
	public Map<String, String> getAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		if(StringUtils.isNotBlank(attributes)) {
			String[] items = StringUtils.split(attributes, "|");
			for(String item : items) {
				String key = StringUtils.substringBefore(item, "=");
				String value = StringUtils.substringAfter(item, "=");
				map.put(key, value);
			}
		}
		return map;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	@Transient
	public void setAttributes(String[] labels, String[] values) {
		List<String> attributeList = new ArrayList<String>();
		if(labels!=null && values!=null) {
			for(int i=0; i<labels.length; i++) {
				if(StringUtils.isNotBlank(labels[i]) &&
						StringUtils.isNotBlank(values[i])){
					String kv = labels[i] + "=" + values[i];
					attributeList.add(kv);
				}
			}
		}
		attributes = StringUtils.join(attributeList, "|");
	}
	
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
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
	
	@Transient
	public Long getLogoId() {
		if(logoId==null && logo!=null) {
			logoId = logo.getId();
		}
		return logoId;
	}
	
	public void setLogoId(Long logoId) {
		this.logoId = logoId;
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
	@IndexedEmbedded
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="logo_id")
	public Attachment getLogo() {
		return logo;
	}
	
	public void setLogo(Attachment logo) {
		this.logo = logo;
	}
}
