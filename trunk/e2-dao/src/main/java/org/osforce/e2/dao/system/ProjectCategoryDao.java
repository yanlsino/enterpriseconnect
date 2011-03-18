package org.osforce.e2.dao.system;

import java.util.List;

import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.platform.dao.BaseDao;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:03:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectCategoryDao extends BaseDao<ProjectCategory> {

	List<ProjectCategory> find(Long siteId);

	Page<ProjectCategory> findPage(Page<ProjectCategory> page, Long siteId);

}
