package org.osforce.connect.web.custom.framework;

import org.osforce.commons.castor.CastorUtil;
import org.osforce.connect.dao.search.SearchBean;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.osforce.platform.web.framework.core.impl.DefaultMethodParameterResolver;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:51:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class CustomMethodParameterResolver extends DefaultMethodParameterResolver {
	
	public CustomMethodParameterResolver() {
	}

	@Override
	protected Object resolveParameterByType(String paramName, Class<?> paramType) {
		Object arg = null;
		if(paramType.isAssignableFrom(Page.class)) {
			arg = getPage();
		} else if(paramType.isAssignableFrom(FragmentContext.class)) {
			arg = getFragmentContext();
		} else if(paramType.isAssignableFrom(Project.class)) {
			arg = getProject();
		} else if(paramType.isAssignableFrom(User.class)) {
			arg = getUser();
		} else if(paramType.isAssignableFrom(Site.class)) {
			arg = getSite();
		} else if(paramType.isAssignableFrom(SearchBean.class)) {
			arg = getSearchBean();
		} else if(paramType.isAssignableFrom(TeamMember.class)) {
			arg = getMember();
		}
		return arg;
	}
	
	private Project getProject() {
		return (Project) getFragmentContext().getRequestData(AttributeKeys.PROJECT_KEY);
	}
	
	private User getUser() {
		return  (User) getFragmentContext().getRequestData(AttributeKeys.USER_KEY);
	}
	
	private TeamMember getMember() {
		return (TeamMember) getFragmentContext().getRequestData(AttributeKeys.TEAM_MEMBER_KEY);
	}
	
	private Site getSite() {
		return (Site) getFragmentContext().getRequestData(AttributeKeys.SITE_KEY);
	}
	
	private SearchBean getSearchBean() {
		SearchBean searchBean = (SearchBean) getFragmentContext().getSessionData(
				AttributeKeys.SEARCH_BEAN_KEY);
		if(searchBean==null) {
			searchBean = new SearchBean();
		}
		//
		String categoryIdStr = getParameterValue("categoryId", null);
		Long categoryId = CastorUtil.castTo(categoryIdStr, Long.class);
		searchBean.setCategoryId(categoryId);
		//
		String keywords = getParameterValue("keywords", null);
		searchBean.setKeywords(keywords);
		// 
		searchBean.setSiteId(getSite().getId());
		getFragmentContext().putSessionData(AttributeKeys.SEARCH_BEAN_KEY, searchBean);
		return searchBean;
	}
	
	private Page<?> getPage() {
		String fragmentId = getFragmentContext().getFragmentData("id");
		String pageKey = fragmentId + "_page";
		Page<?> page = (Page<?>) getFragmentContext().getSessionData(pageKey);
		if(page==null) {
			// new page
			Integer pageSize = 10;
			if(getFragmentContext().getPreferenceData("limit")!=null) {
				String prefValue = getFragmentContext().getPreferenceData("limit");
				pageSize = CastorUtil.castTo(prefValue, Integer.class);
			} else if(getFragmentContext().getPreferenceData("pageSize")!=null) {
				String prefValue = getFragmentContext().getPreferenceData("pageSize");
				pageSize = CastorUtil.castTo(prefValue, Integer.class);
			} 
			page = new Page<Object>(pageSize);
		}
		// set page no
		Integer pageNo = 1;
		if(getFragmentContext().getParameterData("pageNo")!=null) {
			String paramValue = getFragmentContext().getParameterData("pageNo");
			pageNo = CastorUtil.castTo(paramValue, Integer.class);
		} 
		page.setPageNo(pageNo);
		// set page to session
		getFragmentContext().putSessionData(pageKey, page);
		return page;
	}
}
