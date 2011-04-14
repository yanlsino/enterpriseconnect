package org.osforce.connect.entity.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.support.SiteEntity;


/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 4, 2010 - 11:48:55 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="project_categories")
@Cacheable
public class ProjectCategory extends SiteEntity{
	
	private static final long serialVersionUID = 6939240938778471219L;
	
	private String code;
	private String label;
	private Integer level;
	private Boolean enabled;
	// helper
	private Integer count = 0;
	private Long parentId;
	// refer
	private ProjectCategory parent;
	private List<ProjectCategory> children = new ArrayList<ProjectCategory>();
	
	public ProjectCategory() {
	}
	
	public ProjectCategory(String label, String code) {
		this.label = label;
		this.code = code;
	}

	@Column(nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(nullable=false)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 返回当前分类的 Project 个数
	 * @return size
	 */
	@Transient
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Transient
	public Long getParentId() {
		if(parentId==null && parent!=null) {
			parentId = parent.getId();
		}
		return parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public ProjectCategory getParent() {
		return parent;
	}
	
	public void setParent(ProjectCategory parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public List<ProjectCategory> getChildren() {
		return children;
	}
	
	public void setChildren(List<ProjectCategory> children) {
		this.children = children;
	}
	
}
