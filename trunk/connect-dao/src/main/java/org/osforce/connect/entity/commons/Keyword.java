package org.osforce.connect.entity.commons;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 10, 2011 - 2:32:39 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="keywords")
@Cacheable
public class Keyword extends IdEntity {
	
	private static final long serialVersionUID = -2051638401046632347L;
	
	public static final String TYPE_SEARCH = "search"; 
	
	private String keyword;
	private String type;
	// 表识一个关键字是第几次出现
	private Long number;
	private Date entered;
	// refer
	private ProjectCategory category;
	private User enteredBy;
	
	public Keyword() {
	}
	
	public Keyword(Long id, String keyword) {
		this.id = id;
		this.keyword = keyword;
	}

	@Column(nullable=false)
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Column(nullable=false)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(nullable=false)
	public Long getNumber() {
		return number;
	}
	
	public void setNumber(Long number) {
		this.number = number;
	}
	
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public ProjectCategory getCategory() {
		return category;
	}

	public void setCategory(ProjectCategory category) {
		this.category = category;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}
	
	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}
	
}
