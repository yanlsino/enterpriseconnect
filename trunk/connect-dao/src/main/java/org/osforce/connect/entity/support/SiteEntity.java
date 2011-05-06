package org.osforce.connect.entity.support;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.osforce.connect.entity.system.Site;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 6:52:20 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@MappedSuperclass
public class SiteEntity extends IdEntity {

	private static final long serialVersionUID = 4553754659122596181L;
	// helper
	protected Long siteId;
	// refer
	protected Site site;
	
	public SiteEntity() {
	}
	
	@Transient
	public Long getSiteId() {
		if(siteId==null && site!=null) {
			siteId = site.getId();
		}
		return siteId;
	}
	
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="site_id")
	@IndexedEmbedded
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}

}
