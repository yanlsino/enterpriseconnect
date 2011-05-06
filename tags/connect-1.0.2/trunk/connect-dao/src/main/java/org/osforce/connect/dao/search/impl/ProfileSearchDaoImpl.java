package org.osforce.connect.dao.search.impl;

import org.apache.lucene.search.Query;
import org.osforce.connect.dao.search.ProfileSearchDao;
import org.osforce.connect.dao.search.SearchBean;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.platform.dao.support.AbstractSearchDao;
import org.osforce.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 9:53:13 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class ProfileSearchDaoImpl extends AbstractSearchDao<Profile> implements
		ProfileSearchDao {

	public ProfileSearchDaoImpl() {
		super(Profile.class);
	}

	public Page<Profile> search(Page<Profile> page, SearchBean searchBean) {
		Query query = getQueryBuilder().keyword().onField("title")
				.matching(searchBean.getKeywords()).createQuery();
		if (searchBean.getCategoryId() != null) {
			query = getQueryBuilder()
					.bool()
					.must(getQueryBuilder().keyword().onField("title")
							.matching(searchBean.getKeywords()).createQuery())
					.must(getQueryBuilder().keyword()
							.onField("project.category.id")
							.matching(searchBean.getCategoryId()).createQuery())
					.createQuery();
		}
		return searchPage(page, query);
	}

	public Integer count(SearchBean searchBean) {
		Query query = getQueryBuilder()
				.bool()
				.must(getQueryBuilder().keyword().onField("title")
						.matching(searchBean.getKeywords()).createQuery())
				.must(getQueryBuilder().keyword()
						.onField("project.category.id")
						.matching(searchBean.getCategoryId()).createQuery())
				.createQuery();
		return count(query);
	}
}
