package org.osforce.e2.dao.commons.impl;

import org.osforce.e2.dao.commons.CategoryDao;
import org.osforce.e2.entity.commons.Category;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:55:48 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class CategoryDaoImpl extends AbstractDao<Category>
	implements CategoryDao {

	public CategoryDaoImpl() {
		super(Category.class);
	}
}
