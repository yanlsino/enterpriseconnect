package org.osforce.connect.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
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
	private String layout;
	private String layoutPopup;
	private Boolean enabled = false;
	
	public Theme() {
	}
	
	public Theme(String name) {
		this.name = name;
		this.enabled = true;
	}

	@Column(unique=true, nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLayout() {
		if(StringUtils.endsWith(layout, ".jsp")) {
			layout = StringUtils.substringBefore(layout, ".jsp");
		}
		return layout;
	}
	
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public String getLayoutPopup() {
		if(StringUtils.endsWith(layoutPopup, ".jsp")) {
			layoutPopup = StringUtils.substringBefore(layoutPopup, ".jsp");
		}
		return layoutPopup;
	}
	
	public void setLayoutPopup(String layoutPopup) {
		this.layoutPopup = layoutPopup;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
