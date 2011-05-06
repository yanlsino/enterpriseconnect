package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.ProjectFeatureDao;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:05:49 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProjectFeatureDaoImpl extends AbstractDao<ProjectFeature>
	implements ProjectFeatureDao {

	public ProjectFeatureDaoImpl() {
		super(ProjectFeature.class);
	}
}
