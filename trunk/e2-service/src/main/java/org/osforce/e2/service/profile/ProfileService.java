package org.osforce.e2.service.profile;

import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:03:56 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileService {

	Profile getProfile(Long profileId);
	
	Profile getProfile(Project project);
	/**
	 * view profile
	 * @param project
	 * @param user for profile view statistic
	 * @return
	 */
	Profile viewProfile(Project project, User user);
	
	void createProfile(Profile profile);
	
	void updateProfile(Profile profile);
	
	void deleteProfile(Long profileId);

	Page<Profile> getProfilePage(Page<Profile> page, ProjectCategory category);

	Page<Profile> getConcernedProfilePage(Page<Profile> page,
			ProjectCategory category, User user);

}
