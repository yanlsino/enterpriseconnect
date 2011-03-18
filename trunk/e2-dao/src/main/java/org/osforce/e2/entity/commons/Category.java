package org.osforce.e2.entity.commons;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.osforce.e2.entity.support.SiteEntity;

/**
 * Commons category
 * @author gavin
 * @since 1.0.0
 * @create Jan 30, 2011 - 12:16:23 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="categories")
@Cacheable
public class Category extends SiteEntity {
	private static final long serialVersionUID = -6193549424628615098L;
	
	public Category() {
	}

}
