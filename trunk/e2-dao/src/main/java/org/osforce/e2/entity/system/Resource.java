package org.osforce.e2.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 10:59:16 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="resources")
@Cacheable
public class Resource extends IdEntity {
	private static final long serialVersionUID = -8927262837858587151L;
	
	private String name; // 
	private String code; // project-blog-view project-blog-add
	private String description;
	private Boolean enabled;
	
	public Resource() {
	}
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(unique=true, nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
