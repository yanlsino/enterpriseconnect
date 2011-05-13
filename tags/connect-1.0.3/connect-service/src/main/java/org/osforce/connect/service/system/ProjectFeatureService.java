package org.osforce.connect.service.system;

import org.osforce.connect.entity.system.ProjectFeature;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:23:35 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProjectFeatureService {

	ProjectFeature getProjectFeature(Long featureId);
	
	ProjectFeature getProjectFeature(String code, Long projectId);
	
	void createProjectFeature(ProjectFeature feature);
	
	void updateProjectFeature(ProjectFeature feature);
	
	void deleteProjectFeature(Long featureId);
}
