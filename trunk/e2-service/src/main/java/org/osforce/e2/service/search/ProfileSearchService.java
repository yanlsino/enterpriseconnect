package org.osforce.e2.service.search;

import org.osforce.e2.dao.search.SearchBean;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 8:37:01 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileSearchService {

	Page<Profile> searchProfilePage(Page<Profile> page, SearchBean searchBean);

	Integer countProfile(SearchBean searchBean);
}
