package org.osforce.connect.dao.search;

import org.osforce.connect.entity.profile.Profile;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 9:51:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ProfileSearchDao {

	Page<Profile> search(Page<Profile> page, SearchBean searchBean);

	Integer count(SearchBean searchBean);
}
