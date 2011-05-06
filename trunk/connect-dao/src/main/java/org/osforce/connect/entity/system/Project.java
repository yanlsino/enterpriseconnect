package org.osforce.connect.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 4, 2010 - 11:47:28 AM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="projects")
@Cacheable
public class Project extends IdEntity {
	private static final long serialVersionUID = -8971421207588403404L;
	
	public static final String NAME = Project.class.getSimpleName();
	
	private String uniqueId;
	private String title;
	private Date entered;
	private Date modified;
	private Boolean enabled = false;
	// helper
	private Long enteredId;
	private Long modifiedId;
	private Long categoryId;
	private Long subCategoryId1;
	private Long subCategoryId2;
	private Long subCategoryId3;
	private Long profileId;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Profile profile;
	private ProjectCategory category;
	private ProjectCategory subCategory1;
	private ProjectCategory subCategory2;
	private ProjectCategory subCategory3;
	private List<ProjectFeature> features = new ArrayList<ProjectFeature>();

	public Project() {
	}

	@Column(unique=true, nullable=false)
	@Field(store=Store.YES, index=Index.UN_TOKENIZED)
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Field(store=Store.NO, index=Index.TOKENIZED)
	@Column(nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEntered() {
		return entered;
	}

	public void setEntered(Date entered) {
		this.entered = entered;
	}

	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	public Long getCategoryId() {
		if(categoryId==null && category!=null) {
			categoryId = category.getId();
		}
		return categoryId;
	}
	
	@Transient
	public Long getSubCategoryId1() {
		if(subCategoryId1==null && subCategory1!=null)  {
			subCategoryId1 = subCategory1.getId();
		}
		return subCategoryId1;
	}
	
	public void setSubCategoryId1(Long subCategoryId1) {
		this.subCategoryId1 = subCategoryId1;
	}
	
	@Transient
	public Long getSubCategoryId2() {
		if(subCategoryId2==null && subCategory2!=null)  {
			subCategoryId2 = subCategory2.getId();
		}
		return subCategoryId2;
	}
	
	public void setSubCategoryId2(Long subCategoryId2) {
		this.subCategoryId2 = subCategoryId2;
	}
	
	@Transient
	public Long getSubCategoryId3() {
		if(subCategoryId3==null && subCategory3!=null)  {
			subCategoryId3 = subCategory3.getId();
		}
		return subCategoryId3;
	}
	
	public void setSubCategoryId3(Long subCategoryId3) {
		this.subCategoryId3 = subCategoryId3;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	public Long getProfileId() {
		if(profileId==null && profile!=null) {
			profileId = profile.getId();
		}
		return profileId;
	}
	
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="modified_by_id")
	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@OneToOne(mappedBy="project")
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="category_id")
	@IndexedEmbedded
	public ProjectCategory getCategory() {
		return category;
	}

	public void setCategory(ProjectCategory category) {
		this.category = category;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sub_category1_id")
	public ProjectCategory getSubCategory1() {
		return subCategory1;
	}
	
	public void setSubCategory1(ProjectCategory subCategory1) {
		this.subCategory1 = subCategory1;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sub_category2_id")
	public ProjectCategory getSubCategory2() {
		return subCategory2;
	}
	
	public void setSubCategory2(ProjectCategory subCategory2) {
		this.subCategory2 = subCategory2;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sub_category3_id")
	public ProjectCategory getSubCategory3() {
		return subCategory3;
	}
	
	public void setSubCategory3(ProjectCategory subCategory3) {
		this.subCategory3 = subCategory3;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="project")
	@OrderBy("level")
	public List<ProjectFeature> getFeatures() {
		return features;
	}
	
	public void setFeatures(List<ProjectFeature> features) {
		this.features = features;
	}
	
}
