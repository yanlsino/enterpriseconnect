package org.osforce.e2.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 3:46:26 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="themes")
public class Theme extends IdEntity {
	private static final long serialVersionUID = -1683522732887797333L;

	private String name;
	private Boolean enabled = false;
	
	public Theme() {
	}
	
	public Theme(String name, Boolean enabled) {
		this.name = name;
		this.enabled = enabled;
	}

	@Column(unique=true, nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
