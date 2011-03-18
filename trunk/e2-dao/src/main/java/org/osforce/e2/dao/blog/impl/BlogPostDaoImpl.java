package org.osforce.e2.dao.blog.impl;

import org.osforce.e2.dao.blog.BlogPostDao;
import org.osforce.e2.entity.blog.BlogPost;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:02:04 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class BlogPostDaoImpl extends AbstractDao<BlogPost>
		implements BlogPostDao {
	
	public BlogPostDaoImpl() {
		super(BlogPost.class);
	}
}
