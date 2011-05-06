package org.osforce.connect.entity.commons;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 28, 2010 - 2:48:00 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="templates")
@Cacheable
public class Template extends IdEntity {

	private static final long serialVersionUID = 7435717564480354437L;

	private String name;
	private String code;
	private String content;
	private Boolean enabled;
	// helper
	private Long categoryId;
	// refer
	private ProjectCategory category;
	
	public Template() {
	}
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Lob@Column(nullable=false)
	@Type(type="org.hibernate.type.StringClobType")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public ProjectCategory getCategory() {
		return category;
	}
	
	public void setCategory(ProjectCategory category) {
		this.category = category;
	}

}
