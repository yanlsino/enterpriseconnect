package org.osforce.connect.service.search.impl;

import org.osforce.connect.dao.search.ProfileSearchDao;
import org.osforce.connect.dao.search.SearchBean;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.service.search.ProfileSearchService;
import org.osforce.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 8:37:09 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ProfileSearchServiceImpl implements ProfileSearchService {

	private ProfileSearchDao profileSearchDao;
	
	public ProfileSearchServiceImpl() {
	}
	
	@Autowired
	public void setProfileSearchDao(ProfileSearchDao profileSearchDao) {
		this.profileSearchDao = profileSearchDao;
	}

	public Page<Profile> searchProfilePage(Page<Profile> page, SearchBean searchBean) {
		return profileSearchDao.search(page, searchBean);
	}

	public Integer countProfile(SearchBean searchBean) {
		return profileSearchDao.count(searchBean);
	}
}
