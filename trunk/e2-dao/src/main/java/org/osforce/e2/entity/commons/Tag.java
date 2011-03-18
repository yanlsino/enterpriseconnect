package org.osforce.e2.entity.commons;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:12:10 AM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name = "tags")
@Cacheable
public class Tag extends IdEntity {
	private static final long serialVersionUID = -2365317233401737395L;

	private String name;
	private Long linkedId;
	private String entity;
	// helper
	private Object linkedEntity;

	public Tag() {
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public Long getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
	}

	@Column(nullable=false)
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Transient
	public Object getLinkedEntity() {
		return linkedEntity;
	}
	
	public void setLinkedEntity(Object linkedEntity) {
		this.linkedEntity = linkedEntity;
	}
}
