package org.osforce.connect.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.osforce.connect.entity.support.SiteEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 20, 2011 - 10:41:54 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="site_links")
@Cacheable
public class SiteLink extends SiteEntity {
	private static final long serialVersionUID = -2547262738105207326L;

	private String href;
	private String text;
	private String code;
	
	public SiteLink() {
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}