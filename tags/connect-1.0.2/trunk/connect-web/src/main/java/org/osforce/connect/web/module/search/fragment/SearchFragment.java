package org.osforce.connect.web.module.search.fragment;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.dao.search.SearchBean;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.search.ProfileSearchService;
import org.osforce.connect.service.system.ProjectCategoryService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 8:34:08 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SearchFragment {

	private ProfileSearchService profileSearchService;
	private ProjectCategoryService projectCategoryService;
	
	public SearchFragment() {
	}
	
	@Autowired
	public void setProfileSearchService(
			ProfileSearchService profileSearchService) {
		this.profileSearchService = profileSearchService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doBarView(SearchBean searchBean,
			Site site, FragmentContext context) {
		List<ProjectCategory> categories = projectCategoryService
				.getProjectCategoryList(site.getId());
		Integer totalCount = 0;
		if(StringUtils.isNotBlank(searchBean.getKeywords())) {
			for(ProjectCategory category : categories) {
				searchBean.setCategoryId(category.getId());
				Integer count = profileSearchService.countProfile(searchBean);
				category.setCount(count);
				totalCount += count;
			}
		}
		context.putRequestData(AttributeKeys.TOTAL_COUNT_KEY_READABLE, totalCount);
		context.putRequestData("categories", categories);
		context.putRequestData(AttributeKeys.SEARCH_BEAN_KEY_READABLE, searchBean);
		return "search/search_bar";
	}
	
	public String doResultView(SearchBean searchBean, 
			Page<Profile> page, FragmentContext context) {
		if(StringUtils.isNotBlank(searchBean.getKeywords())) {
			page = profileSearchService.searchProfilePage(page, searchBean);
			context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		}
		return "search/search_result";
	}
	
	public String doSiteFormView(SearchBean searchBean, 
			Site site, FragmentContext context) {
		context.putRequestData(AttributeKeys.SEARCH_BEAN_KEY_READABLE, searchBean);
		List<ProjectCategory> categories = projectCategoryService
				.getProjectCategoryList(site.getId());
		//categories.add(0, new ProjectCategory());//影响界面显示
		context.putRequestData("categories", categories);
		return "search/site_search_form";
	}
	
	public String doFormView(SearchBean searchBean,
			Site site, FragmentContext context) {
		context.putRequestData(AttributeKeys.SEARCH_BEAN_KEY_READABLE, searchBean);
		List<ProjectCategory> categories = projectCategoryService
				.getProjectCategoryList(site.getId());
		categories.add(0, new ProjectCategory());
		context.putRequestData("categories", categories);
		return "search/search_form";
	}
}
